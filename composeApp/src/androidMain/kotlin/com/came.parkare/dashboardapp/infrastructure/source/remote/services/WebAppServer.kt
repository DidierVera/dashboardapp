package com.came.parkare.dashboardapp.infrastructure.source.remote.services

import android.content.Context
import fi.iki.elonen.NanoHTTPD
import java.io.File
import java.io.IOException
import fi.iki.elonen.NanoHTTPD.Response.Status
import java.io.FileNotFoundException

class WebAppServer(
    private val context: Context,
    port: Int
) : NanoHTTPD(port) {

    private val webAppDir = File(context.cacheDir, "webapp")
    private val defaultFile = "index.html"

    init {
        copyAssetsToCache()
    }

    override fun serve(session: IHTTPSession): Response {
        var uri = session.uri.trimStart('/')

        // Handle root path by redirecting to default file
        if (uri.isEmpty()) {
            uri = defaultFile
        }

        val file = File(webAppDir, uri)

        println("WebAppServer:: serving ${file.absolutePath}")

        return when {
            !file.exists() -> notFoundResponse()
            file.isDirectory -> serveDirectory(file)
            else -> serveFile(file)
        }
    }

    private fun serveFile(file: File): Response {
        return try {
            val mimeType = getMimeType(file)
            val fileData = file.readBytes() // Use readBytes() for binary-safe reading

            newFixedLengthResponse(
                Status.OK,
                mimeType,
                file.inputStream(),
                fileData.size.toLong()
            ).apply {
                addHeader("Accept-Ranges", "bytes")
            }
        } catch (e: IOException) {
            internalErrorResponse("Error reading file: ${e.message}")
        }
    }

    private fun serveDirectory(directory: File): Response {
        val indexFile = File(directory, defaultFile)
        return if (indexFile.exists()) {
            serveFile(indexFile)
        } else {
            newFixedLengthResponse(
                Status.OK,
                "text/html",
                generateDirectoryListing(directory)
            )
        }
    }

    private fun generateDirectoryListing(directory: File): String {
        val files = directory.listFiles()?.map { it.name } ?: emptyList()
        return """
            <html>
                <head><title>Directory Listing</title></head>
                <body>
                    <h1>Directory: ${directory.name}</h1>
                    <ul>
                        ${files.joinToString("") { "<li><a href='$it'>$it</a></li>" }}
                    </ul>
                </body>
            </html>
        """.trimIndent()
    }

    private fun getMimeType(file: File): String {
        return when (file.extension.lowercase()) {
            "html", "htm" -> "text/html"
            "css" -> "text/css"
            "js" -> "application/javascript"
            "json" -> "application/json"
            "png" -> "image/png"
            "jpg", "jpeg" -> "image/jpeg"
            "gif" -> "image/gif"
            "svg" -> "image/svg+xml"
            "wasm" -> "application/wasm"
            else -> "application/octet-stream"
        }
    }

    private fun notFoundResponse(): Response {
        return newFixedLengthResponse(
            Status.NOT_FOUND,
            "text/plain",
            "404 Not Found"
        )
    }

    private fun internalErrorResponse(message: String): Response {
        return newFixedLengthResponse(
            Status.INTERNAL_ERROR,
            "text/plain",
            "500 Internal Server Error: $message"
        )
    }

    private fun copyAssetsToCache() {
        try {
            copyAssetsRecursively(context.assets, "webapp", webAppDir)
            println("WebAppServer:: Assets copied successfully")
        } catch (e: Exception) {
            println("WebAppServer:: Error copying assets: ${e.message}")
            e.printStackTrace()
        }
    }

    private fun copyAssetsRecursively(assetManager: android.content.res.AssetManager, path: String, outputDir: File) {
        try {
            val assets = assetManager.list(path) ?: return

            if (!outputDir.exists()) {
                outputDir.mkdirs()
            }

            for (asset in assets) {
                val assetPath = if (path.isEmpty()) asset else "$path/$asset"
                val childOutput = File(outputDir, asset)

                try {
                    // Try to open as file
                    assetManager.open(assetPath).use { inputStream ->
                        childOutput.outputStream().use { outputStream ->
                            inputStream.copyTo(outputStream)
                        }
                    }
                } catch (e: FileNotFoundException) {
                    // If opening as file fails, treat as directory
                    copyAssetsRecursively(assetManager, assetPath, childOutput)
                } catch (e: IOException) {
                    println("WebAppServer:: Error copying $assetPath: ${e.message}")
                }
            }
        } catch (e: Exception) {
            println("WebAppServer:: Error listing assets in $path: ${e.message}")
            throw e
        }
    }
}
package com.cameparkare.dashboardapp.infrastructure.source.remote.services

import android.content.Context
import fi.iki.elonen.NanoHTTPD
import java.io.File
import java.io.IOException


class WebAppServer(
    private val context: Context,
    port: Int
) : NanoHTTPD(port) {

    private val webAppDir: File = File(context.cacheDir, "webapp")

    init {
        copyAssetsToCache(context)
    }
    override fun serve(session: IHTTPSession): Response {
        val uri = session.uri
        val file = File(webAppDir, uri.trimStart('/'))

        println("WebAppServer:: serve file ${file.absolutePath}")

        return if (file.exists() && file.isFile) {
            // Detecta el tipo de archivo (MIME type)
            val mimeType = when (file.extension) {
                "html" -> "text/html"
                "css" -> "text/css"
                "js" -> "application/javascript"
                else -> "application/octet-stream"
            }
            // Devuelve el contenido del archivo
            newFixedLengthResponse(Response.Status.OK, mimeType, file.readText())
        } else {
            // Archivo no encontrado
            newFixedLengthResponse(Response.Status.NOT_FOUND, "text/plain", "404 Not Found, try with /index.html")
        }
    }
    private fun copyAssetsToCache(context: Context) {
        val assetManager = context.assets
        val webAppAssetDir = "webapp"
        try {
            val files = assetManager.list(webAppAssetDir)
            println("WebAppServer:: assetFiles ${files?.size}")
            files?.forEach { fileName ->
                println("WebAppServer:: assetFile $fileName")
                val inputStream = assetManager.open("$webAppAssetDir/$fileName")
                val outFile = File(webAppDir, fileName)

                println("WebAppServer:: cacheFile ${outFile.absolutePath}")

                if (!webAppDir.exists()) {
                    webAppDir.mkdir()
                }

                inputStream.use { input ->
                    outFile.outputStream().use { output ->
                        input.copyTo(output)
                    }
                }
            }
        } catch (e: IOException) {
            println("WebAppServer:: ERROR")
            e.printStackTrace()
        }
    }
}
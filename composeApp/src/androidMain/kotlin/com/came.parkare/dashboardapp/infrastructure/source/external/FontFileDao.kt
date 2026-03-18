package com.came.parkare.dashboardapp.infrastructure.source.external

import android.content.Context
import android.graphics.Typeface
import android.os.Build
import android.os.Environment
import com.came.parkare.dashboardapp.config.constants.Constants.FONT_FILE_NAME
import com.came.parkare.dashboardapp.config.constants.Constants.FONT_REGISTRY_KEY
import com.came.parkare.dashboardapp.config.utils.AppLogger
import com.came.parkare.dashboardapp.config.utils.SharedPreferencesProvider
import com.came.parkare.dashboardapp.config.utils.SharedPreferencesWrapper
import com.came.parkare.dashboardapp.domain.models.FontInfo
import com.came.parkare.dashboardapp.domain.repositories.local.FontFileRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import java.io.File

class FontFileDao(
    private val context: Context,
    private val appLogger: AppLogger,
    private val preferences: SharedPreferencesProvider,
): FontFileRepository {

    private val fontsDirectory: File by lazy {
        File(context.filesDir, "fonts").apply {  // ← internal storage, no permissions needed
            if (!exists()) mkdirs()
        }
    }

    override  suspend fun saveFontFile(
        fileName: String,
        fontData: ByteArray,
        overwrite: Boolean
    ): Boolean {
        return withContext(Dispatchers.IO) {
            try {
                // Sanitize filename to prevent path traversal attacks
                val safeFileName = sanitizeFileName(fileName)
                val fontFile = File(fontsDirectory, FONT_FILE_NAME)

                // Check if file already exists
                if (fontFile.exists() && !overwrite) {
                    appLogger.trackLog(
                        "Font Save",
                        "Font file $safeFileName already exists and overwrite=false"
                    )
                    return@withContext false
                }

                // Validate that it's actually a font file
                if (!isValidFontFile(fontData)) {
                    appLogger.trackLog("Font Save", "Invalid font file format for $safeFileName")
                    return@withContext false
                }

                // Ensure writable if already exists
                if (fontFile.exists()) {
                    fontFile.setWritable(true, true)
                }

                // Write the font data
                fontFile.writeBytes(fontData)

                appLogger.trackLog(
                    "Font Save",
                    "Font file $safeFileName saved successfully. Size: ${fontData.size} bytes"
                )

                updateFontRegistry(safeFileName)
                true

            } catch (e: Exception) {
                e.printStackTrace()
                appLogger.trackError(e)
                appLogger.trackLog(
                    "Font Save Error",
                    "Failed to save font file $fileName: ${e.message}"
                )
                false
            }
        }
    }

    private fun updateFontRegistry(newValue: String) {
        preferences.put(FONT_REGISTRY_KEY, newValue)
    }
    private fun isValidFontFile(data: ByteArray): Boolean {
        // Check magic numbers for different font formats
        return when {
            // TTF/OTF files start with 0x00010000 or 'OTTO'
            data.size >= 4 -> {
                val header = data.sliceArray(0..3)
                val headerHex = header.joinToString("") { "%02X".format(it) }

                // TTF: 0x00010000 or 0x74727565 ('true')
                // OTF: 0x4F54544F ('OTTO')
                headerHex == "00010000" ||
                        headerHex == "74727565" ||
                        headerHex == "4F54544F" ||
                        headerHex == "74746366"  // 'ttcf' for TTC collections
            }
            else -> false
        }
    }

    // Helper method to get font file
    override fun getFontFile(fileName: String): File? {
        val safeFileName = sanitizeFileName(fileName)
        val fontFile = File(fontsDirectory, safeFileName)
        return if (fontFile.exists() && fontFile.isFile) fontFile else null
    }


    private fun sanitizeFileName(fileName: String): String {
        // Remove any path separators and only allow safe characters
        return fileName
            .replace(File.separator, "_")
            .replace("..", "_")
            .filter { it.isLetterOrDigit() || it == '.' || it == '-' || it == '_' }
            .takeIf { it.isNotBlank() } ?: "font_${System.currentTimeMillis()}.ttf"
    }

    // List all available fonts
    override suspend fun listAvailableFonts(): List<FontInfo> {
        return withContext(Dispatchers.IO) {
            fontsDirectory.listFiles()
                ?.filter { file ->
                    file.isFile && isFontFile(file.extension)
                }
                ?.map { file ->
                    FontInfo(
                        fileName = file.name,
                        size = file.length(),
                        lastModified = file.lastModified(),
                        isValid = isValidFontFile(file.readBytes())
                    )
                }
                ?: emptyList()
        }
    }

    private fun isFontFile(extension: String): Boolean {
        return extension.equals("ttf", ignoreCase = true) ||
                extension.equals("otf", ignoreCase = true) ||
                extension.equals("ttc", ignoreCase = true)
    }

    // Helper method to load font as Typeface
    override fun loadTypeface(fileName: String): Typeface? {
        return try {
            val fontFile = getFontFile(fileName)
            if (fontFile != null) {
                Typeface.createFromFile(fontFile)
            } else {
                null
            }
        } catch (e: Exception) {
            appLogger.trackError(e)
            null
        }
    }
}
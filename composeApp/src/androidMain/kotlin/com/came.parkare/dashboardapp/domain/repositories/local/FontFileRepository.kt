package com.came.parkare.dashboardapp.domain.repositories.local

import android.graphics.Typeface
import com.came.parkare.dashboardapp.domain.models.FontInfo
import java.io.File

interface FontFileRepository {
    suspend fun saveFontFile(fileName: String, fontData: ByteArray, overwrite: Boolean): Boolean
    fun getFontFile(fileName: String): File?
    fun loadTypeface(fileName: String): Typeface?
    suspend fun listAvailableFonts(): List<FontInfo>
}
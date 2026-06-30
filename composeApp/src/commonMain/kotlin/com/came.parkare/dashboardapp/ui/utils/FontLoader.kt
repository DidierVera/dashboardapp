package com.came.parkare.dashboardapp.ui.utils

import androidx.compose.ui.text.font.FontFamily

interface FontLoader {
    suspend fun loadTypeface(fileName: String, bytes: ByteArray): Any?
}

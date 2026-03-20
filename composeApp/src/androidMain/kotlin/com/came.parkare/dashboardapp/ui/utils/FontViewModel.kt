package com.came.parkare.dashboardapp.ui.utils

import android.graphics.Typeface
import android.graphics.Typeface.create
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontListFontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.came.parkare.dashboardapp.config.constants.Constants.FONT_BOLD
import com.came.parkare.dashboardapp.config.constants.Constants.FONT_MEDIUM
import com.came.parkare.dashboardapp.config.constants.Constants.FONT_REGULAR
import com.came.parkare.dashboardapp.infrastructure.source.external.FontFileDao
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class FontViewModel(
    private val fontFileDao: FontFileDao
) : ViewModel() {

    private var currentTypeface: Typeface? = null

    private val _fontFamily = MutableStateFlow<FontFamily>(FontFamily.Default)
    val fontFamily: StateFlow<FontFamily> = _fontFamily.asStateFlow()

    fun reloadFont() {
        viewModelScope.launch(Dispatchers.IO) {
            val regularFile = fontFileDao.getFontFile(FONT_REGULAR)
            val boldFile = fontFileDao.getFontFile(FONT_BOLD)
            val mediumFile = fontFileDao.getFontFile(FONT_MEDIUM)

            if (regularFile == null) return@launch

            _fontFamily.value = FontFamily(
                Font(file = regularFile, weight = FontWeight.Normal),
                Font(file = boldFile ?: regularFile, weight = FontWeight.Bold),
                Font(file = mediumFile ?: regularFile, weight = FontWeight.Medium),
            )
        }
    }

    override fun onCleared() {
        super.onCleared()
        currentTypeface = null
    }
}
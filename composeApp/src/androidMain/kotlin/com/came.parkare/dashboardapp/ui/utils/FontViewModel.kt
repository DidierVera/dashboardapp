package com.came.parkare.dashboardapp.ui.utils

import android.graphics.Typeface
import androidx.compose.ui.text.font.FontFamily
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.came.parkare.dashboardapp.config.constants.Constants.FONT_FILE_NAME
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
            val typeface = fontFileDao.loadTypeface(FONT_FILE_NAME) ?: return@launch
            currentTypeface = typeface  // ✅ strong reference kept alive
            _fontFamily.value = FontFamily(typeface)
        }
    }

    override fun onCleared() {
        super.onCleared()
        currentTypeface = null
    }
}
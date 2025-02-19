package com.cameparkare.dashboardapp.ui.screens.main

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.ui.unit.dp
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.cameparkare.dashboardapp.config.Constants.MARGIN_BOTTOM
import com.cameparkare.dashboardapp.config.Constants.MARGIN_LEFT
import com.cameparkare.dashboardapp.config.Constants.MARGIN_RIGHT
import com.cameparkare.dashboardapp.config.Constants.MARGIN_TOP
import com.cameparkare.dashboardapp.config.Constants.TEXT_SIZE_SCALE
import com.cameparkare.dashboardapp.config.Constants.VIDEO_FRAME
import com.cameparkare.dashboardapp.config.dataclasses.ErrorTypeClass
import com.cameparkare.dashboardapp.config.utils.AppLogger
import com.cameparkare.dashboardapp.config.utils.IServerConnection
import com.cameparkare.dashboardapp.config.utils.SharedPreferencesProvider
import com.cameparkare.dashboardapp.domain.models.components.CommonStyleModel
import com.cameparkare.dashboardapp.domain.models.components.ElementModel
import com.cameparkare.dashboardapp.domain.models.components.TextDataModel
import com.cameparkare.dashboardapp.domain.usecases.GetCardClassTranslations
import com.cameparkare.dashboardapp.ui.utils.FilesUtils
import com.cameparkare.dashboardapp.ui.utils.UiUtils
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class MainViewModel (
//    private val initConfiguration: InitConfiguration,
    //private val cardClassConfiguration: GetCardClassTranslations,
//    private val startParkingConnection: StartSocketConnection,
//    private val getLanguagesByDispatcher: GetLanguagesByDispatcher,
//    private val getScreenIdByDispatcher: GetScreenIdByDispatcher,
    private val ditsUtils: UiUtils,
    private val preferences: SharedPreferencesProvider,
    private val appLogger: AppLogger,
    private val filesUtils: FilesUtils,
    private val serverConnection: IServerConnection
): ViewModel() {

    private val _itemsState = MutableStateFlow(MainState())
    val itemsState: StateFlow<MainState>
        get() = _itemsState.asStateFlow()

    private val _backgroundState = MutableStateFlow(String())
    val backgroundState: StateFlow<String>
        get() = _backgroundState.asStateFlow()

    private val _videoState = MutableStateFlow(VideoPlayerState(emptyList()))
    val videoState: StateFlow<VideoPlayerState> = _videoState.asStateFlow()

    init {
        try {
            appLogger.trackLog("INIT", "Inicio de aplicación")
        }catch (e: Exception) {
            appLogger.trackError(e)
            val dashboardItems = listOf(
                ElementModel.TextModel(
                data = TextDataModel(
                    defaultText = e.message.toString(),
                    dashboardItemId = "",
                    fontWeight = "Bold",
                    textColor = "#000000",
                    translations = mapOf(Pair("es",e.message.toString())),
                    textSize = 24,
                    style = CommonStyleModel(
                        padding = 0
                    ))
            ))

            _itemsState.update { it.copy(newItems = dashboardItems) }
        }
    }
    fun showVideoFrame() = preferences.get(VIDEO_FRAME, false)

    fun getTranslationText(lang: String){
        viewModelScope.launch {
            try {
                val buildElements = ditsUtils.buildDashboardItem(_itemsState.value.newItems, _itemsState.value.ditsUI, lang)
                _itemsState.update { it.copy(newItems = buildElements) }
            }catch (e: Exception){
                appLogger.trackError(e)
            }
        }
    }

    private fun checkContentMargin() {
        val marginTop = preferences.get(MARGIN_TOP, 10)
        val marginBottom = preferences.get(MARGIN_BOTTOM, 10)
        val marginRight = preferences.get(MARGIN_RIGHT, 10)
        val marginLeft = preferences.get(MARGIN_LEFT, 10)
        _itemsState.update { it.copy(contentPadding = PaddingValues(
            marginLeft.dp, marginTop.dp,
            marginRight.dp, marginBottom.dp)
        )
        }
    }

    private fun checkTextSizeScale() {
        val textSizeScale = preferences.get(TEXT_SIZE_SCALE, 10)
        _itemsState.update { it.copy(textSizeScale = textSizeScale) }
    }

    private fun validateError(error: ErrorTypeClass) {
        val dashboardItems = listOf(
            ElementModel.TextModel(
                data = TextDataModel(
                    defaultText = "An Error Occurred",
                    dashboardItemId = "",
                    fontWeight = "Bold",
                    textColor = "#000000",
                    translations = mapOf(Pair("es","An Error Occurred")),
                    textSize = 24,
                    style = CommonStyleModel(padding = 0))
            ),
            ElementModel.TextModel(
                data = TextDataModel(
                    defaultText = error.javaClass.simpleName,
                    dashboardItemId = "",
                    fontWeight = "Regular",
                    textColor = "#FF5800",
                    translations = mapOf(Pair("es",error.javaClass.simpleName)),
                    textSize = 24,
                    style = CommonStyleModel(padding = 0))
            )
        )
        _itemsState.update { it.copy(newItems = dashboardItems) }
    }
}
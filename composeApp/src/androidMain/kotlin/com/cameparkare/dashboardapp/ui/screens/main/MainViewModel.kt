package com.cameparkare.dashboardapp.ui.screens.main

import android.os.Environment
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.ui.unit.dp
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.cameparkare.dashboardapp.config.DefaultDits
import com.cameparkare.dashboardapp.config.constants.Constants.MARGIN_BOTTOM
import com.cameparkare.dashboardapp.config.constants.Constants.MARGIN_LEFT
import com.cameparkare.dashboardapp.config.constants.Constants.MARGIN_RIGHT
import com.cameparkare.dashboardapp.config.constants.Constants.MARGIN_TOP
import com.cameparkare.dashboardapp.config.constants.Constants.TEXT_SIZE_SCALE
import com.cameparkare.dashboardapp.config.constants.Constants.TIME_DELAY
import com.cameparkare.dashboardapp.config.constants.Constants.VIDEO_FRAME
import com.cameparkare.dashboardapp.config.dataclasses.ErrorTypeClass
import com.cameparkare.dashboardapp.config.dataclasses.ServiceResult
import com.cameparkare.dashboardapp.config.utils.AppLogger
import com.cameparkare.dashboardapp.config.utils.IServerConnection
import com.cameparkare.dashboardapp.config.utils.SharedPreferencesProvider
import com.cameparkare.dashboardapp.domain.models.ScreenModel
import com.cameparkare.dashboardapp.domain.models.components.CommonStyleModel
import com.cameparkare.dashboardapp.domain.models.components.ElementModel
import com.cameparkare.dashboardapp.domain.models.components.TextDataModel
import com.cameparkare.dashboardapp.domain.models.terminal.TerminalResponseModel
import com.cameparkare.dashboardapp.domain.usecases.FtpServerConfiguration
import com.cameparkare.dashboardapp.domain.usecases.InitConfiguration
import com.cameparkare.dashboardapp.domain.usecases.StartSocketConnection
import com.cameparkare.dashboardapp.ui.utils.FilesUtils
import com.cameparkare.dashboardapp.ui.utils.UiUtils
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.isActive
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.io.File
import kotlin.coroutines.cancellation.CancellationException

class MainViewModel (
    private val initConfiguration: InitConfiguration,
    private val ftpServerConfiguration: FtpServerConfiguration,
//    private val cardClassConfiguration: GetCardClassTranslations,
    private val startParkingConnection: StartSocketConnection,
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

    private var loopJob: Job? = null
    init {
        try {
            appLogger.trackLog("INIT", "Inicio de aplicación")
            checkBackgroundImage()
            initAppConfig()
            checkContentMargin()
            checkTextSizeScale()
            checkVideos()
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

    private fun checkBackgroundImage() {
        val backgroundImage = filesUtils.getImageFromDirectory(
            "${Environment.DIRECTORY_PICTURES}/Dashboard",
            "background-image")
        if (!backgroundImage.isNullOrBlank()){
            appLogger.trackLog("BACKGROUND_IMAGE", backgroundImage)
            _backgroundState.update { backgroundImage }
        }
    }


    private fun initAppConfig() {
        viewModelScope.launch {
            showIsConnectingSignal()
            when (val initConfigResult = initConfiguration.invoke()){
                is ServiceResult.Error -> validateError(initConfigResult.error)
                is ServiceResult.Success -> {
                    when(val ftpServerConfigResult = ftpServerConfiguration.invoke()){
                        is ServiceResult.Error -> validateError(ftpServerConfigResult.error)
                        else -> { }
                    }
                    if(initConfigResult.data?.any { it.dispatcherCode == 5L } != null){
                        _itemsState.update { it.copy(screenList = initConfigResult.data) }
                        loadScreenInformation(
                            data = TerminalResponseModel(5, DefaultDits.idleConnected()),
                            initConfigResult.data
                        )
                        //loadLanguages(5)
                    }
                    loopJob = customLoop() // Start the initial loop

                    startParkingConnection.invoke { result ->
                        when(result){
                            is ServiceResult.Error -> validateError(result.error)
                            is ServiceResult.Success -> {
                                loadScreenInformation(result.data!!, initConfigResult.data, _itemsState.value.translations.first())
                            }
                        }
                    }
                }
            }
        }
    }

    private fun customLoop(): Job {
        val delayTime = preferences.get(TIME_DELAY, 5)
        return CoroutineScope(Dispatchers.IO).launch {
            while (isActive) { // Check if the coroutine is still active
                try {
                    itemsState.value.translations.forEach { lang ->
                        withContext(Dispatchers.Main) {
                            if (isActive) { // Check again before updating state
                                _itemsState.update { it.copy(currentLang = lang) }
                            }
                        }
                        delay(delayTime.toLong() * 1000)
                    }
                } catch (e: CancellationException){
                    println("Coroutine cancelled: ${e.message}")
                }catch (e: Exception){
                    appLogger.trackError(e)
                }
            }
        }
    }

    private fun checkVideos() {
        if(!showVideoFrame()) return

        val files = filesUtils.getVideosFiles("${Environment.DIRECTORY_MOVIES}/Dashboard/videos")
        if (files.isNotEmpty()){

            val filesRoutes : MutableList<String> = mutableListOf()
            files.forEach { file ->
                if (File(file).exists()){
                    filesRoutes.add(File(file).absolutePath)
                }
            }
            _videoState.update { currentState ->
                currentState.copy(videoRoutes = filesRoutes)
            }
        }
    }
    private fun loadScreenInformation(
        data: TerminalResponseModel,
        screens: List<ScreenModel>?,
        lang: String = "es"
    ) {
        viewModelScope.launch {
            val screenId = "5"
            if (screenId.isNotBlank()){
                _itemsState.update { it.copy(ditsUI = data.ditsTUI) }
                val dataToShow = screens?.find { it.dispatcherCode == data.dispatcherCode.toLong() }
                if (dataToShow != null){
                    val buildElements = ditsUtils.buildDashboardItem(dataToShow.elements, data.ditsTUI, lang)
                    _itemsState.update { it.copy(newItems = buildElements) }
                }

                // Cancel the existing loop job and wait for it to finish
                loopJob?.cancel()
                //loopJob?.join() // Wait for the coroutine to finish cancellation
                loopJob = customLoop() // Start a new loop
            }
        }
    }


    private fun showIsConnectingSignal() {
        serverConnection.statusConnection.onEach { status ->
            appLogger.trackLog("Is Connected to terminal: ", "$status")
            if(status != _itemsState.value.statusConnection) {
                _itemsState.update { it.copy(statusConnection = status) }
            }
            if (!status){
                loadScreenInformation(
                    data = TerminalResponseModel(1005, null),
                    _itemsState.value.screenList
                )
            }
        }.launchIn(viewModelScope)
    }

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
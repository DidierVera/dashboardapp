package com.cameparkare.dashboardapp.ui.screens.main

import android.os.Environment
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.ui.unit.dp
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.cameparkare.dashboardapp.config.DefaultDits
import com.cameparkare.dashboardapp.config.constants.Constants.TEXT_SIZE_SCALE
import com.cameparkare.dashboardapp.config.constants.Constants.TIME_DELAY
import com.cameparkare.dashboardapp.config.constants.Constants.VIDEO_FRAME
import com.cameparkare.dashboardapp.config.dataclasses.ErrorTypeClass
import com.cameparkare.dashboardapp.config.dataclasses.ServiceResult
import com.cameparkare.dashboardapp.config.utils.AppLogger
import com.cameparkare.dashboardapp.config.utils.IServerConnection
import com.cameparkare.dashboardapp.config.utils.SharedPreferencesProvider
import com.cameparkare.dashboardapp.domain.models.ScreenModel
import com.cameparkare.dashboardapp.domain.models.components.ElementModel
import com.cameparkare.dashboardapp.domain.models.terminal.TerminalResponseModel
import com.cameparkare.dashboardapp.domain.usecases.ConnectionConfig
import com.cameparkare.dashboardapp.domain.usecases.FtpServerConfiguration
import com.cameparkare.dashboardapp.domain.usecases.GetScreenByDispatcher
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
    private val connectionConfig: ConnectionConfig,
    private val ftpServerConfiguration: FtpServerConfiguration,
    private val startParkingConnection: StartSocketConnection,
    private val getScreenByDispatcher: GetScreenByDispatcher,
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
            serverConnection.setRestartApp(false)
            appLogger.trackLog("INIT", "Inicio de aplicación")
            checkBackgroundImage()
            initAppConfig()
            checkTextSizeScale()
            checkVideos()
        }catch (e: Exception) {
            appLogger.trackError(e)
            val dashboardItems = listOf(
                DefaultDits.getElementText(text = e.message.toString(),fontWeight = "Bold")
            )

            _itemsState.update { it.copy(newItems = dashboardItems) }
        }
    }
    fun showVideoFrame() = preferences.get(VIDEO_FRAME, false)

    private fun checkBackgroundImage() {
        val backgroundImage = filesUtils.getImageFromDirectory(
            "/Dashboard",
            "background-image")
        println("check the background image: $backgroundImage")
        if (!backgroundImage.isNullOrBlank()){
            appLogger.trackLog("BACKGROUND_IMAGE", backgroundImage)
            _backgroundState.update { backgroundImage }
        }
    }


    private fun initAppConfig() {
        viewModelScope.launch {
            showIsConnectingSignal()
            //connection config initialization
            when (val connectionResult = connectionConfig.invoke()){
                is ServiceResult.Error -> validateError(connectionResult.error)
                is ServiceResult.Success -> {}
            }
            //ftp server config initialization
            when(val ftpServerConfigResult = ftpServerConfiguration.invoke()){
                is ServiceResult.Error -> validateError(ftpServerConfigResult.error)
                else -> { }
            }
            //screens config initialization
            when (val initConfigResult = initConfiguration.invoke()){
                is ServiceResult.Error -> validateError(initConfigResult.error)
                is ServiceResult.Success -> {

                    if(initConfigResult.data?.any { it.dispatcherCode == 5L } != null){
                        _itemsState.update { it.copy(screenList = initConfigResult.data) }
                        loadScreenInformation(
                            data = TerminalResponseModel(5, DefaultDits.idleConnected())
                        )

                        loadLanguages()
                    }
                    loopJob = customLoop() // Start the initial loop

                    startParkingConnection.invoke { result ->
                        when(result){
                            is ServiceResult.Error -> validateError(result.error)
                            is ServiceResult.Success -> {
                                loadScreenInformation(result.data!!, _itemsState.value.translations.first())
                            }
                        }
                    }
                }
            }
        }
    }

    private fun loadLanguages(){
        viewModelScope.launch {
            val languages = _itemsState.value.screenList.firstOrNull {
                it.dispatcherCode == 5L
            }?.elements?.filterIsInstance<ElementModel.TextModel>()
                ?.firstOrNull()?.data?.translations

            _itemsState.update { state ->
                state.copy(
                    currentLang = languages?.keys?.first().orEmpty(),
                    translations = languages?.keys?.toList().orEmpty()
                )
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
        lang: String = "lang1"
    ) {
        viewModelScope.launch {
            val screen = getScreenByDispatcher.invoke(data.dispatcherCode)
            if (screen != null){
                checkContentMargin(screen.marginLeft, screen.marginTop, screen.marginRight, screen.marginBottom)
                _itemsState.update { it.copy(ditsUI = data.ditsTUI) }
                val buildElements = ditsUtils.buildDashboardItem(screen.elements, data.ditsTUI, lang)
                _itemsState.update { it.copy(newItems = buildElements) }

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
                    data = TerminalResponseModel(1005, null)
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

    private fun checkContentMargin(marginLeft: Int, marginTop: Int, marginRight: Int, marginBottom: Int) {
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
            DefaultDits.getElementText(text = "An Error Occurred", fontWeight = "Bold"),
            DefaultDits.getElementText(text = error.javaClass.simpleName, fontWeight = "Regular",textColor = "#FF5800"),
        )
        _itemsState.update { it.copy(newItems = dashboardItems) }
    }

    fun onRestartApp(launch: () -> Unit){
        serverConnection.restartApp.onEach { value ->
            if (value){
                launch.invoke()
            }
        }.launchIn(viewModelScope)
    }
}
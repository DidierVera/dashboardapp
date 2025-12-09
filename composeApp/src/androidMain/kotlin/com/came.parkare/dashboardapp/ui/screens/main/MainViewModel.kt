package com.came.parkare.dashboardapp.ui.screens.main

import android.app.Activity
import android.os.Environment
import android.provider.Settings
import android.util.Log
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.ui.unit.dp
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.came.parkare.dashboardapp.config.DefaultDits
import com.came.parkare.dashboardapp.config.constants.Constants.AUTO_BRIGHTNESS
import com.came.parkare.dashboardapp.config.constants.Constants.AUTO_BRIGHTNESS_DELAY_TIME
import com.came.parkare.dashboardapp.config.constants.Constants.TEXT_SIZE_SCALE
import com.came.parkare.dashboardapp.config.constants.Constants.TIME_DELAY
import com.came.parkare.dashboardapp.config.constants.Constants.VIDEO_FRAME
import com.came.parkare.dashboardapp.config.dataclasses.ErrorTypeClass
import com.came.parkare.dashboardapp.config.dataclasses.ServiceResult
import com.came.parkare.dashboardapp.config.dataclasses.TypeConnectionEnum
import com.came.parkare.dashboardapp.config.utils.AppLogger
import com.came.parkare.dashboardapp.config.utils.IServerConnection
import com.came.parkare.dashboardapp.config.utils.SharedPreferencesProvider
import com.came.parkare.dashboardapp.domain.models.components.ElementModel
import com.came.parkare.dashboardapp.domain.models.terminal.TerminalResponseModel
import com.came.parkare.dashboardapp.domain.usecases.GetScreenByDispatcher
import com.came.parkare.dashboardapp.domain.usecases.InitConfiguration
import com.came.parkare.dashboardapp.domain.usecases.StartSocketConnection
import com.came.parkare.dashboardapp.ui.utils.FilesUtils
import com.came.parkare.dashboardapp.ui.utils.SystemBrightnessManager
import com.came.parkare.dashboardapp.ui.utils.UiUtils
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
    private val startParkingConnection: StartSocketConnection,
    private val getScreenByDispatcher: GetScreenByDispatcher,
    private val ditsUtils: UiUtils,
    private val preferences: SharedPreferencesProvider,
    private val appLogger: AppLogger,
    private val filesUtils: FilesUtils,
    private val serverConnection: IServerConnection
): ViewModel() {
    private lateinit var brightnessManager: SystemBrightnessManager

    private val _itemsState = MutableStateFlow(MainState())
    val itemsState: StateFlow<MainState>
        get() = _itemsState.asStateFlow()

    private val _backgroundState = MutableStateFlow("")
    val backgroundState: StateFlow<String>
        get() = _backgroundState.asStateFlow()

    private val _videoState = MutableStateFlow(VideoPlayerState(emptyList()))
    val videoState: StateFlow<VideoPlayerState> = _videoState.asStateFlow()

    private val _showVideoFrame = MutableStateFlow(false)
    val showVideoFrame: StateFlow<Boolean>
        get() = _showVideoFrame.asStateFlow()

    private val _activeBrightnessMode = MutableStateFlow(false)
    val activeBrightnessMode: StateFlow<Boolean>
        get() = _activeBrightnessMode.asStateFlow()

    private val _startBrightnessMode = MutableStateFlow(false)
    val startBrightnessMode: StateFlow<Boolean>
        get() = _startBrightnessMode.asStateFlow()

    private val _delayBrightnessTimeout = MutableStateFlow(2)
    val delayBrightnessTimeout: StateFlow<Int>
        get() = _delayBrightnessTimeout.asStateFlow()

    private var loopJob: Job? = null
    init {
        try {
            appLogger.trackLog("INIT", "Inicio de aplicación")
            onRestartApp()
            registerListeners()
            initAllConfig()
        }catch (e: Exception) {
            appLogger.trackError(e)
            val dashboardItems = listOf(
                DefaultDits.getElementText(text = e.message.toString(),fontWeight = "Bold")
            )

            _itemsState.update { it.copy(newItems = dashboardItems) }
        }
    }

    private fun registerListeners() {
        getAllDataFromServices()
        registerScreensListener()
        registerConnectionSignal()
        registerTerminalListener()
    }

    private fun initAllConfig() {
        checkVideoFrame()
        checkBackgroundImage()
        checkTextSizeScale()
        checkBrightnessMode()
        startPeriodicChecking()
        setTerminalConnection(serverConnection.typeConnection.value)
    }

    fun checkBrightnessMode() {
        viewModelScope.launch {
            val autoBrightness = preferences.get(AUTO_BRIGHTNESS, false)
            val autoBrightnessDelay = preferences.get(AUTO_BRIGHTNESS_DELAY_TIME, 2)

            // Only update if values actually changed to avoid unnecessary recompositions
            if (_activeBrightnessMode.value != autoBrightness) {
                _activeBrightnessMode.update { autoBrightness }
            }
            if (_delayBrightnessTimeout.value != autoBrightnessDelay) {
                _delayBrightnessTimeout.update { autoBrightnessDelay }
            }
        }
    }

    private fun startPeriodicChecking() {
        viewModelScope.launch {
            while (true) {
                delay(5000) // Check every 5 seconds (adjust as needed)
                checkBrightnessMode()
            }
        }
    }

    private fun registerTerminalListener() {
        serverConnection.typeConnection.onEach { connection ->
            setTerminalConnection(connection)
        }.launchIn(viewModelScope)
    }

    private fun setTerminalConnection(connection: TypeConnectionEnum) {
        startParkingConnection.invoke(connection) { result ->
            when(result){
                is ServiceResult.Error -> validateError(result.error)
                is ServiceResult.Success -> {
                    loadScreenInformation(result.data!!, _itemsState.value.translations.first())
                }
            }
        }
    }

    private fun registerScreensListener() {
        serverConnection.screensList.onEach { screens ->
            _itemsState.update { it.copy(screenList = screens) }
        }.launchIn(viewModelScope)
    }

    private fun checkVideoFrame() {
        val videoFrame = preferences.get(VIDEO_FRAME, false)
        _showVideoFrame.update { videoFrame }
        if (videoFrame){
            checkVideos()
        }else{
            _videoState.update { it.copy(videoRoutes = emptyList()) }
        }
    }


    private fun checkBackgroundImage() {
        viewModelScope.launch {
            val backgroundImage = filesUtils.getImageFromDatabase("background-image")
            appLogger.trackLog("BACKGROUND_IMAGE", backgroundImage)
            _backgroundState.update { backgroundImage ?: "" }
        }
    }


    private fun getAllDataFromServices() {
        viewModelScope.launch {
            //screens config initialization
            when (val initConfigResult = initConfiguration.invoke()){
                is ServiceResult.Error -> validateError(initConfigResult.error)
                is ServiceResult.Success -> {
                    loadLanguages()

                    if(_itemsState.value.screenList.any { it.dispatcherCode == 5L }){
                        loadScreenInformation(data = TerminalResponseModel(5, DefaultDits.idleConnected()))
                    }

                    loopJob = customLoop() // Start the initial loop
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
                    println("Coroutine cancelled because change screen: ${e.message}")
                }catch (e: Exception){
                    appLogger.trackError(e)
                }
            }
        }
    }

    private fun checkVideos() {
        _videoState.update { it .copy(videoRoutes = emptyList()) }
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
                val shouldStartBrightness = screen.dispatcherCode == 5L && activeBrightnessMode.value
                _startBrightnessMode.update { shouldStartBrightness }

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


    private fun registerConnectionSignal() {
        serverConnection.statusConnection.onEach { status ->
            appLogger.trackLog("Is Connected to terminal: ", "$status")
            if(status != _itemsState.value.statusConnection) {
                _itemsState.update { it.copy(statusConnection = status) }
            }
            if (!status){
                loadScreenInformation(
                    data = TerminalResponseModel(1005, null)
                )
            }else{
                loadScreenInformation(data = TerminalResponseModel(5, DefaultDits.idleConnected()))
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
            marginRight.dp, marginBottom.dp))
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

    private fun onRestartApp(){
        serverConnection.restartApp.onEach { value ->
            if (value){
                initAllConfig()
                serverConnection.setRestartApp(false)
            }
        }.launchIn(viewModelScope)
    }

    fun setBrightnessViaSettings(activity: Activity, brightness: Float) {
        viewModelScope.launch(Dispatchers.IO) {
            try {
                if (Settings.System.canWrite(activity)) {
                    val brightnessValue = (brightness * 255).toInt()

                    Settings.System.putInt(
                        activity.contentResolver,
                        Settings.System.SCREEN_BRIGHTNESS_MODE,
                        Settings.System.SCREEN_BRIGHTNESS_MODE_MANUAL
                    )

                    Settings.System.putInt(
                        activity.contentResolver,
                        Settings.System.SCREEN_BRIGHTNESS,
                        brightnessValue.coerceIn(0, 255)
                    )
                }
            } catch (e: Exception) {
                Log.e("Brightness", "Settings approach failed", e)
            }
        }
    }

    fun setBrightnessByCommand(activity: Activity, brightness: Int){
        brightnessManager = SystemBrightnessManager(activity)

        // Cambiar brillo a 150 (de 0-255)
        val success = brightnessManager.setBrightness(brightness)

        if (success) {
            println("Brillo ajustado exitosamente")
        } else {
            println("Error al ajustar brillo")
        }

        // Obtener brillo actual
        val currentBrightness = brightnessManager.getCurrentBrightness()
        println("Brillo actual: $currentBrightness")
    }
}
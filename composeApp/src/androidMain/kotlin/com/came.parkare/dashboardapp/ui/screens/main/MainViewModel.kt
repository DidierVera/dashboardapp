package com.came.parkare.dashboardapp.ui.screens.main

import android.app.Activity
import android.os.Environment
import android.provider.Settings
import android.util.Log
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.unit.dp
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.came.parkare.dashboardapp.config.DefaultDits
import com.came.parkare.dashboardapp.config.constants.Constants.AUTO_BRIGHTNESS
import com.came.parkare.dashboardapp.config.constants.Constants.AUTO_BRIGHTNESS_DELAY_TIME
import com.came.parkare.dashboardapp.config.constants.Constants.RESET_COUNTER_DELAY_TIME
import com.came.parkare.dashboardapp.config.constants.Constants.SHOW_COUNTER
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
import com.came.parkare.dashboardapp.domain.repositories.local.DashboardElementRepository
import com.came.parkare.dashboardapp.domain.usecases.GetScreenByDispatcher
import com.came.parkare.dashboardapp.domain.usecases.InitConfiguration
import com.came.parkare.dashboardapp.domain.usecases.StartSocketConnection
import com.came.parkare.dashboardapp.ui.components.carcounter.CarCounterManager
import com.came.parkare.dashboardapp.ui.utils.FilesUtils
import com.came.parkare.dashboardapp.ui.utils.FontViewModel
import com.came.parkare.dashboardapp.ui.utils.SystemBrightnessManager
import com.came.parkare.dashboardapp.ui.utils.UiUtils
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.flow.filter
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.isActive
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import org.koin.androidx.compose.koinViewModel
import java.io.File
import kotlin.coroutines.cancellation.CancellationException

class MainViewModel (
    private val initConfiguration: InitConfiguration,
    private val startParkingConnection: StartSocketConnection,
    private val getScreenByDispatcher: GetScreenByDispatcher,
    private val ditsUtils: UiUtils,
    private val dashboardElementRepository: DashboardElementRepository,
    private val preferences: SharedPreferencesProvider,
    private val appLogger: AppLogger,
    private val filesUtils: FilesUtils,
    private val carCounterManager: CarCounterManager,
    private val serverConnection: IServerConnection,
    private val fontViewModel: FontViewModel
): ViewModel() {
    private var isInitializing = false

    private val _isInitialized = MutableStateFlow(false)

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

    private val _showCarCounter = MutableStateFlow(false)
    val showCarCounter: StateFlow<Boolean>
        get() = _showCarCounter.asStateFlow()

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

        registerScreensListener()
        registerConnectionSignal()
        registerTerminalListener()
    }

    private fun initAllConfig() {
        if (isInitializing) return
        isInitializing = true
        serverConnection.setStatusConnection(true)
        reloadFont()
        checkVideoFrame()
        checkBackgroundImage()
        checkTextSizeScale()
        checkBrightnessMode()
        checkCarCounter()
        startPeriodicChecking()
        getAllDataFromServices()
        isInitializing = false
    }

    private fun reloadFont() {
        fontViewModel.reloadFont()
    }

    private fun checkCarCounter() {
        viewModelScope.launch {
            val showCounter  = preferences.get(SHOW_COUNTER, false)
            val resetDelay = preferences.get(RESET_COUNTER_DELAY_TIME, 1)

            val currentShow = carCounterManager.showCounter.value
            val currentDelay = carCounterManager.resetDelay.value

            if (currentDelay != resetDelay){
                carCounterManager.setResetDelay(resetDelay)
            }

            if (currentShow != showCounter){
                carCounterManager.showCarCounter(showCounter)
                _showCarCounter.update { showCounter }
            }
        }
    }

    private fun checkBrightnessMode() {
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
                delay(50000) // Check every 50 seconds
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
                    val data = result.data!!
                    appLogger.trackLog("TERMINAL_RESULT", "dispatcherCode=${data.dispatcherCode}, ditsTUI=${data.ditsTUI != null}")
                    val translations = _itemsState.value.translations
                    if (translations.isEmpty()) {
                        appLogger.trackLog("TERMINAL_RESULT", "translations empty, using default lang")
                        loadScreenInformation(data, "en")
                    } else {
                        loadScreenInformation(data, translations.first())
                    }
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
            // Skip file read if screens already loaded by splash
            val existingScreens = dashboardElementRepository.getAllScreens()
            if (existingScreens.isEmpty()) {
                //screens config initialization
                when (val initConfigResult = initConfiguration.invoke()){
                    is ServiceResult.Error -> {
                        validateError(initConfigResult.error)
                        return@launch
                    }
                    is ServiceResult.Success -> { /* continue */ }
                }
            } else {
                serverConnection.setScreensList(existingScreens)
            }
            _isInitialized.update { true }
        }
    }

    private fun loadLanguages(screenElements: List<ElementModel>) {
        val allKeys = collectTranslationKeys(screenElements)
        if (allKeys.isEmpty()) return
        _itemsState.update { state ->
            state.copy(
                currentLang = if (state.currentLang in allKeys) state.currentLang else allKeys.first(),
                translations = allKeys.toList()
            )
        }
    }

    private fun collectTranslationKeys(elements: List<ElementModel>): Set<String> {
        val keys = mutableSetOf<String>()
        for (element in elements) {
            when (element) {
                is ElementModel.TextModel -> {
                    element.data.translations?.keys?.let { keys.addAll(it) }
                }
                is ElementModel.BoxModel -> {
                    keys.addAll(collectTranslationKeys(element.data.content))
                }
                is ElementModel.ColumnModel -> {
                    keys.addAll(collectTranslationKeys(element.data.content))
                }
                is ElementModel.RowModel -> {
                    keys.addAll(collectTranslationKeys(element.data.content))
                }
                else -> { }
            }
        }
        return keys
    }

    private fun customLoop(): Job {
        return viewModelScope.launch {
            while (isActive) {
                try {
                    val delayTime = preferences.get(TIME_DELAY, 5)
                    val langs = itemsState.value.translations
                    if (langs.isEmpty()) return@launch
                    langs.forEach { lang ->
                        //appLogger.trackLog("LOAD_LANG", "Lang=$lang, lang list: ${langs.size}")

                        withContext(Dispatchers.Main) {
                            if (isActive) {
                                _itemsState.update { it.copy(currentLang = lang) }
                            }
                        }
                        rebuildLanguage(lang)
                        delay(delayTime.toLong() * 1000)
                    }
                } catch (e: CancellationException){
                    // normal cancellation
                } catch (e: Exception){
                    appLogger.trackError(e)
                }
            }
        }
    }

    private suspend fun rebuildLanguage(lang: String) {
        try {
            val buildElements = ditsUtils.buildDashboardItem(_itemsState.value.newItems, _itemsState.value.ditsUI, lang)
            _itemsState.update { it.copy(newItems = buildElements) }
        } catch (e: Exception){
            appLogger.trackError(e)
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
            //appLogger.trackLog("LOAD_SCREEN", "dispatcherCode=${data.dispatcherCode}, screen found: ${screen != null}, screenList size: ${_itemsState.value.screenList.size}")
            if (screen != null){
                loadLanguages(screen.elements)
                val shouldStartBrightness = screen.dispatcherCode == 5L && activeBrightnessMode.value
                _startBrightnessMode.update { shouldStartBrightness }

                checkContentMargin(screen.marginLeft, screen.marginTop, screen.marginRight, screen.marginBottom)
                _itemsState.update { it.copy(ditsUI = data.ditsTUI) }
                val buildElements = ditsUtils.buildDashboardItem(screen.elements, data.ditsTUI, lang)
                _itemsState.update { it.copy(newItems = buildElements) }

                if (loopJob == null || !loopJob!!.isActive) {
                    loopJob = customLoop()
                }

                //count new entry when is dispatched 12 code
                if (screen.dispatcherCode == 12L){
                    carCounterManager.newCarEntered()
                }
            }
        }
    }

    private fun registerConnectionSignal() {
        combine(
            serverConnection.statusConnection,
            _isInitialized
        ) { status, isInitialized ->
            Pair(status, isInitialized)
        }
            .filter { (_, isInitialized) -> isInitialized }
            .distinctUntilChanged()
            .onEach { (status, _) ->
                appLogger.trackLog("Is Connected to terminal: ", "$status")
                if (status != _itemsState.value.statusConnection) {
                    _itemsState.update { it.copy(statusConnection = status) }
                }
                if (!status) {
                    appLogger.trackLog("RECOMPOSICIÓN", "RECOMPOSICIÓN POR PERDIDA DE CONEXIÓN------")
                    loadScreenInformation(data = TerminalResponseModel(1005L, null))
                } else {
                    appLogger.trackLog("RECOMPOSICIÓN","RECOMPOSICIÓN POR RECUPERAR LA CONEXIÓN+++++")
                    loadScreenInformation(data = TerminalResponseModel(5L, DefaultDits.idleConnected()))
                }
            }
            .launchIn(viewModelScope)
    }

    fun getTranslationText(lang: String){
        viewModelScope.launch {
            try {
                val buildElements = ditsUtils.buildDashboardItem(_itemsState.value.newItems, _itemsState.value.ditsUI, lang)
                if (_itemsState.value.currentLang == lang) {
                    _itemsState.update { it.copy(newItems = buildElements) }
                }
            }catch (e: Exception){
                appLogger.trackError(e)
            }
        }
    }

    private fun checkContentMargin(marginLeft: Int, marginTop: Int, marginRight: Int, marginBottom: Int) {
        val scaleFactor = (_itemsState.value.textSizeScale / 10f).coerceIn(0.5f, 3f)
        _itemsState.update { it.copy(contentPadding = PaddingValues(
            (marginLeft.toFloat() * scaleFactor).dp, (marginTop.toFloat() * scaleFactor).dp,
            (marginRight.toFloat() * scaleFactor).dp, (marginBottom.toFloat() * scaleFactor).dp))
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

    // *This method permit to refresh the screen each change from
    // *The web configurator
    // *On each change, the restarApp value change to true, and is why call initAllConfig
    private fun onRestartApp(){
        serverConnection.restartApp.onEach { value ->
            if (value){
                initAllConfig()
                serverConnection.setRestartApp(false)
            }
        }.launchIn(viewModelScope)
    }
}
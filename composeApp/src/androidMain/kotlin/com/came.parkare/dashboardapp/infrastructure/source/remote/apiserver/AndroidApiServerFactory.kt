package com.came.parkare.dashboardapp.infrastructure.source.remote.apiserver

import com.came.parkare.dashboardapp.config.utils.AppLogger
import com.came.parkare.dashboardapp.config.utils.DeviceUtils
import com.came.parkare.dashboardapp.config.utils.SharedPreferencesProvider
import com.came.parkare.dashboardapp.domain.repositories.local.ConfigTemplateRepository
import com.came.parkare.dashboardapp.domain.repositories.remote.ApiServerRepository
import com.came.parkare.dashboardapp.infrastructure.source.remote.services.MockService

/**
 * NanoHTTPD binds its port in the constructor, so a server on another port (the fallback
 * when the configured one is busy) needs a new instance.
 */
class AndroidApiServerFactory(
    private val preferences: SharedPreferencesProvider,
    private val apiServerRepository: ApiServerRepository,
    private val configTemplateRepository: ConfigTemplateRepository,
    private val appLogger: AppLogger,
    private val deviceUtils: DeviceUtils,
    private val mockService: MockService
) {
    fun create(port: Int): AndroidApiServer = AndroidApiServer(
        preferences = preferences,
        apiServerRepository = apiServerRepository,
        configTemplateRepository = configTemplateRepository,
        appLogger = appLogger,
        deviceUtils = deviceUtils,
        mockService = mockService,
        port = port
    )
}

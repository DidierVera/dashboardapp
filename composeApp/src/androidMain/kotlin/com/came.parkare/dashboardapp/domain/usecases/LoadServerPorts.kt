package com.came.parkare.dashboardapp.domain.usecases

import com.came.parkare.dashboardapp.config.constants.Constants.API_PORT
import com.came.parkare.dashboardapp.config.constants.Constants.FTP_APP_PORT
import com.came.parkare.dashboardapp.config.constants.Constants.FTP_DEVICE_PORT
import com.came.parkare.dashboardapp.config.constants.Constants.WEB_PORT
import com.came.parkare.dashboardapp.config.constants.DefaultPorts
import com.came.parkare.dashboardapp.config.dataclasses.ServiceResult
import com.came.parkare.dashboardapp.config.utils.AppLogger
import com.came.parkare.dashboardapp.config.utils.ResolvedServerPorts
import com.came.parkare.dashboardapp.config.utils.ServerPorts
import com.came.parkare.dashboardapp.config.utils.ServerPortsResolver
import com.came.parkare.dashboardapp.config.utils.SharedPreferencesProvider
import com.came.parkare.dashboardapp.domain.repositories.external.ConfigFileRepository
import com.came.parkare.dashboardapp.domain.repositories.external.FtpServerFileRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

/**
 * Reads the ports of the local servers from ftp_connection.json and connection_config.json
 * before the servers start, so a change in those files is applied on the next start of the app.
 * If a file can not be read, the ports stored from the last good read (or the defaults) are used.
 */
class LoadServerPorts(
    private val ftpServerFileRepository: FtpServerFileRepository,
    private val configFileRepository: ConfigFileRepository,
    private val preferences: SharedPreferencesProvider,
    private val appLogger: AppLogger
) {
    suspend fun invoke(): ResolvedServerPorts = withContext(Dispatchers.IO) {
        try {
            val ftpResult = ftpServerFileRepository.readFtpServerConfiguration()
            if (ftpResult is ServiceResult.Error) {
                appLogger.trackLog(TAG, "ftp_connection.json could not be read: ${ftpResult.error}")
            }
        } catch (e: Exception) {
            appLogger.trackError(e)
        }

        try {
            val connectionResult = configFileRepository.readServerPorts()
            if (connectionResult is ServiceResult.Error) {
                appLogger.trackLog(TAG, "connection_config.json could not be read: ${connectionResult.error}")
            }
        } catch (e: Exception) {
            appLogger.trackError(e)
        }

        val configured = ServerPorts(
            web = preferences.get(WEB_PORT, DefaultPorts.WEB),
            api = preferences.get(API_PORT, DefaultPorts.API),
            ftpDevice = preferences.get(FTP_DEVICE_PORT, DefaultPorts.FTP_DEVICE),
            ftpApp = preferences.get(FTP_APP_PORT, DefaultPorts.FTP_APP)
        )
        val resolved = ServerPortsResolver.resolve(configured)
        appLogger.trackLog(TAG, "configured=$configured resolved=${resolved.ports}")
        resolved.warnings.forEach { appLogger.trackLog(TAG, it) }
        resolved
    }

    private companion object {
        const val TAG = "SERVER_PORTS"
    }
}

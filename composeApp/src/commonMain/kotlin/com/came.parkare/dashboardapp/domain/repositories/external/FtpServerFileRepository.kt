package com.came.parkare.dashboardapp.domain.repositories.external

import com.came.parkare.dashboardapp.config.dataclasses.ServiceResult
import com.came.parkare.dashboardapp.infrastructure.source.external.dto.ftp.FtpServerConnectionDto

interface FtpServerFileRepository {
    suspend fun readFtpServerConfiguration(): ServiceResult<Boolean>
    suspend fun writeFtpServerConfiguration(newData: FtpServerConnectionDto): ServiceResult<Boolean>
}
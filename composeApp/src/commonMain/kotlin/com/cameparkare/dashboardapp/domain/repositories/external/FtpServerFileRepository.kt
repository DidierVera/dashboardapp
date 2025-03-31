package com.cameparkare.dashboardapp.domain.repositories.external

import com.cameparkare.dashboardapp.config.dataclasses.ServiceResult
import com.cameparkare.dashboardapp.infrastructure.source.external.dto.FtpServerConnectionDto

interface FtpServerFileRepository {
    suspend fun readFtpServerConfiguration(): ServiceResult<Boolean>
    suspend fun writeFtpServerConfiguration(newData: FtpServerConnectionDto): ServiceResult<Boolean>
}
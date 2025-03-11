package com.cameparkare.dashboardapp.domain.repositories.external

import com.cameparkare.dashboardapp.config.dataclasses.ServiceResult

interface FtpServerFileRepository {
    suspend fun readFtpServerConfiguration(): ServiceResult<Boolean>
}
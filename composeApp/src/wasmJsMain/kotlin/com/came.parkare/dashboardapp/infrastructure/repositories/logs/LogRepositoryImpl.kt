package com.came.parkare.dashboardapp.infrastructure.repositories.logs

import com.came.parkare.dashboardapp.config.dataclasses.ResponseStatusDto
import com.came.parkare.dashboardapp.domain.repositories.log.LogRepository
import com.came.parkare.dashboardapp.infrastructure.source.external.dto.logs.TrackErrorDto
import com.came.parkare.dashboardapp.infrastructure.source.external.dto.logs.TrackLogDto
import com.came.parkare.dashboardapp.infrastructure.source.services.base.HttpClient

class LogRepositoryImpl(
    private val httpClient: HttpClient
): LogRepository {
    override suspend fun trackError(dto: TrackErrorDto): ResponseStatusDto {
        TODO("Not yet implemented")
    }

    override suspend fun trackLog(dto: TrackLogDto): ResponseStatusDto {
        TODO("Not yet implemented")
    }

}
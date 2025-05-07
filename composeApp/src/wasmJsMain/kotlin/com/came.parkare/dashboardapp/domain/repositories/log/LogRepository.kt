package com.came.parkare.dashboardapp.domain.repositories.log

import com.came.parkare.dashboardapp.config.dataclasses.ResponseStatusDto
import com.came.parkare.dashboardapp.infrastructure.source.external.dto.logs.TrackErrorDto
import com.came.parkare.dashboardapp.infrastructure.source.external.dto.logs.TrackLogDto

interface LogRepository {
    suspend fun trackError(dto: TrackErrorDto): ResponseStatusDto
    suspend fun trackLog(dto: TrackLogDto): ResponseStatusDto
}
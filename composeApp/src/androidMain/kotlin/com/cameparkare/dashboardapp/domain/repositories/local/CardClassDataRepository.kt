package com.cameparkare.dashboardapp.domain.repositories.local

import com.cameparkare.dashboardapp.infrastructure.source.local.entities.CardClassEntity

interface CardClassDataRepository: BaseDataRepository<CardClassEntity> {
    suspend fun selectByElementCode(code: Int): List<CardClassEntity>
}
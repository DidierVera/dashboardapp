package com.cameparkare.dashboardapp.domain.usecases

import com.cameparkare.dashboardapp.config.dataclasses.ServiceResult
import com.cameparkare.dashboardapp.domain.models.CardClassModel

interface GetCardClassTranslations {
    suspend fun invoke(code: Int, lang: String): ServiceResult<CardClassModel?>
}
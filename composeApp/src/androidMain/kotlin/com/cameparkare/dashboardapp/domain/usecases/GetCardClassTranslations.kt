package com.cameparkare.dashboardapp.domain.usecases

import com.cameparkare.dashboardapp.config.dataclasses.ErrorTypeClass
import com.cameparkare.dashboardapp.config.dataclasses.ServiceResult
import com.cameparkare.dashboardapp.config.utils.AppLogger
import com.cameparkare.dashboardapp.domain.models.CardClassModel
import com.cameparkare.dashboardapp.domain.repositories.local.CardClassDataRepository
import com.cameparkare.dashboardapp.infrastructure.source.local.entities.toModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class GetCardClassTranslations @Inject constructor(
    private val cardClassDataRepository: CardClassDataRepository,
    private val appLogger: AppLogger
) {
    suspend fun invoke(code: Int, lang: String): ServiceResult<CardClassModel?> {
        return try {
            val result = withContext(Dispatchers.IO) {
                cardClassDataRepository.selectByElementCode(
                    code
                )
            }
            val translations = result.firstOrNull{it.languageCode.lowercase() == lang.lowercase()}
                ?: return  ServiceResult.Success(result.first { it.languageCode == "default" }.toModel())
            return ServiceResult.Success(translations.toModel())
        }catch (e: Exception){
            appLogger.trackError(e)
            ServiceResult.Error(ErrorTypeClass.GeneralException())
        }
    }
}
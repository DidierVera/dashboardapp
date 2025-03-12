package com.cameparkare.dashboardapp.infrastructure.repositories.local

import com.cameparkare.dashboardapp.config.utils.AppLogger
import com.cameparkare.dashboardapp.domain.models.ScreenModel
import com.cameparkare.dashboardapp.domain.models.components.ElementModel
import com.cameparkare.dashboardapp.domain.repositories.local.DashboardElementRepository
import com.cameparkare.dashboardapp.infrastructure.source.local.dao.ElementDao
import com.cameparkare.dashboardapp.infrastructure.source.local.dao.ScreenDao
import com.cameparkare.dashboardapp.infrastructure.source.local.entities.mapElementsModelToEntity
import com.cameparkare.dashboardapp.infrastructure.source.local.entities.toEntity
import com.cameparkare.dashboardapp.infrastructure.source.local.entities.toModel

class DashboardElementRepositoryImpl(
    private val elementDao: ElementDao,
    private val screenDao: ScreenDao,
    private val appLogger: AppLogger

): DashboardElementRepository {
    override suspend fun saveElements(items: List<ElementModel>) {
        try {
            val entities = mapElementsModelToEntity(items)
            for (element in entities){
                elementDao.insertElement(element)
            }
        }catch (e: Exception){
            appLogger.trackError(e)
        }
    }

    override suspend fun saveScreens(items: List<ScreenModel>) {
        try {
            for (screen in items){
                screenDao.insertScreen(screen.toEntity())
            }
        }catch (e: Exception){
            appLogger.trackError(e)
        }
    }

    override suspend fun getScreenByDispatcher(dispatcherCode: Long): ScreenModel? {
        return try {
            val entity = screenDao.getScreenByDispatcher(dispatcherCode)
            entity?.toModel()
        }catch (e: Exception){
            appLogger.trackError(e)
            null
        }
    }

    override suspend fun getAllScreens(): List<ScreenModel> {
        return try {
            val entities = screenDao.getAll()
            entities.map { it.toModel() }
        }catch (e: Exception){
            appLogger.trackError(e)
            emptyList()
        }
    }
}
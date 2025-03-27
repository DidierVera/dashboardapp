package com.cameparkare.dashboardapp.config.database

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.cameparkare.dashboardapp.infrastructure.source.local.dao.ImagesDao
import com.cameparkare.dashboardapp.infrastructure.source.local.dao.ScreenDao
import com.cameparkare.dashboardapp.infrastructure.source.local.entities.ImagesFilesEntity
import com.cameparkare.dashboardapp.infrastructure.source.local.entities.ScreenEntity
import com.cameparkare.dashboardapp.infrastructure.source.local.entities.config.ElementListConverter
import com.cameparkare.dashboardapp.infrastructure.source.local.entities.config.MapConverter


// Room database
@Database(
    entities = [
        ScreenEntity::class,
        ImagesFilesEntity::class
    ],
    version = 3
)
@TypeConverters(ElementListConverter::class, MapConverter::class)
abstract class AppDatabase : RoomDatabase() {
    abstract fun screenDao(): ScreenDao
    abstract fun imagesDao(): ImagesDao
}
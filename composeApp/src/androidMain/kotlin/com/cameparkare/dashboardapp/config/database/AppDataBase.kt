package com.cameparkare.dashboardapp.config.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.cameparkare.dashboardapp.infrastructure.source.local.dao.ElementDao
import com.cameparkare.dashboardapp.infrastructure.source.local.dao.ScreenDao
import com.cameparkare.dashboardapp.infrastructure.source.local.entities.ElementEntity
import com.cameparkare.dashboardapp.infrastructure.source.local.entities.ScreenEntity
import com.cameparkare.dashboardapp.infrastructure.source.local.entities.config.ElementListConverter
import com.cameparkare.dashboardapp.infrastructure.source.local.entities.config.MapConverter


// Room database
@Database(
    entities = [
        ScreenEntity::class,
        ElementEntity::class,
        ElementEntity.BoxEntity::class,
        ElementEntity.SpacerEntity::class,
        ElementEntity.ColumnEntity::class,
        ElementEntity.TextEntity::class,
        ElementEntity.RowEntity::class,
        ElementEntity.ImageEntity::class,
        ElementEntity.VideoEntity::class
    ],
    version = 2
)
@TypeConverters(ElementListConverter::class, MapConverter::class)
abstract class AppDatabase : RoomDatabase() {
    abstract fun screenDao(): ScreenDao
    abstract fun elementDao(): ElementDao
}
package com.cameparkare.dashboardapp.config.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.cameparkare.dashboardapp.infrastructure.source.local.dao.ElementDao
import com.cameparkare.dashboardapp.infrastructure.source.local.entities.ElementEntity
import com.cameparkare.dashboardapp.infrastructure.source.local.entities.ScreenEntity
import com.cameparkare.dashboardapp.infrastructure.source.local.entities.TranslationEntity


// Room database
@Database(
    entities = [ScreenEntity::class, ElementEntity::class, TranslationEntity::class],
    version = 1
)
abstract class AppDatabase : RoomDatabase() {
    abstract fun elementDao(): ElementDao

    companion object {
        @Volatile
        private var INSTANCE: AppDatabase? = null

        fun getDatabase(context: Context): AppDatabase {
            synchronized(this) {
                return INSTANCE ?: Room.databaseBuilder(
                    context.applicationContext,
                    AppDatabase::class.java,
                    "app_database"
                ).build().also { INSTANCE = it }
            }
        }
    }
}
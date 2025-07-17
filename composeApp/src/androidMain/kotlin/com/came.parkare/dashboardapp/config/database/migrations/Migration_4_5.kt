package com.came.parkare.dashboardapp.config.database.migrations

import androidx.room.migration.Migration
import androidx.sqlite.db.SupportSQLiteDatabase
import com.came.parkare.dashboardapp.infrastructure.source.local.entities.config.RoomTableNames.CONFIG_TEMPLATE_TABLE_NAME

object Migration_4_5: Migration(4, 5) {
    override fun migrate(database: SupportSQLiteDatabase) {
        // Create new config template table
        database.execSQL("""
            CREATE TABLE IF NOT EXISTS $CONFIG_TEMPLATE_TABLE_NAME (
                id INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL,
                templateName TEXT  NOT NULL,
                screens TEXT  NOT NULL
            )
        """)
    }
}
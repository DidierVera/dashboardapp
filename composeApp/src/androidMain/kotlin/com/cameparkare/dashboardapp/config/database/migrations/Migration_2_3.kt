package com.cameparkare.dashboardapp.config.database.migrations

import androidx.room.migration.Migration
import androidx.sqlite.db.SupportSQLiteDatabase
import com.cameparkare.dashboardapp.infrastructure.source.local.entities.config.RoomTableNames.SCREEN_TABLE_NAME

object Migration_2_3 : Migration(2, 3) {
    override fun migrate(database: SupportSQLiteDatabase) {
        // Create new images table
        database.execSQL("""
            CREATE TABLE IF NOT EXISTS tbl_images (
                id INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL,
                fileName TEXT,
                fileContent TEXT
            )
        """)

        // Add new margin columns to ScreenEntity table
        database.execSQL("ALTER TABLE ${SCREEN_TABLE_NAME} ADD COLUMN marginTop INTEGER NOT NULL DEFAULT 0")
        database.execSQL("ALTER TABLE ${SCREEN_TABLE_NAME} ADD COLUMN marginBottom INTEGER NOT NULL DEFAULT 0")
        database.execSQL("ALTER TABLE ${SCREEN_TABLE_NAME} ADD COLUMN marginLeft INTEGER NOT NULL DEFAULT 0")
        database.execSQL("ALTER TABLE ${SCREEN_TABLE_NAME} ADD COLUMN marginRight INTEGER NOT NULL DEFAULT 0")
    }
}
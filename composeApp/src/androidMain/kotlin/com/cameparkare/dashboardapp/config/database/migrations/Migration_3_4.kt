package com.cameparkare.dashboardapp.config.database.migrations

import androidx.room.migration.Migration
import androidx.sqlite.db.SupportSQLiteDatabase

object Migration_3_4: Migration(3, 4) {
    override fun migrate(database: SupportSQLiteDatabase) {
        // Create new dashboard device table
        database.execSQL("""
            CREATE TABLE IF NOT EXISTS tbl_dashboard_device (
                id INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL,
                deviceIp TEXT  NOT NULL,
                customName TEXT  NOT NULL,
                terminalIp TEXT
            )
        """)
    }
}
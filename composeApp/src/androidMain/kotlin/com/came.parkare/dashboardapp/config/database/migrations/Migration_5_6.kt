package com.came.parkare.dashboardapp.config.database.migrations

import androidx.room.migration.Migration
import androidx.sqlite.db.SupportSQLiteDatabase
import com.came.parkare.dashboardapp.infrastructure.source.local.entities.config.RoomTableNames.SCREEN_TABLE_NAME

object Migration_5_6 : Migration(5, 6) {
    override fun migrate(database: SupportSQLiteDatabase) {
        database.execSQL("""
            CREATE TABLE IF NOT EXISTS `screen_temp` (
                `id` INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL,
                `screenId` TEXT NOT NULL,
                `dispatcherCode` INTEGER NOT NULL,
                `marginTop` INTEGER NOT NULL DEFAULT 0,
                `marginBottom` INTEGER NOT NULL DEFAULT 0,
                `marginLeft` INTEGER NOT NULL DEFAULT 0,
                `marginRight` INTEGER NOT NULL DEFAULT 0,
                `dateOfCreation` INTEGER,
                `lastTimeUpdated` INTEGER,
                `elements` TEXT NOT NULL
            )
        """)

        database.execSQL("""
            INSERT INTO `screen_temp` (id, screenId, dispatcherCode, marginTop, marginBottom, marginLeft, marginRight, elements)
            SELECT id, screenId, dispatcherCode, marginTop, marginBottom, marginLeft, marginRight, elements
            FROM `$SCREEN_TABLE_NAME`
        """)

        database.execSQL("DROP TABLE `$SCREEN_TABLE_NAME`")

        database.execSQL("ALTER TABLE `screen_temp` RENAME TO `$SCREEN_TABLE_NAME`")
    }
}
package com.came.parkare.dashboardapp.config.database.migrations

import androidx.room.migration.Migration
import androidx.sqlite.db.SupportSQLiteDatabase
import com.came.parkare.dashboardapp.infrastructure.source.local.entities.config.RoomTableNames
import com.came.parkare.dashboardapp.infrastructure.source.local.entities.config.RoomTableNames.CARD_CLASS_TABLE_NAME
import com.came.parkare.dashboardapp.infrastructure.source.local.entities.config.RoomTableNames.IMAGES_TABLE_NAME
import com.came.parkare.dashboardapp.infrastructure.source.local.entities.config.RoomTableNames.LANGUAGE_TABLE_NAME
import com.came.parkare.dashboardapp.infrastructure.source.local.entities.config.RoomTableNames.SCREEN_TABLE_NAME

object Migration_2_3 : Migration(2, 3) {
    override fun migrate(database: SupportSQLiteDatabase) {
        // Step 1: Create new tables
        database.execSQL("""
            CREATE TABLE IF NOT EXISTS `${RoomTableNames.DASHBOARD_DEVICE_TABLE_NAME}` (
                `id` INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL,
                `deviceIp` TEXT NOT NULL,
                `customName` TEXT NOT NULL,
                `terminalIp` TEXT
            )
        """)

        database.execSQL("""
            CREATE TABLE IF NOT EXISTS `$IMAGES_TABLE_NAME` (
                `id` INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL,
                `fileName` TEXT,
                `fileContent` TEXT
            )
        """)
// Step 2: Modify the existing screen table
        // First create a temporary table to hold the old screen data
        database.execSQL("""
            CREATE TABLE IF NOT EXISTS `screen_temp` (
                `id` INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL,
                `screenId` TEXT NOT NULL,
                `dispatcherCode` INTEGER NOT NULL,
                `marginTop` INTEGER NOT NULL,
                `marginBottom` INTEGER NOT NULL,
                `marginLeft` INTEGER NOT NULL,
                `marginRight` INTEGER NOT NULL,
                `elements` TEXT NOT NULL
            )
        """)

        // Copy data from old screen table to temp table, converting fields
        database.execSQL("""
            INSERT INTO `screen_temp` (id, screenId, dispatcherCode, marginTop, marginBottom, marginLeft, marginRight, elements)
            SELECT localId, screenId, dispatchCode, 0, 0, 0, 0, '[]' 
            FROM `$SCREEN_TABLE_NAME`
        """)

        // Drop the old screen table
        database.execSQL("DROP TABLE `$SCREEN_TABLE_NAME`")

        // Rename temp table to original name
        database.execSQL("ALTER TABLE `screen_temp` RENAME TO `$SCREEN_TABLE_NAME`")

        // Step 3: Drop old tables that are no longer needed
        database.execSQL("DROP TABLE IF EXISTS `$LANGUAGE_TABLE_NAME`")
        database.execSQL("DROP TABLE IF EXISTS `$CARD_CLASS_TABLE_NAME`")
    }
}
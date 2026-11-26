package com.came.parkare.dashboardapp.config.database.migrations

import androidx.room.migration.Migration
import androidx.sqlite.db.SupportSQLiteDatabase
import com.came.parkare.dashboardapp.infrastructure.source.local.entities.config.RoomTableNames.SCREEN_TABLE_NAME

/**
 * Adds the position of the content of each screen. The existing screens get null, which is the
 * default alignment (center).
 */
object Migration_6_7 : Migration(6, 7) {
    override fun migrate(database: SupportSQLiteDatabase) {
        database.execSQL("ALTER TABLE `$SCREEN_TABLE_NAME` ADD COLUMN `contentAlignment` TEXT")
    }
}

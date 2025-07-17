package com.came.parkare.dashboardapp.infrastructure.source.local.entities

import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.TypeConverters
import com.came.parkare.dashboardapp.domain.models.ConfigTemplateModel
import com.came.parkare.dashboardapp.infrastructure.source.local.entities.config.RoomTableNames.CONFIG_TEMPLATE_TABLE_NAME
import com.came.parkare.dashboardapp.infrastructure.source.local.entities.config.ScreenListConverter

@Entity(tableName = CONFIG_TEMPLATE_TABLE_NAME)
data class ConfigTemplateEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Long = 0,
    val templateName: String,
    @TypeConverters(ScreenListConverter::class)
    val screens: List<ScreenEntity>
)

fun ConfigTemplateEntity.toModel(): ConfigTemplateModel {
    return ConfigTemplateModel(
        id = id,
        templateName = templateName,
        screens = screens.map { it.toModel() }
    )
}

fun ConfigTemplateModel.toEntity(): ConfigTemplateEntity  {
    return ConfigTemplateEntity(
        id = id,
        templateName = templateName,
        screens = screens.map { it.toEntity() }
    )
}
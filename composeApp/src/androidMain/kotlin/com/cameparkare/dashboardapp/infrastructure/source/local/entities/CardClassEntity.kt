package com.cameparkare.dashboardapp.infrastructure.source.local.entities

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.cameparkare.dashboardapp.domain.models.CardClassModel
import com.cameparkare.dashboardapp.infrastructure.source.local.entities.config.RoomTableNames.CARD_CLASS_TABLE_NAME

@Entity(tableName = CARD_CLASS_TABLE_NAME)
data class CardClassEntity(
    @PrimaryKey(autoGenerate = true)
    override val localId: Long = 0,
    val elementCode: Int,
    val languageCode: String,
    val translation: String
): BaseEntity()

fun CardClassEntity.toModel(): CardClassModel {
    return CardClassModel(
        languageCode = languageCode,
        translation = translation
    )
}
package com.cameparkare.dashboardapp.infrastructure.source.local.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.cameparkare.dashboardapp.infrastructure.source.local.entities.config.RoomTableNames.ELEMENTS_TABLE_NAME

@Entity(tableName = ELEMENTS_TABLE_NAME)
data class ElementEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    @ColumnInfo(name = "screen_id")
    val screenId: Int,
    @ColumnInfo(name = "parent_id")
    val parentId: Int? = null,
    @ColumnInfo(name = "element_type")
    val elementType: String,
    @ColumnInfo(name = "data", typeAffinity = ColumnInfo.BLOB)
    val data: ByteArray
) {
    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as ElementEntity

        return data.contentEquals(other.data)
    }

    override fun hashCode(): Int {
        return data.contentHashCode()
    }
}

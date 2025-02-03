package com.cameparkare.dashboardapp.infrastructure.source.local.dao

import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.RawQuery
import androidx.room.RoomDatabase
import androidx.room.Update
import androidx.sqlite.db.SimpleSQLiteQuery
import androidx.sqlite.db.SupportSQLiteQuery
import com.cameparkare.dashboardapp.infrastructure.source.local.entities.BaseEntity


abstract class BaseDao<T: BaseEntity>(
    private val tableName: String,
    private val roomDatabase: RoomDatabase
) {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    abstract suspend fun insert(entity: T): Long

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    abstract suspend fun insertAll(entities: List<T>)

    @Update
    abstract suspend fun update(entity: T)

    @Delete
    abstract fun delete(entity: T)

    @RawQuery
    protected abstract suspend fun deleteAllEntities(query: SupportSQLiteQuery) : Long?
    @RawQuery
    protected abstract suspend fun getEntitySync(query: SupportSQLiteQuery): List<T>?

    suspend fun deleteAll(): Long? {
        val query = SimpleSQLiteQuery("DELETE FROM $tableName; SELECT ROW_COUNT() AS rows_affected;")
        return deleteAllEntities(query)
    }
    suspend fun getAll(): List<T>?{
        val query = SimpleSQLiteQuery("SELECT * FROM $tableName;")
        return getEntitySync(query)
    }

    suspend fun getEntityById(id: Long): T?{
        val query = SimpleSQLiteQuery("SELECT * FROM $tableName WHERE localId = $id;")
        return getEntitySync(query)?.first()
    }
}
package com.cameparkare.dashboardapp.domain.repositories.local

interface BaseDataRepository <T> {
    suspend fun insert(entity: T)
    suspend fun update(entity: T)
    suspend fun delete(entity: T)
    suspend fun deleteAll(): Long?
    suspend fun selectById(id: Long): T
    suspend fun selectAll(): List<T>
    suspend fun insertAll(data: List<T>)
}
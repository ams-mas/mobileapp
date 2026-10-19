package com.example.mobileapp.data.repository

import com.example.mobileapp.data.local.PropertyDao
import com.example.mobileapp.data.local.entity.PropertyEntity
import kotlinx.coroutines.flow.Flow

/**
 * Local-first property access. New edits are marked pending until a future
 * authenticated PHP API sync confirms they were saved on the server.
 */
class PropertyRepository(private val propertyDao: PropertyDao) {
    fun observeProperties(): Flow<List<PropertyEntity>> = propertyDao.observeAll()

    fun observeProperty(localId: Long): Flow<PropertyEntity?> =
        propertyDao.observeByLocalId(localId)

    fun searchProperties(query: String): Flow<List<PropertyEntity>> =
        propertyDao.search(query.trim())

    fun observeAvailableProperties(): Flow<List<PropertyEntity>> =
        propertyDao.observeByStatus("available")

    suspend fun saveLocally(property: PropertyEntity): Long =
        propertyDao.upsert(
            property.copy(
                propertyCode = property.propertyCode, // server-generated only
                updatedAtEpochMillis = System.currentTimeMillis(),
                syncState = "pending"
            )
        )

    suspend fun removeLocalProperty(localId: Long) {
        propertyDao.deleteByLocalId(localId)
    }

    suspend fun pendingSync(): List<PropertyEntity> = propertyDao.getPendingSync()
}

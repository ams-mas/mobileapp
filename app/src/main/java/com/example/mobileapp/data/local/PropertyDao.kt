package com.example.mobileapp.data.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.example.mobileapp.data.local.entity.PropertyEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface PropertyDao {
    @Query("SELECT * FROM properties ORDER BY updatedAtEpochMillis DESC")
    fun observeAll(): Flow<List<PropertyEntity>>

    @Query("SELECT * FROM properties WHERE localId = :localId LIMIT 1")
    fun observeByLocalId(localId: Long): Flow<PropertyEntity?>

    @Query("SELECT * FROM properties WHERE propertyCode LIKE '%' || :query || '%' OR title LIKE '%' || :query || '%' OR province LIKE '%' || :query || '%' ORDER BY updatedAtEpochMillis DESC")
    fun search(query: String): Flow<List<PropertyEntity>>

    @Query("SELECT * FROM properties WHERE status = :status ORDER BY updatedAtEpochMillis DESC")
    fun observeByStatus(status: String): Flow<List<PropertyEntity>>

    @Query("SELECT * FROM properties WHERE syncState != 'synced'")
    suspend fun getPendingSync(): List<PropertyEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun upsert(property: PropertyEntity): Long

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun upsertAll(properties: List<PropertyEntity>)

    @Update
    suspend fun update(property: PropertyEntity)

    @Query("DELETE FROM properties WHERE localId = :localId")
    suspend fun deleteByLocalId(localId: Long)

    @Query("SELECT COUNT(*) FROM properties")
    fun observeCount(): Flow<Int>
}

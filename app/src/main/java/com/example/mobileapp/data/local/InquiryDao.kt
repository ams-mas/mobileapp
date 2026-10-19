package com.example.mobileapp.data.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.example.mobileapp.data.local.entity.InquiryEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface InquiryDao {
    @Query("SELECT * FROM inquiries ORDER BY createdAtEpochMillis DESC")
    fun observeAll(): Flow<List<InquiryEntity>>

    @Query("SELECT * FROM inquiries WHERE status = :status ORDER BY createdAtEpochMillis DESC")
    fun observeByStatus(status: String): Flow<List<InquiryEntity>>

    @Query("SELECT * FROM inquiries WHERE syncState != 'synced'")
    suspend fun getPendingSync(): List<InquiryEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun upsert(inquiry: InquiryEntity): Long

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun upsertAll(inquiries: List<InquiryEntity>)

    @Update
    suspend fun update(inquiry: InquiryEntity)

    @Query("DELETE FROM inquiries WHERE localId = :localId")
    suspend fun deleteByLocalId(localId: Long)
}

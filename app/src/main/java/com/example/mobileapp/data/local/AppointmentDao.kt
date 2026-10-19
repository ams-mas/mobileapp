package com.example.mobileapp.data.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.example.mobileapp.data.local.entity.AppointmentEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface AppointmentDao {
    @Query("SELECT * FROM appointments ORDER BY scheduledAtEpochMillis")
    fun observeAll(): Flow<List<AppointmentEntity>>

    @Query("SELECT * FROM appointments WHERE scheduledAtEpochMillis >= :fromEpochMillis ORDER BY scheduledAtEpochMillis")
    fun observeUpcoming(fromEpochMillis: Long): Flow<List<AppointmentEntity>>

    @Query("SELECT * FROM appointments WHERE syncState != 'synced'")
    suspend fun getPendingSync(): List<AppointmentEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun upsert(appointment: AppointmentEntity): Long

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun upsertAll(appointments: List<AppointmentEntity>)

    @Update
    suspend fun update(appointment: AppointmentEntity)

    @Query("DELETE FROM appointments WHERE localId = :localId")
    suspend fun deleteByLocalId(localId: Long)
}

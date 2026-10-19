package com.example.mobileapp.data.local.entity

import androidx.room.Entity
import androidx.room.Index
import androidx.room.PrimaryKey

@Entity(
    tableName = "appointments",
    indices = [Index(value = ["serverId"], unique = true), Index(value = ["scheduledAtEpochMillis"])]
)
data class AppointmentEntity(
    @PrimaryKey(autoGenerate = true) val localId: Long = 0,
    val serverId: Long? = null,
    val customerLocalId: Long? = null,
    val propertyLocalId: Long? = null,
    val scheduledAtEpochMillis: Long,
    val notes: String = "",
    val status: String = "scheduled",
    val updatedAtEpochMillis: Long = System.currentTimeMillis(),
    val syncState: String = "pending"
)

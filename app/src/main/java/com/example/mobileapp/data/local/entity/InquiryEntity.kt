package com.example.mobileapp.data.local.entity

import androidx.room.Entity
import androidx.room.Index
import androidx.room.PrimaryKey

@Entity(
    tableName = "inquiries",
    indices = [Index(value = ["serverId"], unique = true), Index(value = ["customerLocalId"]), Index(value = ["propertyLocalId"])]
)
data class InquiryEntity(
    @PrimaryKey(autoGenerate = true) val localId: Long = 0,
    val serverId: Long? = null,
    val customerLocalId: Long? = null,
    val propertyLocalId: Long? = null,
    val inquiryType: String = "rent", // sale, rent, general
    val message: String = "",
    val status: String = "new",
    val createdAtEpochMillis: Long = System.currentTimeMillis(),
    val updatedAtEpochMillis: Long = System.currentTimeMillis(),
    val syncState: String = "pending"
)

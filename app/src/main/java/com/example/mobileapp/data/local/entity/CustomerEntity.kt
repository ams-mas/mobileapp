package com.example.mobileapp.data.local.entity

import androidx.room.Entity
import androidx.room.Index
import androidx.room.PrimaryKey

@Entity(
    tableName = "customers",
    indices = [Index(value = ["serverId"], unique = true), Index(value = ["phone"])]
)
data class CustomerEntity(
    @PrimaryKey(autoGenerate = true) val localId: Long = 0,
    val serverId: Long? = null,
    val fullName: String,
    val phone: String,
    val customerType: String = "buyer", // buyer, renter, owner
    val preferredPropertyType: String = "",
    val preferredLocation: String = "",
    val minimumBudget: Long? = null,
    val maximumBudget: Long? = null,
    val notes: String = "",
    val updatedAtEpochMillis: Long = System.currentTimeMillis(),
    val syncState: String = "pending"
)

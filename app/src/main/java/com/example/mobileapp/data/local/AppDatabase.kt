package com.example.mobileapp.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.mobileapp.data.local.entity.AppointmentEntity
import com.example.mobileapp.data.local.entity.CustomerEntity
import com.example.mobileapp.data.local.entity.InquiryEntity
import com.example.mobileapp.data.local.entity.PropertyEntity

@Database(
    entities = [
        PropertyEntity::class,
        CustomerEntity::class,
        InquiryEntity::class,
        AppointmentEntity::class
    ],
    version = 1,
    exportSchema = true
)
abstract class AppDatabase : RoomDatabase() {
    abstract fun propertyDao(): PropertyDao
    abstract fun customerDao(): CustomerDao
    abstract fun inquiryDao(): InquiryDao
    abstract fun appointmentDao(): AppointmentDao
}

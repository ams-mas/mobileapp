package com.example.mobileapp.data.local

import android.content.Context
import androidx.room.Room

/**
 * Process-wide database instance. The database stays on this device.
 * Do not use destructive migrations: they can erase real property/customer records.
 */
object DatabaseProvider {
    @Volatile
    private var instance: AppDatabase? = null

    fun get(context: Context): AppDatabase {
        return instance ?: synchronized(this) {
            instance ?: Room.databaseBuilder(
                context.applicationContext,
                AppDatabase::class.java,
                "real_estate_local.db"
            ).build().also { instance = it }
        }
    }
}

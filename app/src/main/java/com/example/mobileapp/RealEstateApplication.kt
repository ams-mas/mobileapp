package com.example.mobileapp

import android.app.Application
import com.example.mobileapp.data.local.DatabaseProvider

class RealEstateApplication : Application() {
    // Create the Room database lazily on first access; no network is required.
    val localDatabase by lazy { DatabaseProvider.get(this) }
}

package com.example.mobileapp.data.repository

import com.example.mobileapp.data.local.CustomerDao
import com.example.mobileapp.data.local.entity.CustomerEntity
import kotlinx.coroutines.flow.Flow

class CustomerRepository(private val customerDao: CustomerDao) {
    fun observeCustomers(): Flow<List<CustomerEntity>> = customerDao.observeAll()

    fun searchCustomers(query: String): Flow<List<CustomerEntity>> =
        customerDao.search(query.trim())

    suspend fun saveLocally(customer: CustomerEntity): Long =
        customerDao.upsert(
            customer.copy(
                updatedAtEpochMillis = System.currentTimeMillis(),
                syncState = "pending"
            )
        )

    suspend fun pendingSync(): List<CustomerEntity> = customerDao.getPendingSync()
}

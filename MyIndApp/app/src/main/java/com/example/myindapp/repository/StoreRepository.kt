package com.example.myindapp.repository

import com.example.myindapp.api.RetrofitInstance
import com.example.myindapp.database.StoreDao
import com.example.myindapp.data.Store
import kotlinx.coroutines.flow.Flow

class StoreRepository(private val storeDao: StoreDao) {

    fun getStores(categoryId: Int): Flow<List<Store>> = storeDao.getStores(categoryId)

    suspend fun refreshStores(categoryId: Int) {
        val storesFromApi = RetrofitInstance.api.getStores(categoryId)
        storeDao.insertAll(storesFromApi)
    }
}
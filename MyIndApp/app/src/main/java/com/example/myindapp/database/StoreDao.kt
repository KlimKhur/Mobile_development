package com.example.myindapp.database

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.myindapp.data.Store
import kotlinx.coroutines.flow.Flow
@Dao
interface StoreDao {
    @Query("SELECT * FROM store WHERE categoryId = :categoryId")
    fun getStores(categoryId: Int): Flow<List<Store>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(stores: List<Store>)
}
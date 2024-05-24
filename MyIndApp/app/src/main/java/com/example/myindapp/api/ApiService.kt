package com.example.myindapp.api

import com.example.myindapp.data.Category
import com.example.myindapp.data.Store
import retrofit2.http.GET
import retrofit2.http.Path

interface ApiService {
    @GET("categories")
    suspend fun getCategories(): List<Category>

    @GET("categories/{categoryId}/stores")
    suspend fun getStores(@Path("categoryId") categoryId: Int): List<Store>
}
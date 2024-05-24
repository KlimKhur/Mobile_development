package com.example.myindapp.repository

import com.example.myindapp.api.RetrofitInstance
import com.example.myindapp.database.CategoryDao
import com.example.myindapp.data.Category
import kotlinx.coroutines.flow.Flow

class CategoryRepository(private val categoryDao: CategoryDao) {

    val categories: Flow<List<Category>> = categoryDao.getCategories()

    suspend fun refreshCategories() {
        val categoriesFromApi = RetrofitInstance.api.getCategories()
        categoryDao.insertAll(categoriesFromApi)
    }
}
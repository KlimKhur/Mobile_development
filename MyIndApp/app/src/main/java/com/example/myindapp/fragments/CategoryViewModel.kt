package com.example.myindapp.fragments

import androidx.lifecycle.*
import com.example.myindapp.repository.CategoryRepository
import com.example.myindapp.data.Category
import kotlinx.coroutines.launch

class CategoryViewModel(private val repository: CategoryRepository) : ViewModel() {

    val categories: LiveData<List<Category>> = repository.categories.asLiveData()

    fun fetchCategoriesFromApi() {
        viewModelScope.launch {
            repository.refreshCategories()
        }
    }
}

class CategoryViewModelFactory(private val repository: CategoryRepository) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(CategoryViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return CategoryViewModel(repository) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}

package com.example.myindapp.fragments


import androidx.lifecycle.*
import com.example.myindapp.repository.StoreRepository
import com.example.myindapp.data.Store
import kotlinx.coroutines.launch

class StoreViewModel(private val repository: StoreRepository) : ViewModel() {

    fun getStores(categoryId: Int): LiveData<List<Store>> =
        repository.getStores(categoryId).asLiveData()

    fun fetchStoresFromApi(categoryId: Int) {
        viewModelScope.launch {
            repository.refreshStores(categoryId)
        }
    }
}

class StoreViewModelFactory(private val repository: StoreRepository) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(StoreViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return StoreViewModel(repository) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}
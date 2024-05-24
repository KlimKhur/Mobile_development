package com.example.myindapp.data

data class Store(
    val id: Int,
    val name: String,
    val address: String,
    val hours: String, // Время работы
    val description: String,
    val phone: String,
    val email: String,
    val website: String,
    val categoryId: Int
)
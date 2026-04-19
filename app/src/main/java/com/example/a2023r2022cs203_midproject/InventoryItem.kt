package com.example.a2023r2022cs203_midproject

data class InventoryItem(
    val id: String,
    val name: String,
    val category: String,
    val subCategory: String,
    val stockQuantity: Double,
    val unit: String,
    val price: Double
)

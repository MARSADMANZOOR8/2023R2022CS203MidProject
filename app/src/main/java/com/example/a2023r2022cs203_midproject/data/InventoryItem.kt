package com.example.a2023r2022cs203_midproject.data

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "inventory_items")
data class InventoryItem(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    val itemName: String,
    val quantity: Int,
    val price: Double
)
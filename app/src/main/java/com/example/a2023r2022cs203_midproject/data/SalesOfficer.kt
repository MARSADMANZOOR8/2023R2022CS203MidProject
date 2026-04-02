package com.example.a2023r2022cs203_midproject.data

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "sales_officers")
data class SalesOfficer(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    val email: String,
    val area: String,
    val role: String = "Sales Officer"
)
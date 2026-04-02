package com.example.a2023r2022cs203_midproject.data

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query

@Dao
interface SalesOfficerDao {
    @Insert
    suspend fun insert(salesOfficer: SalesOfficer)

    @Query("SELECT * FROM sales_officers")
    suspend fun getAllSalesOfficers(): List<SalesOfficer>
}
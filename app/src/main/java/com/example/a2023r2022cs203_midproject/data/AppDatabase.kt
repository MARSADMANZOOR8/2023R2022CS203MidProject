package com.example.a2023r2022cs203_midproject.data

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [SalesOfficer::class], version = 1)
abstract class AppDatabase : RoomDatabase() {
    abstract fun salesOfficerDao(): SalesOfficerDao

    companion object {
        @Volatile
        private var INSTANCE: AppDatabase? = null

        fun getDatabase(context: Context): AppDatabase {
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    AppDatabase::class.java,
                    "smart_agro_database"
                ).build()
                INSTANCE = instance
                instance
            }
        }
    }
}
package com.cs4520.assignment4

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(entities = [Product::class], version = 3)
abstract class AppDatabase : RoomDatabase() {
    abstract fun productDao(): ProductDao
}
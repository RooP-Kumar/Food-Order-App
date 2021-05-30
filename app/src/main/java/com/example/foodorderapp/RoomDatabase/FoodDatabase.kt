package com.example.foodorderapp.RoomDatabase

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [DataEntity::class], version = 1, exportSchema = false)
abstract class FoodDatabase: RoomDatabase() {
    abstract fun DataDao() : DataDao

    companion object {
        private var INSTANCE: FoodDatabase? = null
        fun getDatabase(context: Context): FoodDatabase {
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context, FoodDatabase::class.java, "Data"
                ).build()
                INSTANCE = instance
                instance
            }
        }
    }
}
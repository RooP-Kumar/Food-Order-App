package com.example.foodorderapp.RoomDatabase

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "Data")
class DataEntity (var name: String, var price: Int, var qnt: Int, var image: String) {
    @PrimaryKey(autoGenerate = true)
    var id: Int = 0
}
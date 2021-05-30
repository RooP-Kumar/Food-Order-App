package com.example.foodorderapp.RoomDatabase

import androidx.lifecycle.LiveData
import androidx.room.*

@Dao
abstract class DataDao {
    @Insert
    abstract fun insert(data: DataEntity)

    @Delete
    abstract fun delete(data: DataEntity)

    @Query("select * from data order by id asc")
    abstract fun allFoods(): LiveData<List<DataEntity>>
}
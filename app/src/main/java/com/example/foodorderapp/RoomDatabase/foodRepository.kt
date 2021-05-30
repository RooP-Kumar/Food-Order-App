package com.example.foodorderapp.RoomDatabase

import androidx.lifecycle.LiveData

class foodRepository(val DataDao: DataDao) {
    val allFoods: LiveData<List<DataEntity>> = DataDao.allFoods()

    fun addOrder(dataEntity: DataEntity) {
        DataDao.insert(dataEntity)
    }

    fun deleteOrder(dataEntity: DataEntity) {
        DataDao.delete(dataEntity)
    }
}
package com.example.foodorderapp.RoomDatabase

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class FoodViewModel(application: Application): AndroidViewModel(application) {
    var allFoods: LiveData<List<DataEntity>>
    var repository: foodRepository

    init {
        val dao = FoodDatabase.getDatabase(application).DataDao()
        repository = foodRepository(dao)
        allFoods = repository.allFoods
    }

    fun addOrder(dataEntity: DataEntity) = viewModelScope.launch(Dispatchers.IO) {
        repository.addOrder(dataEntity)
    }

    fun deleteOrder(dataEntity: DataEntity) = viewModelScope.launch(Dispatchers.IO) {
        repository.deleteOrder(dataEntity)
    }

}
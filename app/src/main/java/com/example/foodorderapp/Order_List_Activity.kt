package com.example.foodorderapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.foodorderapp.RecyclerViewAdapter.OrderedRecyclerView
import com.example.foodorderapp.RoomDatabase.FoodViewModel
import com.example.foodorderapp.databinding.ActivityFoodOrderBinding
import com.example.foodorderapp.databinding.ActivityOrderListBinding

class Order_List_Activity : AppCompatActivity() {
    private lateinit var binding: ActivityOrderListBinding
    private lateinit var viewModel: FoodViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityOrderListBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setSupportActionBar(binding.mainToolbar)
        val action = supportActionBar
        action?.setDisplayHomeAsUpEnabled(true)
        binding.mainToolbar.setNavigationOnClickListener {
            onBackPressed()
        }




        viewModel = ViewModelProvider(this,
            ViewModelProvider.AndroidViewModelFactory.getInstance(application))
            .get(FoodViewModel::class.java)

        val adapter = OrderedRecyclerView(this, viewModel)
        binding.orderedRecyclerView.adapter = adapter
        binding.orderedRecyclerView.layoutManager = LinearLayoutManager(this)

        viewModel.allFoods.observe(this, {
            it?.let {
                adapter.updateList(it)
            }
        })


    }
}
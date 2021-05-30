package com.example.foodorderapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import com.bumptech.glide.Glide
import com.example.foodorderapp.RoomDatabase.DataEntity
import com.example.foodorderapp.RoomDatabase.FoodViewModel
import com.example.foodorderapp.databinding.ActivityFoodOrderBinding

class Food_Order_Activity : AppCompatActivity() {
    private lateinit var binding: ActivityFoodOrderBinding
    private var qnt: Int = 1
    private lateinit var foodViewModel: FoodViewModel
    private lateinit var rs: String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityFoodOrderBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // grabbing rs sign
        rs = resources.getString(R.string.rs)

        setSupportActionBar(binding.mainToolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        binding.mainToolbar.setNavigationOnClickListener {
            onBackPressed()
        }

        // Creating viewModel
        foodViewModel = ViewModelProvider(this,
            ViewModelProvider.AndroidViewModelFactory.getInstance(application))
            .get(FoodViewModel::class.java)

        // Grabing the data from FoodAdapter class using intent.
        val name = intent.getStringExtra("name").toString()
        val price = intent.getIntExtra("price", 0)
        val image = intent.getStringExtra("image").toString()

        // Adding image in ImageView
        Glide.with(this).load(image).into(binding.foodImageInOrderActivity)

        binding.foodNameInOrderActivity.text = name
        binding.totalPrice.text = ("${rs}${qnt * price}").toString()

        // Setting up minus and plus button
        binding.increaseQuantity.setOnClickListener {
            qnt += 1
            binding.foodQuantity.text = (qnt).toString()
            binding.totalPrice.text = ("${rs}${qnt * price}").toString()
        }

        binding.decreaseQuantity.setOnClickListener {
            if(qnt > 0) {
                qnt -= 1
                binding.foodQuantity.text = (qnt).toString()
                binding.totalPrice.text = ("${rs}${qnt * price}").toString()
            } else {
                Toast.makeText(this, "Quantity can not be decrease now.",
                Toast.LENGTH_LONG)
                    .show()
            }
        }

        // Saving the order and adding to the orderbook
        binding.orderBtn.setOnClickListener {
            val quantity = binding.foodQuantity.text.toString().toInt()
            val foodprice = qnt * price
            val food = DataEntity(name, foodprice, quantity, image)
            foodViewModel.addOrder(food)
            finish()
        }

    }
}
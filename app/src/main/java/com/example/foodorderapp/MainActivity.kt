package com.example.foodorderapp

import android.app.AlertDialog
import android.app.Dialog
import android.content.Context
import android.content.DialogInterface
import android.content.Intent
import android.net.ConnectivityManager
import android.net.NetworkInfo
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.service.controls.templates.ControlTemplate
import android.util.Log
import android.view.Menu
import android.view.MenuInflater
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.foodorderapp.FirebaseData.FirebaseModel
import com.example.foodorderapp.RecyclerViewAdapter.FoodAdapter
import com.example.foodorderapp.databinding.ActivityMainBinding
import com.google.android.gms.tasks.OnSuccessListener
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.core.OnlineState
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private var food_data: ArrayList<FirebaseModel> = ArrayList() // All firebase data will save in this arraylist

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        //Setting up custom toolbar
        setSupportActionBar(binding.mainToolbar)

        // Creating the FirebaseFirestore instance
        val firebase = FirebaseFirestore.getInstance()

        // Fetching the data from firebase using firebase instance
        CoroutineScope(Dispatchers.IO).launch {
            firebase.collection("Foods").get().addOnCompleteListener {
                if (it.isSuccessful) {
                    it.result?.forEach {
                        val name = it.getString("title").toString()
                        val price = it.getString("price").toString().toInt()
                        val image = it.getString("image").toString()

                        // Adding data to the food_data list
                        var data = FirebaseModel(name, price, image)
                        food_data.add(data)
                    }

                    //passing the food_data to the recycler view to show
                    val adapter = FoodAdapter(applicationContext, food_data)
                    binding.foodOrderRecyclerView.adapter = adapter
                    binding.foodOrderRecyclerView.layoutManager = LinearLayoutManager(applicationContext)
                }
            }
        }

        binding.shoppingCart.setOnClickListener {
            val intent = Intent(this, Order_List_Activity::class.java)
            startActivity(intent)
        }



    }


}


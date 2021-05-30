package com.example.foodorderapp.RecyclerViewAdapter

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.foodorderapp.FirebaseData.FirebaseModel
import com.example.foodorderapp.Food_Order_Activity
import com.example.foodorderapp.R

class FoodAdapter(private val context: Context, private val food_data: ArrayList<FirebaseModel>): RecyclerView.Adapter<FoodAdapter.myHolder>() {
    val rs = context.resources.getString(R.string.rs)

    class myHolder(itemview: View): RecyclerView.ViewHolder(itemview) {
        val name = itemview.findViewById<TextView>(R.id.food_name)
        val price = itemview.findViewById<TextView>(R.id.food_price)
        val image = itemview.findViewById<ImageView>(R.id.food_image)
        val goToOrderPage = itemview.findViewById<CardView>(R.id.goToOrderPage)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): myHolder {
        val view = LayoutInflater.from(context).inflate(R.layout.recycler_food_card, parent, false)
        return myHolder(view)
    }

    override fun onBindViewHolder(holder: myHolder, position: Int) {
        holder.name.text = food_data[position].name
        holder.price.text = ("${rs}${food_data[position].price}").toString()
        Glide.with(holder.image.context).load(food_data[position].image_url).into(holder.image)
        holder.goToOrderPage.setOnClickListener {
            orderPageIntent(food_data[position].name, food_data[position].price, food_data[position].image_url, position)
        }
    }

    override fun getItemCount(): Int {
        return food_data.size
    }

    private fun orderPageIntent(name: String, price: Int, image: String, position: Int) {
        val intent = Intent(context, Food_Order_Activity::class.java)
        intent.putExtra("name", name)
        intent.putExtra("price", price)
        intent.putExtra("image", image)
        context.startActivity(intent)
    }
}
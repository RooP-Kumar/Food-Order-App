package com.example.foodorderapp.RecyclerViewAdapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.foodorderapp.R
import com.example.foodorderapp.RoomDatabase.DataEntity
import com.example.foodorderapp.RoomDatabase.FoodViewModel

class OrderedRecyclerView(private val context: Context, private val viewModel: FoodViewModel):
    RecyclerView.Adapter<OrderedRecyclerView.MyHolder>() {
    val rs = context.resources.getString(R.string.rs)

    val foodOrderList = ArrayList<DataEntity>()

    class MyHolder(item: View): RecyclerView.ViewHolder(item) {
        val image = item.findViewById<ImageView>(R.id.food_image)
        val foodName = item.findViewById<TextView>(R.id.food_name)
        val foodQnt = item.findViewById<TextView>(R.id.food_qnt)
        val foodTotalPrice = item.findViewById<TextView>(R.id.food_total_price)
        val deleteOrder = item.findViewById<LinearLayout>(R.id.deleteOrder)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyHolder {
        val view = LayoutInflater.from(context).inflate(R.layout.ordered_card_view_recycler_view, parent, false)
        return MyHolder(view)
    }

    override fun onBindViewHolder(holder: MyHolder, position: Int) {
        Glide.with(context).load(foodOrderList[position].image).into(holder.image)
        holder.foodName.text = foodOrderList[position].name
        holder.foodQnt.text = ("Qty = ${foodOrderList[position].qnt}").toString()
        holder.foodTotalPrice.text = ("= ${rs}${foodOrderList[position].price}").toString()
        holder.deleteOrder.setOnClickListener {
            viewModel.deleteOrder(foodOrderList[position])
        }
    }

    override fun getItemCount(): Int {
        return foodOrderList.size
    }

    fun updateList(data: List<DataEntity>){
        foodOrderList.clear()
        foodOrderList.addAll(data)
        notifyDataSetChanged()
    }
}
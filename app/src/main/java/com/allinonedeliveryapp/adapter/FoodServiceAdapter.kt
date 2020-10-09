package com.allinonedeliveryapp.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.allinonedeliveryapp.R
import com.allinonedeliveryapp.pojo.Subcategory
import com.allinonedeliveryapp.util.OnRecyclerItemClickListener
import com.bumptech.glide.Glide
import kotlinx.android.synthetic.main.raw_food_service.view.*

class FoodServiceAdapter(var listener: OnRecyclerItemClickListener<Subcategory>) :
    RecyclerView.Adapter<FoodServiceAdapter.FoodServiceViewHolder>() {

    lateinit var context: Context
    private var foodList: ArrayList<Subcategory> = arrayListOf()


    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): FoodServiceViewHolder {
        context = parent.context
        return FoodServiceViewHolder(
            LayoutInflater.from(context).inflate(R.layout.raw_food_service, parent, false)
        )
    }

    fun addItems(items: ArrayList<Subcategory>) {
        foodList.clear()
        foodList.addAll(items)
        notifyDataSetChanged()
    }

    override fun getItemCount(): Int {
        return foodList.size
    }

    override fun onBindViewHolder(holder: FoodServiceViewHolder, position: Int) {
        holder.name.text = foodList[position].title
        Glide.with(context).load(foodList[position].image).into(holder.imgService)

    }

    inner class FoodServiceViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val name = itemView.tvName
        val imgService = itemView.ivServiceLogo
        val cardBg = itemView.llCardView

        init {
            itemView.setOnClickListener {
                listener.onItemClick(it, adapterPosition, foodList[adapterPosition])
            }
        }
    }
}
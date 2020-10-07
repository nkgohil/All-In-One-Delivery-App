package com.allinonedeliveryapp.ui

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.allinonedeliveryapp.R
import com.allinonedeliveryapp.adapter.FoodServiceAdapter
import com.allinonedeliveryapp.util.OnRecyclerItemClickListener
import kotlinx.android.synthetic.main.activity_dash_board.*
import kotlinx.android.synthetic.main.activity_sub_category.*

class SubCategoryActivity : AppCompatActivity(), OnRecyclerItemClickListener<FoodServicesData> {
    var subCategory: FoodServiceAdapter? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sub_category)
        rvFoodServices.layoutManager =
            LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false)
        subCategory = FoodServiceAdapter(this)
        subCategory!!.addItems(foodServiceList())
        subcategoryrecyclerview.adapter = subCategory
    }

    private fun foodServiceList(): ArrayList<FoodServicesData> {
        val foodList: ArrayList<FoodServicesData> = arrayListOf()
        foodList.add(FoodServicesData("Vegetable", R.drawable.vegetable, R.color.cream))
        foodList.add(FoodServicesData("Groceries", R.drawable.groceries, R.color.cream))
        foodList.add(FoodServicesData("Vegetable", R.drawable.vegetable, R.color.cream))
        return foodList

    }

    override fun onItemClick(view: View?, position: Int, obj: FoodServicesData) {

    }
}
package com.allinonedeliveryapp.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.allinonedeliveryapp.R
import com.allinonedeliveryapp.adapter.FoodServiceAdapter
import kotlinx.android.synthetic.main.activity_dash_board.*

class DashBoardActivity : AppCompatActivity() {

    var adapterFood: FoodServiceAdapter? = null
    var adapterGeneral: FoodServiceAdapter? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_dash_board)

        initView()
    }

    private fun initView() {
        rvFoodServices.layoutManager =
            LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false)
        adapterFood = FoodServiceAdapter()
        adapterFood!!.addItems(foodServiceList())
        rvFoodServices.adapter = adapterFood

        rvGeneralServices.layoutManager =
            LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false)
        adapterGeneral = FoodServiceAdapter()
        adapterGeneral!!.addItems(foodServiceList())
        rvGeneralServices.adapter = adapterGeneral
    }

    private fun foodServiceList(): ArrayList<FoodServicesData> {
        val foodList: ArrayList<FoodServicesData> = arrayListOf()
        foodList.add(FoodServicesData("Vegetable", R.drawable.vegetable, R.color.cream))
        foodList.add(FoodServicesData("Groceries", R.drawable.groceries, R.color.cream))
        foodList.add(FoodServicesData("Vegetable", R.drawable.vegetable, R.color.cream))
        return foodList
    }
}

data class FoodServicesData(
    var name: String,
    var serviceImage: Int,
    var bgColor: Int
)
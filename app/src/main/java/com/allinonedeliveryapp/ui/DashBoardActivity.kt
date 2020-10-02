package com.allinonedeliveryapp.ui

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.allinonedeliveryapp.R
import com.allinonedeliveryapp.adapter.FoodServiceAdapter
import com.allinonedeliveryapp.util.OnRecyclerItemClickListener
import kotlinx.android.synthetic.main.activity_dash_board.*

class DashBoardActivity : AppCompatActivity(), OnRecyclerItemClickListener<FoodServicesData> {

    var adapterFood: FoodServiceAdapter? = null
    var adapterGeneral: FoodServiceAdapter? = null
    var adapterRepair: FoodServiceAdapter? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_dash_board)

        initView()
    }

    private fun initView() {
        rvFoodServices.layoutManager =
            LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false)
        adapterFood = FoodServiceAdapter(this)
        adapterFood!!.addItems(foodServiceList())
        rvFoodServices.adapter = adapterFood

        rvGeneralServices.layoutManager =
            LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false)
        adapterGeneral = FoodServiceAdapter(this)
        adapterGeneral!!.addItems(foodServiceList())
        rvGeneralServices.adapter = adapterGeneral

        rvRepairServices.layoutManager =
            LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false)
        adapterRepair = FoodServiceAdapter(this)
        adapterRepair!!.addItems(foodServiceList())
        rvRepairServices.adapter = adapterRepair
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

data class FoodServicesData(
    var name: String,
    var serviceImage: Int,
    var bgColor: Int
)
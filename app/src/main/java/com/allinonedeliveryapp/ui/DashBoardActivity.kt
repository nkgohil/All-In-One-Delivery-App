package com.allinonedeliveryapp.ui

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.allinonedeliveryapp.R
import com.allinonedeliveryapp.adapter.FoodServiceAdapter
import com.allinonedeliveryapp.pojo.CategoryItem
import com.allinonedeliveryapp.util.OnRecyclerItemClickListener
import com.allinonedeliveryapp.util.PreferenceHelper
import com.allinonedeliveryapp.webapi.RemoteCallback
import com.allinonedeliveryapp.webapi.WebAPIManager
import kotlinx.android.synthetic.main.activity_dash_board.*


class DashBoardActivity : AppCompatActivity(), OnRecyclerItemClickListener<FoodServicesData> {
    var adapterFood: FoodServiceAdapter? = null
    var adapterGeneral: FoodServiceAdapter? = null
    var adapterRepair: FoodServiceAdapter? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
//        overridePendingTransition(R.anim.fadein, R.anim.fadeout)
        setContentView(R.layout.activity_dash_board)
        tvUserName.setText("Hey " + PreferenceHelper.getInstance().username)
        initView()
        getCategory()

    }

    private fun initView() {
        contactus.setOnClickListener {
            openWhatsApp()
        }
        rvFoodServices.layoutManager =
            LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false)
        adapterFood = FoodServiceAdapter(this)


        rvGeneralServices.layoutManager =
            LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false)
        adapterGeneral = FoodServiceAdapter(this)


        rvRepairServices.layoutManager =
            LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false)
        adapterRepair = FoodServiceAdapter(this)

        imageProfile.setOnClickListener {
            startActivity(Intent(this, ProfileScreen::class.java))


        }
    }

    private fun getCategory() {
        WebAPIManager.instance.category()
            .enqueue(object : RemoteCallback<ArrayList<CategoryItem>>() {
                override fun onSuccess(response: ArrayList<CategoryItem>?) {
                    adapterFood!!.addItems(foodServiceList())
                    rvFoodServices.adapter = adapterFood
                    adapterRepair!!.addItems(foodServiceList())
                    rvRepairServices.adapter = adapterRepair
                    adapterGeneral!!.addItems(foodServiceList())
                    rvGeneralServices.adapter = adapterGeneral
                }

                override fun onUnauthorized(throwable: Throwable) {
                    TODO("Not yet implemented")
                }

                override fun onFailed(throwable: Throwable) {
                    TODO("Not yet implemented")
                }

                override fun onInternetFailed() {
                    TODO("Not yet implemented")
                }

                override fun onEmptyResponse(message: String) {
                    TODO("Not yet implemented")
                }

            })
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

    private fun openWhatsApp() {
        try {
            val text = "This is a test" // Replace with your message.
            val toNumber =
                "917878716161" // Replace with mobile phone number without +Sign or leading zeros, but with country code
            //Suppose your country is India and your phone number is “xxxxxxxxxx”, then you need to send “91xxxxxxxxxx”.
            val intent = Intent(Intent.ACTION_VIEW)
            intent.data = Uri.parse("http://api.whatsapp.com/send?phone=$toNumber&text=$text")
            startActivity(intent)
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }
}

data class FoodServicesData(
    var name: String,
    var serviceImage: Int,
    var bgColor: Int
)
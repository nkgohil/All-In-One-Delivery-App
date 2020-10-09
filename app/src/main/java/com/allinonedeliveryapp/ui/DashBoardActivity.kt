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
import com.allinonedeliveryapp.pojo.Subcategory
import com.allinonedeliveryapp.util.OnRecyclerItemClickListener
import com.allinonedeliveryapp.util.PreferenceHelper
import com.allinonedeliveryapp.webapi.RemoteCallback
import com.allinonedeliveryapp.webapi.WebAPIManager
import kotlinx.android.synthetic.main.activity_dash_board.*


class DashBoardActivity : AppCompatActivity(), OnRecyclerItemClickListener<Subcategory> {
    var adapterFood: FoodServiceAdapter? = null
    var adapterGeneral: FoodServiceAdapter? = null
    var adapterRepair: FoodServiceAdapter? = null
    var foodList: ArrayList<Subcategory> = arrayListOf()
    var generalList: ArrayList<Subcategory> = arrayListOf()
    var repairingList: ArrayList<Subcategory> = arrayListOf()

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
                    for (item in response!!.indices) {
                        if (response[item].category_id == 1) {
                            foodList.addAll(response[item].subcategory!!)
                        }
                        if (response[item].category_id == 2) {
                            generalList.addAll(response[item].subcategory!!)
                        }
                        if (response[item].category_id == 3) {
                            repairingList.addAll(response[item].subcategory!!)
                        }
                    }
                    adapterFood!!.addItems(foodList)
                    rvFoodServices.adapter = adapterFood
                    adapterRepair!!.addItems(repairingList)
                    rvRepairServices.adapter = adapterRepair
                    adapterGeneral!!.addItems(generalList)
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

    override fun onItemClick(view: View?, position: Int, obj: Subcategory) {
        val intent = Intent(this, ProductClickActivity::class.java)
        intent.putExtra("data", obj)
        startActivity(intent)
    }
}


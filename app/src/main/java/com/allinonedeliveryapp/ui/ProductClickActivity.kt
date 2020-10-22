package com.allinonedeliveryapp.ui

import android.content.ActivityNotFoundException
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.text.method.ScrollingMovementMethod
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.PopupMenu
import com.allinonedeliveryapp.R
import com.allinonedeliveryapp.pojo.Subcategory
import com.glide.slider.library.SliderLayout
import com.glide.slider.library.slidertypes.DefaultSliderView
import com.glide.slider.library.slidertypes.TextSliderView
import kotlinx.android.synthetic.main.activity_product_click.*


class ProductClickActivity : AppCompatActivity(), View.OnClickListener {
    private var mDemoSlider: SliderLayout? = null
    var list: Subcategory? = null
    val listUrl: ArrayList<String> = ArrayList()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_product_click)
        initview()


        mDemoSlider = findViewById(R.id.imgproduct)
        if (intent != null) {
            list = intent.getParcelableExtra("data")
        }
        listUrl.add(list!!.image!!)
        listUrl.add(list!!.image1!!)
        listUrl.add(list!!.image2!!)
        listUrl.add(list!!.image3!!)
        val sliderView = DefaultSliderView(this)
        //.diskCacheStrategy(DiskCacheStrategy.NONE)
        //.placeholder(R.drawable.placeholder)
        //.error(R.drawable.placeholder);
        for (i in listUrl.indices) {
            val sliderView = TextSliderView(this)
            // if you want show image only / without description text use DefaultSliderView instead

            // initialize SliderLayout
            sliderView
                .image(listUrl[i])
                .setProgressBarVisible(true)


            //add your extra information
            mDemoSlider!!.addSlider(sliderView)
        }



        menuIcon.setOnClickListener {
            val intent = Intent(this, DashBoardActivity::class.java)
            startActivity(intent)
        }
        getDescription()

        tvhowmightwork.movementMethod = ScrollingMovementMethod()
        tvdescription.movementMethod = ScrollingMovementMethod()
        menuitem.setOnClickListener {
            //Creating the instance of PopupMenu
            val popup = PopupMenu(this@ProductClickActivity, menuitem)
            //Inflating the Popup using xml file
            popup.menuInflater.inflate(R.menu.menu, popup.menu)
            //registering popup with OnMenuItemClickListener
            popup.setOnMenuItemClickListener {
                when (it.itemId) {
                    R.id.share -> {
                        val link =
                            "https://play.google.com/store/apps/details?id=" + this.packageName
                        val AutomaticIntent = Intent(Intent.ACTION_SEND)
                        AutomaticIntent.type = "text/plain"
                        AutomaticIntent.putExtra(Intent.EXTRA_TEXT, link)
                        AutomaticIntent.putExtra(Intent.EXTRA_SUBJECT, "All In One Delivery App")
                        startActivity(AutomaticIntent)
                    }
                    R.id.rating -> {
                        val uri: Uri = Uri.parse("market://details?id=" + this.packageName)
                        val goToMarket = Intent(Intent.ACTION_VIEW, uri)
                        // To count with Play market backstack, After pressing back button,
                        // to taken back to our application, we need to add d flags to intent.
                        // To count with Play market backstack, After pressing back button,
                        // to taken back to our application, we need to add d flags to intent.
                        goToMarket.addFlags(
                            Intent.FLAG_ACTIVITY_NO_HISTORY or
                                    Intent.FLAG_ACTIVITY_NEW_DOCUMENT or
                                    Intent.FLAG_ACTIVITY_MULTIPLE_TASK
                        )
                        try {
                            startActivity(goToMarket)
                        } catch (e: ActivityNotFoundException) {
                            startActivity(
                                Intent(
                                    Intent.ACTION_VIEW,
                                    Uri.parse("http://play.google.com/store/apps/details?id=" + this.packageName)
                                )
                            )
                        }

                        true

                        true
                    }
                }

                true
            }
            popup.show()//showing popup menu
        }
    }

    private fun initview() {
        callus.setOnClickListener(this)
        btnmessage.setOnClickListener(this)
        directwhatsapp.setOnClickListener(this)
    }

    private fun getDescription() {
        tvdescription.text = list!!.description
        tvhowmightwork.text = list!!.how_it_works
        tvtitle.text = list!!.title

    }

    override fun onClick(v: View?) {
        when (v?.id) {
            R.id.callus -> {
                val callIntent = Intent(Intent.ACTION_DIAL)
                callIntent.data = Uri.parse("tel:" + list!!.contact_number)
                startActivity(callIntent)
            }
            R.id.btnmessage -> {
                val uri = Uri.parse("smsto:+" + list!!.contact_number)
                val intent = Intent(Intent.ACTION_SENDTO, uri)
                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK)
                intent.putExtra("sms_body", "I want to buy " + list!!.title)
                startActivity(intent)
            }
            R.id.directwhatsapp -> {
                openWhatsApp()
            }
        }
    }

    private fun openWhatsApp() {
        try {
            val text = "i want to buy " + list!!.title // Replace with your message.
            val toNumber = list!!.contact_number
            // Replace with mobile phone number without +Sign or leading zeros, but with country code
            //Suppose your country is India and your phone number is “xxxxxxxxxx”, then you need to send “91xxxxxxxxxx”.
            val intent = Intent(Intent.ACTION_VIEW)
            intent.data = Uri.parse("http://api.whatsapp.com/send?phone=$toNumber&text=$text")
            startActivity(intent)
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }
}
package com.allinonedeliveryapp.ui

import android.content.ActivityNotFoundException
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.PopupMenu
import com.allinonedeliveryapp.R
import kotlinx.android.synthetic.main.activity_product_click.*


class ProductClickActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
//        overridePendingTransition(R.anim.fadein, R.anim.fadeout)
        setContentView(R.layout.activity_product_click)
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

}
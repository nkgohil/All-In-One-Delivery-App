package com.allinonedeliveryapp.ui

import android.Manifest
import android.app.Activity
import android.content.ActivityNotFoundException
import android.content.Intent
import android.content.pm.PackageManager
import android.net.Uri
import android.os.Build
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.allinonedeliveryapp.R
import com.allinonedeliveryapp.extension.hideProgressDialog
import com.allinonedeliveryapp.extension.showProgressDialog
import com.allinonedeliveryapp.pojo.ProfileRetrieve
import com.allinonedeliveryapp.pojo.ProfileUpdate
import com.allinonedeliveryapp.util.Constant
import com.allinonedeliveryapp.util.PreferenceHelper
import com.allinonedeliveryapp.webapi.RemoteCallback
import com.allinonedeliveryapp.webapi.WebAPIManager
import com.bumptech.glide.Glide
import kotlinx.android.synthetic.main.activity_profile_screen.*


class ProfileScreen : AppCompatActivity(), View.OnClickListener {
    private val IMAGE_PICK_CODE = 1000;

    //Permission code
    private val PERMISSION_CODE = 1001;

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
//        overridePendingTransition(R.anim.fadein, R.anim.fadeout)
        setContentView(R.layout.activity_profile_screen)
        getProfile()
        initview()
        imageProfile.setOnClickListener {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                if (checkSelfPermission(Manifest.permission.READ_EXTERNAL_STORAGE) ==
                    PackageManager.PERMISSION_DENIED
                ) {
                    //permission denied
                    val permissions = arrayOf(Manifest.permission.READ_EXTERNAL_STORAGE)
                    //show popup to request runtime permission
                    requestPermissions(permissions, PERMISSION_CODE)
                } else {
                    //permission already granted
                    pickImageFromGallery()
                }
            } else {
                //system OS is < Marshmallow
                pickImageFromGallery()
            }
        }
    }

    private fun getProfile() {
        showProgressDialog()
        WebAPIManager.instance.userProfile(PreferenceHelper.getInstance().userId!!.toInt())
            .enqueue(object :
                RemoteCallback<ProfileRetrieve>() {
                override fun onSuccess(response: ProfileRetrieve?) {

                    Glide.with(this@ProfileScreen).asBitmap().placeholder(R.drawable.placeholder)
                        .load(response!!.profile_image).into(imageProfile)
                    tvprofileusername.setText(response!!.username)
                    profileemail.setText(response!!.email)
                    hideProgressDialog()
                }

                override fun onUnauthorized(throwable: Throwable) {
                    hideProgressDialog()
                }

                override fun onFailed(throwable: Throwable) {
                    Log.e("tag", throwable.localizedMessage)
                }

                override fun onInternetFailed() {
                    hideProgressDialog()
                }

                override fun onEmptyResponse(message: String) {
                    hideProgressDialog()
                }

            })
    }

    private fun initview() {
        shareall.setOnClickListener(this)
        rate.setOnClickListener(this)
        logout.setOnClickListener(this)
    }

    private fun pickImageFromGallery() {
        val intent = Intent(Intent.ACTION_PICK)
        intent.type = "image/*"
        startActivityForResult(intent, IMAGE_PICK_CODE)

    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        when (requestCode) {
            PERMISSION_CODE -> {
                if (grantResults.size > 0 && grantResults[0] ==
                    PackageManager.PERMISSION_GRANTED
                ) {
                    //permission from popup granted
                    pickImageFromGallery()
                } else {
                    //permission from popup denied
                    Toast.makeText(this, "Permission denied", Toast.LENGTH_SHORT).show()
                }
            }
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (resultCode == Activity.RESULT_OK && requestCode == IMAGE_PICK_CODE) {
            imageProfile.setImageURI(data?.data).toString()
            var image: String? = data!!.data!!.path

            Log.e("tag", data.toString())

            profileUpdate(Constant.BASE_URL + image!!)

        }
    }

    private fun profileUpdate(image: String) {
        WebAPIManager.instance.profileUpdate(image, PreferenceHelper.getInstance().userId!!.toInt())
            .enqueue(
                object : RemoteCallback<ProfileUpdate>() {
                    override fun onSuccess(response: ProfileUpdate?) {
                    }

                    override fun onUnauthorized(throwable: Throwable) {
                        Log.e("test", throwable.message!!)
                    }

                    override fun onFailed(throwable: Throwable) {
                        Log.e("test", throwable.message!!)
                    }

                    override fun onInternetFailed() {
                        Log.e("test", "no internet")
                    }

                    override fun onEmptyResponse(message: String) {
                        Log.e("test", message!!)
                    }

                })
    }

    override fun onClick(v: View) {
        when (v.id) {
            R.id.shareall -> {
                val link = "https://play.google.com/store/apps/details?id=" + this.packageName
                val AutomaticIntent = Intent(Intent.ACTION_SEND)
                AutomaticIntent.type = "text/plain"
                AutomaticIntent.putExtra(Intent.EXTRA_TEXT, link)
                AutomaticIntent.putExtra(Intent.EXTRA_SUBJECT, "All In One Delivery App")
                startActivity(AutomaticIntent)
                Log.e("tag", "button click")
            }
            R.id.rate -> {
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

            }
            R.id.logout -> {
                val intent = Intent(this, SignInActivity::class.java)
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)
                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK)
                PreferenceHelper.getInstance().clearPref()
                startActivity(intent)
                finish()
            }
        }

    }

}
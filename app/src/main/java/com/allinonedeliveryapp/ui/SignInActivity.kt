package com.allinonedeliveryapp.ui

import android.annotation.SuppressLint
import android.content.ActivityNotFoundException
import android.content.ComponentName
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.text.TextUtils
import android.util.Patterns
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.allinonedeliveryapp.R
import com.allinonedeliveryapp.extension.hideProgressDialog
import com.allinonedeliveryapp.extension.showProgressDialog
import com.allinonedeliveryapp.pojo.Login
import com.allinonedeliveryapp.util.PreferenceHelper
import com.allinonedeliveryapp.webapi.RemoteCallback
import com.allinonedeliveryapp.webapi.WebAPIManager
import kotlinx.android.synthetic.main.activity_sign_in.*


class SignInActivity : AppCompatActivity(), View.OnClickListener {
    @SuppressLint("ClickableViewAccessibility")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
//        overridePendingTransition(R.anim.fadein, R.anim.fadeout)
        setContentView(R.layout.activity_sign_in)
        initView()
    }

    private fun initView() {
        imgbackbutton.setOnClickListener(this)
        btnLogIn.setOnClickListener(this)
        signupbtn.setOnClickListener(this)
        tvForgotPassword.setOnClickListener(this)
    }

    override fun onClick(v: View?) {
        when (v?.id) {
            R.id.imgbackbutton -> {
                startActivity(Intent(this, LoginSignup::class.java))
                finish()
            }
            R.id.btnLogIn -> {
//                val intent = Intent(this, DashBoardActivity::class.java)
//                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)
//                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK)
//                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
//                startActivity(intent)
//                finish()
                loginemail.text!!.trim().toString()
                loginpassword.text!!.trim().toString()

                if (isValid()) {
                    callLoginUser()
                }

            }
            R.id.signupbtn -> {
                val intent = Intent(this, SignUpActivity::class.java)
                startActivity(intent)
            }
            R.id.tvForgotPassword -> {
                val url = "http://samarth1771.pythonanywhere.com/reset_password/"
                try {
                    val i = Intent("android.intent.action.MAIN")
                    i.component =
                        ComponentName.unflattenFromString("com.android.chrome/com.android.chrome.Main")
                    i.addCategory("android.intent.category.LAUNCHER")
                    i.data = Uri.parse(url)
                    startActivity(i)
                } catch (e: ActivityNotFoundException) {
                    // Chrome is not installed
                    val i = Intent(Intent.ACTION_VIEW, Uri.parse(url))
                    startActivity(i)
                }
            }
        }
    }

    private fun callLoginUser() {
        showProgressDialog()
        WebAPIManager.instance.login(loginemail.text.toString(), loginpassword.text.toString())
            .enqueue(object : RemoteCallback<Login>() {
                override fun onSuccess(response: Login?) {
                    PreferenceHelper.getInstance().userId = response!!.profile_id.toString()
                    PreferenceHelper.getInstance().username = response!!.username
                    PreferenceHelper.getInstance().isLogin = true

                    val i = Intent(this@SignInActivity, DashBoardActivity::class.java)
                    i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)
                    i.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
                    i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK)
                    startActivity(i)

                    hideProgressDialog()
                }

                override fun onUnauthorized(throwable: Throwable) {
                    hideProgressDialog()

                }

                override fun onFailed(throwable: Throwable) {
                    wrongid.text = throwable.message!!

                    hideProgressDialog()

                }

                override fun onInternetFailed() {
                    hideProgressDialog()

                }

                override fun onEmptyResponse(message: String) {
                    hideProgressDialog()

                }

            })


    }

    private fun isValid(): Boolean {
        if (TextUtils.isEmpty(loginemail.text.toString())) {
            Toast.makeText(this, "Email required", Toast.LENGTH_LONG).show()
            loginemail.requestFocus()
            return false
        }
        if (!Patterns.EMAIL_ADDRESS.matcher(loginemail.text.toString()).matches()) {
            Toast.makeText(this, "Invalid Email Address", Toast.LENGTH_LONG).show()
            loginemail.requestFocus()
            return false
        }
        if (TextUtils.isEmpty(loginpassword.text.toString())) {
            Toast.makeText(this, "Password Required", Toast.LENGTH_LONG).show()
            loginpassword.requestFocus()
            return false
        }
        if (loginpassword.text!!.length < 8) {
            Toast.makeText(this, "Atleast 8 characters", Toast.LENGTH_LONG).show()
            loginpassword.requestFocus()
            return false
        }
        return true
    }

}
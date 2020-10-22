package com.allinonedeliveryapp.ui

import android.content.Intent
import android.os.Bundle
import android.text.TextUtils
import android.util.Patterns
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.allinonedeliveryapp.R
import com.allinonedeliveryapp.extension.hideProgressDialog
import com.allinonedeliveryapp.extension.showProgressDialog
import com.allinonedeliveryapp.extension.showToast
import com.allinonedeliveryapp.pojo.Register
import com.allinonedeliveryapp.util.PreferenceHelper
import com.allinonedeliveryapp.util.SessionManager
import com.allinonedeliveryapp.webapi.RemoteCallback
import com.allinonedeliveryapp.webapi.WebAPIManager
import kotlinx.android.synthetic.main.activity_sign_up.*

class SignUpActivity : AppCompatActivity(), View.OnClickListener {
    private lateinit var sessionManager: SessionManager
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
//        overridePendingTransition(R.anim.fadein, R.anim.fadeout)
        setContentView(R.layout.activity_sign_up)
        sessionManager = SessionManager(this)
        initView()
    }

    private fun initView() {
        imgbacksignup.setOnClickListener(this)
        SignUp.setOnClickListener(this)
        loginbtn.setOnClickListener(this)
    }

    override fun onClick(v: View?) {
        when (v?.id) {
            R.id.imgbacksignup -> {
                startActivity(Intent(this, LoginSignup::class.java))

                finish()
            }
            R.id.SignUp -> {
                fullname.text.trim().toString()
                Email.text!!.trim().toString()
                password.text!!.trim().toString()

                if (isValid()) {
                    callRegisterUser()
                }
            }
            R.id.loginbtn -> {
                val intent = Intent(this, SignInActivity::class.java)
                startActivity(intent)
            }
        }
    }

    private fun callRegisterUser() {
        showProgressDialog()
        WebAPIManager.instance.getData(
            Email.text.toString(),
            fullname.text.toString(),
            password.text.toString()
        ).enqueue(object : RemoteCallback<Register>() {
            override fun onSuccess(response: Register?) {
                PreferenceHelper.getInstance().userId = response!!.profile_id.toString()
                PreferenceHelper.getInstance().username = response!!.username
                PreferenceHelper.getInstance().isLogin = true
                showToast("successful registration")
                val i = Intent(this@SignUpActivity, DashBoardActivity::class.java)
                i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)
                i.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
                i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK)
                startActivity(i)
                hideProgressDialog()

            }

            override fun onUnauthorized(throwable: Throwable) {
                hideProgressDialog()
                showToast(throwable.message!!)
            }

            override fun onFailed(throwable: Throwable) {
                showToast("Something went Wrong!!")
                hideProgressDialog()


            }

            override fun onInternetFailed() {
                showToast("no internet available!!")
                hideProgressDialog()
            }

            override fun onEmptyResponse(message: String) {
                hideProgressDialog()
                showToast(message)
            }

        })
    }

    private fun isValid(): Boolean {

        if (TextUtils.isEmpty(fullname.text.toString())) {
            Toast.makeText(this, "Fullname required", Toast.LENGTH_LONG).show()
            fullname.requestFocus()
            return false
        }
        if (TextUtils.isEmpty(Email.text.toString())) {
            Toast.makeText(this, "Email required", Toast.LENGTH_LONG).show()
            Email.requestFocus()
            return false
        }
        if (!Patterns.EMAIL_ADDRESS.matcher(Email.text.toString()).matches()) {
            Toast.makeText(this, "Invalid Email Address", Toast.LENGTH_LONG).show()
            Email.requestFocus()
            return false
        }
        if (TextUtils.isEmpty(password.text.toString())) {
            Toast.makeText(this, "Password required", Toast.LENGTH_LONG).show()
            password.requestFocus()
            return false
        }
        if (password.text!!.length < 8) {
            Toast.makeText(this, "Atleast 8 characters", Toast.LENGTH_LONG).show()
            password.requestFocus()
            return false
        }

        return true
    }
}
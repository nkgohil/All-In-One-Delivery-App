package com.allinonedeliveryapp.ui

import android.content.Intent
import android.os.Bundle
import android.text.TextUtils
import android.util.Patterns
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.allinonedeliveryapp.R
import com.allinonedeliveryapp.extension.hideProgressDialog
import com.allinonedeliveryapp.extension.showProgressDialog
import com.allinonedeliveryapp.extension.showToast
import com.allinonedeliveryapp.pojo.Register
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
                Email.text.trim().toString()
                password.text.trim().toString()

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
                sessionManager.saveAuthToken(response!!.token)
                showToast(sessionManager.toString())
                hideProgressDialog()

            }

            override fun onUnauthorized(throwable: Throwable) {
                hideProgressDialog()
                showToast(throwable.message!!)
            }

            override fun onFailed(throwable: Throwable) {
                hideProgressDialog()
                showToast(throwable.message!!)

            }

            override fun onInternetFailed() {
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
            fullname.error = "Fullname required"
            fullname.requestFocus()
            return false
        }
        if (TextUtils.isEmpty(Email.text.toString())) {
            Email.error = "Email required"
            Email.requestFocus()
            return false
        }
        if (!Patterns.EMAIL_ADDRESS.matcher(Email.text.toString()).matches()) {
            Email.error = "Invalid Email Address"
            Email.requestFocus()
            return false
        }
        if (TextUtils.isEmpty(password.text.toString())) {
            password.error = "Password required"
            password.requestFocus()
            return false
        }
        if (password.text.length < 8) {
            password.error = "atleast 8 characters"
            password.requestFocus()
            return false
        }

        return true
    }
}
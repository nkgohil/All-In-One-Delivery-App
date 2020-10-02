package com.allinonedeliveryapp.ui

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.allinonedeliveryapp.R
import com.allinonedeliveryapp.SignInActivity
import kotlinx.android.synthetic.main.activity_login_signup.*

class LoginSignup : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login_signup)
        event()
    }

    private fun event() {
        btnSignUp.setOnClickListener {
            startActivity(Intent(this, SignUpActivity::class.java))
            finish()
        }
        btnLogIn.setOnClickListener {
            startActivity(Intent(this, SignInActivity::class.java))
            finish()
        }
    }
}
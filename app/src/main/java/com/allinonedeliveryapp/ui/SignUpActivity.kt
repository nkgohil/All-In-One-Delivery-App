package com.allinonedeliveryapp.ui

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.allinonedeliveryapp.R
import kotlinx.android.synthetic.main.activity_sign_up.*

class SignUpActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sign_up)
        event()
    }

    private fun event() {
        imgbacksignup.setOnClickListener {
            startActivity(Intent(this, LoginSignup::class.java))
            finish()
        }
        SignUp.setOnClickListener {
            startActivity(Intent(this, ProductClickActivity::class.java))
            finish()
        }
    }
}
package com.allinonedeliveryapp

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.allinonedeliveryapp.ui.LoginSignup
import kotlinx.android.synthetic.main.activity_sign_in.*

class SignInActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sign_in)
        event()
    }

    private fun event() {
        imgbackbutton.setOnClickListener {
            startActivity(Intent(this, LoginSignup::class.java))
            finish()
        }
    }
}
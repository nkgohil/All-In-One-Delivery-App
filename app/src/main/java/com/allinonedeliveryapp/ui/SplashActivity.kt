package com.allinonedeliveryapp.ui

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.view.WindowManager
import android.view.animation.Animation
import android.view.animation.AnimationUtils
import androidx.appcompat.app.AppCompatActivity
import com.allinonedeliveryapp.R
import com.allinonedeliveryapp.util.PreferenceHelper
import kotlinx.android.synthetic.main.activity_splash.*

class SplashActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
//        overridePendingTransition(R.anim.fadein, R.anim.fadeout)
        setContentView(R.layout.activity_splash)
        window.setFlags(
            WindowManager.LayoutParams.FLAG_FULLSCREEN,
            WindowManager.LayoutParams.FLAG_FULLSCREEN
        )
        var anim: Animation = AnimationUtils.loadAnimation(this, R.anim.splash)
        Handler().postDelayed({
            splashlogo.setImageResource(R.drawable.maapaa)

        }, 1000)
        Handler().postDelayed({

            moveToNextScreen()
        }, 2000)
    }

    private fun moveToNextScreen() {
        if (PreferenceHelper.getInstance().isLogin!!) {
            val intent = Intent(this, DashBoardActivity::class.java)
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)
            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK)
            startActivity(intent)
            finish()
        } else {
            startActivity(Intent(this, OnBoardActivity::class.java))
            finish()
        }
    }
}

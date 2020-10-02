package com.allinonedeliveryapp.ui

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.viewpager.widget.ViewPager
import com.allinonedeliveryapp.R
import com.allinonedeliveryapp.adapter.CustomPagerAdapter
import kotlinx.android.synthetic.main.activity_on_board.*
import me.relex.circleindicator.CircleIndicator

class OnBoardActivity : AppCompatActivity(), ViewPager.OnPageChangeListener {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_on_board)
        val viewPager = findViewById<View>(R.id.viewPager) as ViewPager
        viewPager.adapter = CustomPagerAdapter(this)
        var indicator: CircleIndicator = findViewById(R.id.indicator)
        indicator.setViewPager(viewPager)

        viewPager.setOnPageChangeListener(this)
        event()
    }

    private fun event() {
        img.setOnClickListener {
            startActivity(Intent(this, LoginSignup::class.java))
            finish()
        }
        skip.setOnClickListener {
            startActivity(Intent(this, LoginSignup::class.java))
            finish()
        }
    }

    override fun onPageScrolled(position: Int, positionOffset: Float, positionOffsetPixels: Int) {

    }

    override fun onPageSelected(position: Int) {
        if (position == 2) {
            skip.visibility = View.GONE
            img.visibility = View.VISIBLE
        } else {
            skip.visibility = View.VISIBLE
            img.visibility = View.GONE
        }
    }

    override fun onPageScrollStateChanged(state: Int) {

    }
}
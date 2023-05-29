package com.example.marvelappx.ui

import androidx.viewpager.widget.PagerAdapter
import android.view.LayoutInflater
import com.example.marvelappx.R
import android.widget.RelativeLayout
import android.view.ViewGroup
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.viewpager.widget.ViewPager
import android.widget.LinearLayout
import com.example.marvelappx.ui.SliderAdapter
import android.os.Bundle

class SlideActivity : AppCompatActivity() {
    private val mSlideViewPager: ViewPager? = null
    private val mDotLayout: LinearLayout? = null
    private val sliderAdapter: SliderAdapter? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.main_activity)
    }
}
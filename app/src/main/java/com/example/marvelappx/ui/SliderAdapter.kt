package com.example.marvelappx.ui

import android.content.Context
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
import android.view.View
import android.widget.ImageView

class SliderAdapter(var context: Context) : PagerAdapter() {
    var layoutInflater: LayoutInflater? = null

    //Arrays
    var slide_images = intArrayOf(
        R.drawable.civilwar1,
        R.drawable.civilwar2
    )
    var slide_headings = arrayOf(
        "primeiro",
        "segundo"
    )
    var slide_descs = arrayOf(
        "",
        ""
    )

    override fun getCount(): Int {
        return slide_headings.size
    }

    override fun isViewFromObject(view: View, `object`: Any): Boolean {
        return view === `object` as RelativeLayout
    }

    override fun instantiateItem(container: ViewGroup, position: Int): Any {
        layoutInflater = context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
        val view = layoutInflater!!.inflate(R.layout.slide_layout, container, false)
        val slideImageView = view.findViewById<View>(R.id.imageView2) as ImageView
        val slideHeading = view.findViewById<View>(R.id.heading) as TextView
        val slidedescription = view.findViewById<View>(R.id.description) as TextView
        slideImageView.setImageResource(slide_images[position])
        slideHeading.text = slide_headings[position]
        slidedescription.text = slide_descs[position]
        container.addView(view)
        return view
    }

    override fun destroyItem(container: ViewGroup, position: Int, `object`: Any) {
        container.removeView(`object` as RelativeLayout)
    }
}
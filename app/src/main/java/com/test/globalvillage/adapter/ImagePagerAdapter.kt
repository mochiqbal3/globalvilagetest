package com.test.globalvillage.adapter

import android.app.Activity
import android.content.Context
import android.content.Context.LAYOUT_INFLATER_SERVICE
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.RelativeLayout
import androidx.viewpager.widget.PagerAdapter
import androidx.viewpager.widget.ViewPager
import com.bumptech.glide.Glide
import com.test.globalvillage.R


class ImagePagerAdapter(private val act: Activity, private var items: MutableList<String>?, val context: Context) : PagerAdapter() {

    override fun getCount(): Int {
        return this.items!!.size
    }

    fun getItem(pos: Int): String {
        return items!![pos]
    }

    fun setItems(items: MutableList<String>) {
        this.items = items
        notifyDataSetChanged()
    }

    override fun isViewFromObject(view: View, `object`: Any): Boolean {
        return view === `object` as LinearLayout
    }

    override fun instantiateItem(container: ViewGroup, position: Int): Any {
        val image = items!![position]
        val inflater = act.getSystemService(LAYOUT_INFLATER_SERVICE) as LayoutInflater
        val v = inflater.inflate(R.layout.layout_pager, container, false)

        val imageView = v.findViewById(R.id.imageView) as ImageView
        Glide.with(context).load(image).into(imageView)

        (container as ViewPager).addView(v)
        return v
    }

    override fun destroyItem(container: ViewGroup, position: Int, `object`: Any) {
        (container as ViewPager).removeView(`object` as LinearLayout)
    }

}
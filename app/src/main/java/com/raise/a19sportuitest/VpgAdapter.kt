package com.raise.a19sportuitest

import android.content.Context
import android.graphics.Color
import android.provider.CalendarContract
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.LinearLayout
import androidx.viewpager.widget.PagerAdapter
import kotlinx.android.synthetic.main.item_vpg.view.*
import kotlin.random.Random


class VpgAdapter(val context: Context, val itemCount: Int) : PagerAdapter() {
    override fun isViewFromObject(v: View, any: Any): Boolean {
        return v == any
    }

    override fun getCount(): Int {
        return 2
    }

    override fun instantiateItem(container: ViewGroup, position: Int): Any {
        val rdm = Random
        val view = LayoutInflater.from(context).inflate(R.layout.item_vpg, container, false) as LinearLayout
        for(i in 0..itemCount){
            val item = View(container.context)
            item.setBackgroundColor(Color.argb(255, 255, rdm.nextInt(256), rdm.nextInt(256)))
            view.addView(item, LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, 500))
        }
        view.requestLayout()
        container.addView(view)
        return view
    }

    override fun destroyItem(container: ViewGroup, position: Int, `object`: Any) {
        container.removeView(`object` as View)
    }
}
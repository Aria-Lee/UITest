package com.raise.a19sportuitest

import android.content.Context
import android.graphics.Color
import android.provider.CalendarContract
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.LinearLayout
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import androidx.viewpager.widget.PagerAdapter
import kotlinx.android.synthetic.main.item_vpg.view.*
import kotlin.random.Random


class VpgAdapter(val context: Context) : PagerAdapter() {
    override fun isViewFromObject(v: View, any: Any): Boolean {
        return v == any
    }

    override fun getCount(): Int {
        return 2
    }

    override fun instantiateItem(container: ViewGroup, position: Int): Any {
        val rdm = Random
        val item = View(container.context)
        item.setBackgroundColor(Color.argb(255, 255, rdm.nextInt(256), rdm.nextInt(256)))
        container.addView(item, ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT))
        container.requestLayout()
        return item
    }

    override fun destroyItem(container: ViewGroup, position: Int, `object`: Any) {
        container.removeView(`object` as View)
    }
}

class FragmentPagerAdapter(fragmentManager: FragmentManager, var fragmentList: MutableList<Fragment>
) :
    FragmentPagerAdapter(fragmentManager) {

    private var isUpdate = false

    override fun getItem(position: Int): Fragment {
        println("****************** ${this.hashCode()} getItem ${fragmentList[position].hashCode()}")
        return fragmentList[position]
    }

    override fun isViewFromObject(view: View, `object`: Any): Boolean {
        println("****************** ${this.hashCode()} isViewFromObject ${super.isViewFromObject(view, `object`)}")
        return super.isViewFromObject(view, `object`)
    }


    override fun getCount(): Int {
        println("****************** ${this.hashCode()} getCount ${fragmentList.size}")

        return fragmentList.size
    }

    override fun instantiateItem(container: ViewGroup, position: Int): Any {
        println("****************** ${this.hashCode()} instantiateItem ${fragmentList[position].hashCode()}")
        return super.instantiateItem(container, position)
    }
//    override fun getPageTitle(position: Int): CharSequence? {
//        return if(titleList.size == 0) super.getPageTitle(position)
//        else context?.resources?.getString(titleList[position])
//    }

//    override fun getItemPosition(`object`: Any): Int {
////        return if(isUpdate) PagerAdapter.POSITION_NONE else super.getItemPosition(`object`)
//        return super.getItemPosition(`object`)
//    }

    fun update(list : MutableList<Fragment>){
        isUpdate = true
        fragmentList = list
        this.notifyDataSetChanged()
//        println("****************** ${this.hashCode()} notifyDataSetChanged")
        isUpdate = false
    }
}
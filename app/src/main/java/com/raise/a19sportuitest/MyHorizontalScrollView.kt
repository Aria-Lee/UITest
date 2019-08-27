package com.raise.a19sportuitest

import android.content.Context
import android.util.AttributeSet
import android.widget.HorizontalScrollView
import com.google.android.material.tabs.TabLayout


interface ScrollViewListener {
    fun onScrollChanged(scrollView: HorizontalScrollView, x: Int, y: Int, oldx: Int, oldy: Int)

}
class MyHorizontalScrollView @JvmOverloads constructor(
    context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0
) : HorizontalScrollView(context, attrs, defStyleAttr) {

    override fun onScrollChanged(l: Int, t: Int, oldl: Int, oldt: Int) {
        super.onScrollChanged(l, t, oldl, oldt)
    }
}

class MyTabLayout @JvmOverloads constructor(
    context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0
) : TabLayout(context, attrs, defStyleAttr) {

    var scrollViewListener : ScrollViewListener? = null

    fun setScrollViewLister(scrollViewListener: ScrollViewListener){
        this.scrollViewListener = scrollViewListener
    }

    override fun onScrollChanged(l: Int, t: Int, oldl: Int, oldt: Int) {
        super.onScrollChanged(l, t, oldl, oldt)
        scrollViewListener?.onScrollChanged(this, l, t, oldl, oldt)
    }
}
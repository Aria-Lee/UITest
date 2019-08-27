package com.raise.a19sportuitest

import android.content.Context
import android.util.AttributeSet
import android.view.View
import androidx.viewpager.widget.ViewPager

class MyViewPager: ViewPager {

    constructor(context: Context) : super(context)
    constructor(context: Context, attrs: AttributeSet?) : super(context, attrs)

    override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {
        var height = 0

        for (i in 0 until childCount) {
            val child = getChildAt(i)
            child.measure(
                widthMeasureSpec,
                View.MeasureSpec.makeMeasureSpec(0, View.MeasureSpec.UNSPECIFIED)
            )
            height = child.measuredHeight
        }
        var newHeightMeasureSpec = View.MeasureSpec.makeMeasureSpec(
            height,
            View.MeasureSpec.EXACTLY
        )
        super.onMeasure(widthMeasureSpec, newHeightMeasureSpec)
    }
}
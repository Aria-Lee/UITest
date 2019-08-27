package com.raise.a19sportuitest

import android.annotation.SuppressLint
import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.TypedValue
import android.view.Gravity
import android.view.MotionEvent
import android.view.View
import android.view.ViewGroup
import android.widget.AbsListView
import android.widget.HorizontalScrollView
import android.widget.TextView
import androidx.core.view.get
import com.google.android.material.tabs.TabLayout
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    lateinit var tv: TextView
    var selectedTab: View? = null
    val adapter = ExAdapter()
    var lastFirstVisibleItem = 0

    @SuppressLint("ResourceAsColor", "ClickableViewAccessibility")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        tv = TextView(this)

        setTab()
        setExpandableList()

        btn.setOnClickListener {
            for (i in 0 until adapter.groupCount) {
                exlv.collapseGroup(i)
            }
        }
    }

    private fun setExpandableList() {
        exlv.setAdapter(adapter)
        adapter.updateData()

        exlv.setOnScrollListener(object : AbsListView.OnScrollListener {
            override fun onScrollStateChanged(view: AbsListView?, scrollState: Int) {

            }

            override fun onScroll(
                view: AbsListView?,
                firstVisibleItem: Int,
                visibleItemCount: Int,
                totalItemCount: Int
            ) {
                lastFirstVisibleItem = firstVisibleItem
                if (lastFirstVisibleItem <= firstVisibleItem && totalItemCount - firstVisibleItem < 20) {
                    println("************** totalItemCount $totalItemCount  firstVisibleItem $firstVisibleItem")
                    adapter.updateData()
                    for (i in 0 until adapter.groupCount) {
                        exlv.expandGroup(i)
                    }
                }
            }

        })

        for (i in 0 until adapter.groupCount) {
            exlv.expandGroup(i)
        }
    }

    private fun setTab() {
        tblv.addOnTabSelectedListener(object : TabLayout.BaseOnTabSelectedListener<TabLayout.Tab> {
            override fun onTabReselected(p0: TabLayout.Tab?) {
            }

            override fun onTabUnselected(p0: TabLayout.Tab?) {
            }

            override fun onTabSelected(p0: TabLayout.Tab?) {
                selectedTab = (tblv.getChildAt(0) as ViewGroup).getChildAt(tblv.selectedTabPosition)
                tv.text = p0?.text
                tv.visibility = View.GONE
            }

        })
        for (i in 0..10) {
            val tab = tblv.newTab().setText("TAB $i")
            tblv.addTab(tab)
        }

        tblv.tabMode = if (tblv.tabCount > 5) TabLayout.MODE_SCROLLABLE else TabLayout.MODE_FIXED


        tblv.post {
            tv.height = tblv.height
            tv.width = selectedTab!!.width
            tv.x = tblv.left.toFloat()
            tv.top = tblv.top
            tv.setBackgroundResource(R.color.purple)
            tv.setTextColor(Color.WHITE)
            tv.setTextSize(TypedValue.COMPLEX_UNIT_PX, getResources().getDimension(R.dimen.tabTextSize))
            tv.gravity = Gravity.CENTER
            root.addView(tv)
            tv.setOnTouchListener { v, event ->
                event.action == MotionEvent.ACTION_DOWN
            }
        }

        tblv.setScrollViewLister(object : ScrollViewListener {
            override fun onScrollChanged(scrollView: HorizontalScrollView, x: Int, y: Int, oldx: Int, oldy: Int) {
                selectedTab?.run {
                    if (oldx < x) {
                        if (tv.visibility == View.GONE && this.left <= x) {
                            tv.visibility = View.VISIBLE
                            tv.x = tblv.left.toFloat()
                        } else if (tv.visibility == View.VISIBLE && tv.x == (tblv.right - tv.width).toFloat() && this.right <= x + scrollView.width)
                            tv.visibility = View.GONE
                    } else if (oldx > x) {
                        if (tv.visibility == View.VISIBLE && tv.x == tblv.left.toFloat() && this.left > x) tv.visibility =
                            View.GONE
                        else if (tv.visibility == View.GONE && this.right > x + scrollView.width) {
                            tv.visibility = View.VISIBLE
                            tv.x = (tblv.right - tv.width).toFloat()
                        }
                    }
                }
            }

        })
    }
}

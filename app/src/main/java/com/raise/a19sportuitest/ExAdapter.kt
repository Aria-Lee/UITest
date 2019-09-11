package com.raise.a19sportuitest

import android.graphics.Color
import android.view.View
import android.view.ViewGroup

import android.view.LayoutInflater
import android.widget.*

import androidx.core.view.get
import androidx.fragment.app.FragmentManager
import androidx.recyclerview.widget.GridLayoutManager
import androidx.viewpager.widget.ViewPager
import kotlinx.android.synthetic.main.child.view.*
import kotlinx.android.synthetic.main.group.view.*
import kotlinx.android.synthetic.main.item_vpg.view.*
import kotlin.random.Random

class ExAdapter(var fm: FragmentManager) :
    BaseExpandableListAdapter() {


    private var count = 0
    private var isLoading = true
    private var groupHeight = 0
    private var clickListener: ClickListener? = null


    interface ClickListener {
        fun controllAll()
    }

    fun setClickListener(clickListener: ClickListener) {
        this.clickListener = clickListener
    }

    override fun getGroupCount(): Int {
        return count
    }

    override fun getChildrenCount(groupPosition: Int): Int {
        return if(groupPosition ==0 ) 0 else 1
    }

    override fun getGroup(groupPosition: Int): Any {
        return groupPosition
    }

    override fun getChild(groupPosition: Int, childPosition: Int): Any {
        return childPosition
    }

    override fun onGroupCollapsed(groupPosition: Int) {
        if (groupPosition == 0) clickListener?.controllAll()
    }

    override fun onGroupExpanded(groupPosition: Int) {
        if (groupPosition == 0) clickListener?.controllAll()
    }

    override fun isEmpty(): Boolean {
        return false
    }

    override fun getCombinedGroupId(groupId: Long): Long {
        return groupId
    }

    override fun getCombinedChildId(groupId: Long, childId: Long): Long {
        return (groupId * 10 + childId)
    }

    override fun getGroupId(groupPosition: Int): Long {
        return groupPosition.toLong()
    }

    override fun getChildId(groupPosition: Int, childPosition: Int): Long {
        return childPosition.toLong()
    }

    override fun isChildSelectable(groupPosition: Int, childPosition: Int): Boolean {
        return true
    }

    override fun hasStableIds(): Boolean {
        return true
    }

    override fun getGroupView(groupPosition: Int, isExpanded: Boolean, convertView: View?, parent: ViewGroup?): View {
        val groupHolder: GroupViewHolder
        var view = convertView
        if (view == null) {
            view =
                LayoutInflater.from(parent!!.context).inflate(R.layout.group, parent, false)
            groupHolder = GroupViewHolder()
            groupHolder.groupTitle = view!!.tv_group
            groupHolder.groupView = view
            groupHolder.arrow = view.arrow
            view.tag = groupHolder
        } else {
            groupHolder = view.tag as GroupViewHolder
        }

        groupHolder.groupTitle.text = "Group $groupPosition"
        groupHolder.arrow.rotationX = if (isExpanded) 0f else 180f
        return view
    }

    override fun getChildView(
        groupPosition: Int,
        childPosition: Int,
        isLastChild: Boolean,
        convertView: View?,
        parent: ViewGroup?
    ): View {
        val itemHolder: ChildViewHolder
        var view = convertView
        if (view == null) {
            view = LayoutInflater.from(parent!!.context)
                .inflate(R.layout.child, parent, false)
            itemHolder = ChildViewHolder()
            itemHolder.title = view!!.tv_child
            itemHolder.vpg = view.vpg_child
            itemHolder.rdt1 = view.rdt1
            itemHolder.rdt2 = view.rdt2
//            itemHolder.vpg.adapter = VpgAdapter(parent!!.context, Random.nextInt(1, 3))
//            frag1.ta
            val adapter = FragmentPagerAdapter(fm, mutableListOf(MyFragment(), MyFragment()))
            itemHolder.vpg.adapter = adapter
            adapter.needOverrideItemId = true
            itemHolder.vpg.addOnPageChangeListener(object : ViewPager.OnPageChangeListener{
                override fun onPageScrollStateChanged(state: Int) {
                }

                override fun onPageScrolled(position: Int, positionOffset: Float, positionOffsetPixels: Int) {
                }

                override fun onPageSelected(position: Int) {
                    when(position){
                        0 -> itemHolder.rdt1.isChecked = true
                        1 -> itemHolder.rdt2.isChecked = true
                    }
                }

            })
            view.tag = itemHolder
        } else {
            itemHolder = view.tag as ChildViewHolder
        }

        itemHolder.title.text = "Child $groupPosition"
        itemHolder.rdt1.isChecked = true
        return view
    }

    override fun areAllItemsEnabled(): Boolean {
        return false
    }

    class GroupViewHolder {
        lateinit var groupTitle: TextView
        lateinit var groupView: View
        lateinit var arrow: ImageView

    }

    class ChildViewHolder {
        lateinit var title: TextView
        lateinit var vpgContainer: LinearLayout
        lateinit var vpg: ViewPager
        lateinit var rdt1: RadioButton
        lateinit var rdt2: RadioButton
        lateinit var child: View
    }

    fun updateData() {
        count += 30
        this.notifyDataSetChanged()
    }
}
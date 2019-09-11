package com.raise.a19sportuitest

import android.content.Context
import android.graphics.Color
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import androidx.fragment.app.Fragment
import kotlin.random.Random

class MyFragment: Fragment() {
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.frag, container, false)
        val rdm = Random
        view.setBackgroundColor(Color.argb(255, 255, rdm.nextInt(256), rdm.nextInt(256)))
        println("****************** fragment ${this.hashCode()} onCreateView")

        return view
    }

    override fun onAttach(context: Context?) {
        super.onAttach(context)
        println("****************** fragment ${this.hashCode()} onAttach")
    }

    override fun setUserVisibleHint(isVisibleToUser: Boolean) {
        println("****************** fragment ${this.hashCode()}  isVisibleToUser $isVisibleToUser")

        super.setUserVisibleHint(isVisibleToUser)
    }
}
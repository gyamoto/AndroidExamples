package com.gyamoto.viewpagertransformer.one

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.viewpager.widget.PagerAdapter
import com.gyamoto.viewpagertransformer.R
import com.gyamoto.viewpagertransformer.databinding.ViewContentBinding

class OneAdapter : PagerAdapter() {

    enum class Contents(val value: String) {
        VIEW("View"),
        PAGER("Pager"),
        ONE("One")
    }

    override fun isViewFromObject(view: View, `object`: Any): Boolean {
        return view == `object`
    }

    override fun getCount(): Int = Contents.values().size

    override fun instantiateItem(container: ViewGroup, position: Int): Any {

        val item = Contents.values()[position]
        val inflater = LayoutInflater.from(container.context)
        val binding = DataBindingUtil.inflate<ViewContentBinding>(
            inflater, R.layout.view_content, container, true
        ).apply {
            name.text = item.value
        }

        return binding.root
    }

    override fun destroyItem(container: ViewGroup, position: Int, `object`: Any) {
        container.removeView(`object` as View)
    }
}
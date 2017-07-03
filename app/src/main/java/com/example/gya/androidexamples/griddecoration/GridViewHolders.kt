package com.example.gya.androidexamples.griddecoration

import android.support.annotation.ColorInt
import android.support.annotation.LayoutRes
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ImageView
import com.example.gya.androidexamples.R

sealed class GridViewHolder(parent: ViewGroup, @LayoutRes layout: Int)
    : RecyclerView.ViewHolder(LayoutInflater.from(parent.context).inflate(layout, parent, false)) {
    private val image by lazy { itemView.findViewById(R.id.image) as ImageView }

    fun bind(@ColorInt color: Int) {
        image.setBackgroundColor(color)
    }
}

class LargeViewHolder(parent: ViewGroup, @LayoutRes layout: Int) : GridViewHolder(parent, layout)
class MediumViewHolder(parent: ViewGroup, @LayoutRes layout: Int) : GridViewHolder(parent, layout)
class SmallViewHolder(parent: ViewGroup, @LayoutRes layout: Int) : GridViewHolder(parent, layout)

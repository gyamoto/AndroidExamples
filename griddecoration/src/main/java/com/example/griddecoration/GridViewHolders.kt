package com.example.griddecoration

import android.support.annotation.ColorInt
import android.support.annotation.LayoutRes
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ImageView

sealed class GridViewHolder(parent: ViewGroup, @LayoutRes layout: Int) :
    RecyclerView.ViewHolder(LayoutInflater.from(parent.context).inflate(layout, parent, false)) {
    private val image by lazy { itemView.findViewById<ImageView>(R.id.image) }

    fun bind(@ColorInt color: Int) {
        image.setBackgroundColor(color)
    }
}

class LargeViewHolder(parent: ViewGroup, @LayoutRes layout: Int) : GridViewHolder(parent, layout)
class MediumViewHolder(parent: ViewGroup, @LayoutRes layout: Int) : GridViewHolder(parent, layout)
class SmallViewHolder(parent: ViewGroup, @LayoutRes layout: Int) : GridViewHolder(parent, layout)
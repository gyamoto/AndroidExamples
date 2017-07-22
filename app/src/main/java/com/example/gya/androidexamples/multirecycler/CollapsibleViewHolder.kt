package com.example.gya.androidexamples.multirecycler

import android.graphics.Color
import android.support.annotation.ColorInt
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ImageView
import com.example.gya.androidexamples.R

/**
 * [ImageView]に単色を表示する[RecyclerView.ViewHolder]
 */
class CollapsibleViewHolder(val view: ImageView) : RecyclerView.ViewHolder(view) {

    companion object {
        fun inflate(parent: ViewGroup): CollapsibleViewHolder {
            val view = LayoutInflater.from(parent.context).inflate(R.layout.item_collapsible, parent, false)
            return CollapsibleViewHolder(view as ImageView)
        }
    }

    var color: Int = Color.TRANSPARENT
        set(@ColorInt value) {
            field = value
            view.setBackgroundColor(value)
        }
}

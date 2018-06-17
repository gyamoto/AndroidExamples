package com.example.gya.androidexamples

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView

class ExampleViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

    private val title by lazy { itemView.findViewById<TextView>(R.id.title) }
    private val description by lazy { itemView.findViewById<TextView>(R.id.description) }

    companion object {
        fun inflate(parent: ViewGroup) = ExampleViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.item_example, parent, false)
        )
    }

    fun bind(example: Example) {
        title.text = example.name
        description.text = example.description
    }
}

package com.example.overscrolltodismiss

import android.content.Context
import android.support.v7.recyclerview.extensions.ListAdapter
import android.support.v7.util.DiffUtil
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.util.AttributeSet
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import kotlinx.android.synthetic.main.item_dummy.view.*

class DummyRecyclerView @JvmOverloads constructor(
    context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0
) : RecyclerView(context, attrs, defStyleAttr) {

    private val dummyAdapter = DummyAdapter().apply {
        submitList((1..100).map { it })
    }

    init {
        adapter = dummyAdapter
        layoutManager = LinearLayoutManager(context, RecyclerView.VERTICAL, false)
    }

    class DummyAdapter : ListAdapter<Int, Dummy>(object : DiffUtil.ItemCallback<Int>() {
        override fun areItemsTheSame(old: Int?, new: Int?): Boolean = old == new
        override fun areContentsTheSame(old: Int?, new: Int?): Boolean = old == new
    }) {

        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Dummy {
            return Dummy.create(parent)
        }

        override fun onBindViewHolder(holder: Dummy, position: Int) {
            holder.bind(position)
        }
    }

    class Dummy(itemView: View) : RecyclerView.ViewHolder(itemView) {

        fun bind(position: Int) {
            itemView.text.text = position.toString()
        }

        companion object {
            fun create(parent: ViewGroup): Dummy {
                val inflater = LayoutInflater.from(parent.context)
                val view = inflater.inflate(R.layout.item_dummy, parent, false)
                return Dummy(view)
            }
        }
    }
}

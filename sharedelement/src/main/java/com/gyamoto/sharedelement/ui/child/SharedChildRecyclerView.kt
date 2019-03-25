package com.gyamoto.sharedelement.ui.child

import android.content.Context
import android.support.v7.recyclerview.extensions.ListAdapter
import android.support.v7.util.DiffUtil
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.util.AttributeSet
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.bumptech.glide.Glide
import com.gyamoto.sharedelement.R
import kotlinx.android.synthetic.main.item_child.view.*

class SharedChildRecyclerView @JvmOverloads constructor(
    context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0
) : RecyclerView(context, attrs, defStyleAttr) {

    inner class ViewHolder(
        parent: ViewGroup
    ) : RecyclerView.ViewHolder(
        LayoutInflater.from(parent.context).inflate(R.layout.item_child, parent, false)
    ) {
        fun bind(url: String) {

            Glide.with(context)
                .load(url)
                .into(itemView.image)

            itemView.image.apply {

                transitionName = url

                setOnClickListener {
                    clickImage(it, url)
                }
            }
        }
    }

    inner class Adapter : ListAdapter<String, ViewHolder>(object : DiffUtil.ItemCallback<String>() {

        override fun areItemsTheSame(old: String, new: String): Boolean {
            return old == new
        }

        override fun areContentsTheSame(old: String, new: String): Boolean {
            return old == new
        }
    }) {

        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
            return ViewHolder(parent)
        }

        override fun onBindViewHolder(viewHolder: ViewHolder, position: Int) {
            viewHolder.bind(getItem(position))
        }
    }

    private val innerAdapter = Adapter()

    var urls: List<String> = emptyList()
        set(value) {
            field = value
            innerAdapter.submitList(value)
        }

    var clickImage: (View, String) -> Unit = { _, _ -> }

    init {
        adapter = innerAdapter
        layoutManager = LinearLayoutManager(context, HORIZONTAL, false)
    }
}
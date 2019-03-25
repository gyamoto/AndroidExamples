package com.gyamoto.sharedelement.ui.parent

import android.content.Context
import android.support.v7.recyclerview.extensions.ListAdapter
import android.support.v7.util.DiffUtil
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.util.AttributeSet
import android.view.LayoutInflater
import android.view.ViewGroup
import com.gyamoto.sharedelement.R
import kotlinx.android.synthetic.main.item_parent.view.*

class SharedParentRecyclerView @JvmOverloads constructor(
    context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0
) : RecyclerView(context, attrs, defStyleAttr) {

    data class Images(
        val title: String,
        val urls: List<String>
    )

    inner class ViewHolder(
        parent: ViewGroup
    ) : RecyclerView.ViewHolder(
        LayoutInflater.from(parent.context).inflate(R.layout.item_parent, parent, false)
    ) {
        fun bind(images: Images) {
            itemView.title.text = images.title
            itemView.list.images = images.urls
        }
    }

    inner class Adapter : ListAdapter<Images, ViewHolder>(
        object : DiffUtil.ItemCallback<Images>() {

            override fun areItemsTheSame(old: Images, new: Images): Boolean {
                return old.title == new.title
            }

            override fun areContentsTheSame(old: Images, new: Images): Boolean {
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

    var images: List<Images> = emptyList()
        set(value) {
            field = value
            innerAdapter.submitList(value)
        }

    init {
        adapter = innerAdapter
        layoutManager = LinearLayoutManager(context, RecyclerView.VERTICAL, false)
    }
}
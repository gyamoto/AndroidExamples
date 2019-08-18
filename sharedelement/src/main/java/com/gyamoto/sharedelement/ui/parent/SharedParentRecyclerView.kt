package com.gyamoto.sharedelement.ui.parent

import android.content.Context
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import android.util.AttributeSet
import android.view.LayoutInflater
import android.view.View
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
            itemView.list.apply {
                urls = images.urls
                clickImage = { view, url ->
                    clickChild(view, images.title, url)
                }
            }
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

    var clickChild: (View, String, String) -> Unit = { _, _, _ -> }

    init {
        adapter = innerAdapter
        layoutManager = LinearLayoutManager(
            context,
            RecyclerView.VERTICAL,
            false
        )
    }
}
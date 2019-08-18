package com.gyamoto.viewpagertransformer

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.gyamoto.viewpagertransformer.databinding.ViewContentBinding

class TwoAdapter : ListAdapter<String, TwoAdapter.TwoViewHolder>(
    object : DiffUtil.ItemCallback<String>() {

        override fun areItemsTheSame(old: String, new: String): Boolean = old == new

        override fun areContentsTheSame(old: String, new: String): Boolean = old == new
    }) {

    enum class Contents(val value: String) {
        VIEW("View"),
        PAGER("Pager"),
        TWO("Two")
    }

    init {
        submitList(
            Contents.values().map { it.value }
        )
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TwoViewHolder {
        return TwoViewHolder(parent)
    }

    override fun onBindViewHolder(holder: TwoViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    inner class TwoViewHolder(parent: ViewGroup) :
        RecyclerView.ViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.view_content, parent, false)
        ) {

        private val binding = ViewContentBinding.bind(itemView)

        fun bind(content: String) {

            binding.name.text = content
        }
    }
}

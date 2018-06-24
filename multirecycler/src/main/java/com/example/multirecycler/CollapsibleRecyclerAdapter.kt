package com.example.multirecycler

import android.support.v7.util.DiffUtil
import android.support.v7.widget.RecyclerView
import android.view.ViewGroup
import kotlin.properties.Delegates

/**
 * [CollapsibleViewHolder]を表示する[RecyclerView.Adapter]
 */
class CollapsibleRecyclerAdapter : RecyclerView.Adapter<CollapsibleViewHolder>() {

    companion object {
        private const val VIEW_TYPE = 1
    }

    var itemClickListener: OnCollapsibleClick = { Unit }

    var colors: List<Int> by Delegates.observable(emptyList()) { _, old, new ->
        DiffUtil.calculateDiff(object : DiffUtil.Callback() {

            override fun getOldListSize(): Int = old.size

            override fun getNewListSize(): Int = new.size

            override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean =
                old[oldItemPosition] == new[newItemPosition]

            override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean =
                old[oldItemPosition] == new[newItemPosition]

        }).dispatchUpdatesTo(this)
    }

    override fun getItemCount(): Int = colors.size

    override fun getItemViewType(position: Int): Int =
        VIEW_TYPE

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CollapsibleViewHolder =
        CollapsibleViewHolder.inflate(parent)

    override fun onBindViewHolder(holder: CollapsibleViewHolder, position: Int) {
        holder.color = colors[position]
        holder.itemView.setOnClickListener { itemClickListener.invoke() }
    }
}

/**
 * [CollapsibleViewHolder]をクリックしたときのCallback
 */
typealias OnCollapsibleClick = () -> Unit

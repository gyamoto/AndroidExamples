package com.example.multirecycler

import android.content.Context
import android.graphics.Color
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.util.AttributeSet

/**
 * 開閉する[RecyclerView]
 */
class CollapsableRecyclerView @JvmOverloads constructor(
    context: Context, attrs: AttributeSet? = null, defStyle: Int = 0
) : RecyclerView(context, attrs, defStyle) {

    private val collapsibleAdapter = CollapsibleRecyclerAdapter()

    var background: Int = Color.TRANSPARENT
        set(value) {
            field = value
            setBackgroundColor(value)
        }

    var collapsedColor: Int = Color.BLACK

    var colors: List<Int> = emptyList()
        set(value) {
            field = value
            collapsibleAdapter.colors = value
        }

    var collapsed: Boolean = false
        set(value) {
            field = value
            collapsibleAdapter.colors = if (value) listOf(collapsedColor) else colors
        }

    init {
        layoutManager = LinearLayoutManager(context, VERTICAL, false)
        adapter = collapsibleAdapter
    }

    fun setItemClickListener(onClick: OnCollapsibleClick) {
        collapsibleAdapter.itemClickListener = onClick
    }

}

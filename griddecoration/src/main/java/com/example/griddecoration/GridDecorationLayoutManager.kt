package com.example.griddecoration

import android.content.Context
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView

class GridDecorationLayoutManager(context: Context, val adapter: GridItemAdapter) :
    GridLayoutManager(context, 6, RecyclerView.VERTICAL, false) {

    init {
        spanSizeLookup = object : SpanSizeLookup() {
            override fun getSpanSize(position: Int): Int {
                return spanCount / adapter.getMaxCount(position)
            }
        }
    }
}

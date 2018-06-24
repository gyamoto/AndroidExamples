package com.example.griddecoration

import android.content.Context
import android.support.v7.widget.GridLayoutManager
import android.widget.GridLayout

class GridDecorationLayoutManager(context: Context, val adapter: GridItemAdapter) :
    GridLayoutManager(context, 6, GridLayout.VERTICAL, false) {

    init {
        spanSizeLookup = object : SpanSizeLookup() {
            override fun getSpanSize(position: Int): Int {
                return spanCount / adapter.getMaxCount(position)
            }
        }
    }
}

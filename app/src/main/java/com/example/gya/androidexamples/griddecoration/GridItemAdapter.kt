package com.example.gya.androidexamples.griddecoration

import android.content.Context
import android.graphics.Color
import android.support.annotation.ColorInt
import android.support.annotation.LayoutRes
import android.support.v7.widget.GridLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.GridLayout
import android.widget.ImageView
import com.example.gya.androidexamples.R

sealed class GridItem(@ColorInt val color: Int, val spanSize: Int)
class Large(color: Int) : GridItem(color, 2)
class Small(color: Int) : GridItem(color, 1)

/**
 * 1,2カラム表示をおこなう [RecyclerView.Adapter]
 */
class GridItemAdapter : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    companion object {
        private const val TYPE_LARGE = 0
        private const val TYPE_SMALL = 1
    }

    private val colors = listOf(
            Large(Color.BLACK),
            Small(Color.BLUE),
            Small(Color.CYAN),
            Large(Color.DKGRAY),
            Large(Color.GRAY),
            Small(Color.GREEN),
            Small(Color.LTGRAY),
            Small(Color.MAGENTA),
            Small(Color.RED),
            Large(Color.YELLOW))

    fun getSpanSize(position: Int): Int {
        if (IntRange(0, colors.size - 1).contains(position)) {
            return colors[position].spanSize
        }
        throw IndexOutOfBoundsException()
    }

    override fun getItemCount(): Int = colors.size

    override fun getItemViewType(position: Int): Int {
        return when (colors[position]) {
            is Large -> TYPE_LARGE
            is Small -> TYPE_SMALL
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return when (viewType) {
            TYPE_LARGE -> LargeViewHolder(parent, R.layout.item_large_grid)
            TYPE_SMALL -> SmallViewHolder(parent, R.layout.item_small_grid)
            else -> throw IllegalStateException()
        }
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val item = colors[position]
        when (holder) {
            is LargeViewHolder -> holder.bind(item.color)
            is SmallViewHolder -> holder.bind(item.color)
        }
    }
}

sealed class GridViewHolder(parent: ViewGroup, @LayoutRes layout: Int)
    : RecyclerView.ViewHolder(LayoutInflater.from(parent.context).inflate(layout, parent, false)) {
    private val image by lazy { itemView.findViewById(R.id.image) as ImageView }

    fun bind(@ColorInt color: Int) {
        image.setBackgroundColor(color)
    }
}

class LargeViewHolder(parent: ViewGroup, @LayoutRes layout: Int) : GridViewHolder(parent, layout)
class SmallViewHolder(parent: ViewGroup, @LayoutRes layout: Int) : GridViewHolder(parent, layout)

class GridDecorationLayoutManager(context: Context, val adapter: GridItemAdapter)
    : GridLayoutManager(context, 2, GridLayout.VERTICAL, false) {

    init {
        spanSizeLookup = object : SpanSizeLookup() {
            override fun getSpanSize(position: Int): Int {
                return adapter.getSpanSize(position)
            }
        }
    }
}

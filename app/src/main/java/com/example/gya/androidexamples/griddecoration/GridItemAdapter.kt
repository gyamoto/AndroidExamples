package com.example.gya.androidexamples.griddecoration

import android.graphics.Color
import android.support.annotation.ColorInt
import android.support.v7.widget.RecyclerView
import android.view.ViewGroup
import com.example.gya.androidexamples.R

sealed class GridItem(@ColorInt val color: Int, val maxCount: Int)
class Large(color: Int) : GridItem(color, 1)
class Medium(color: Int) : GridItem(color, 2)
class Small(color: Int) : GridItem(color, 3)

/**
 * 1,2,3カラム表示をおこなう [RecyclerView.Adapter]
 */
class GridItemAdapter : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    companion object {
        private const val TYPE_LARGE = 1
        private const val TYPE_MEDIUM = 2
        private const val TYPE_SMALL = 3
    }

    private val colors = listOf(
        Large(Color.BLACK),
        Medium(Color.BLUE),
        Medium(Color.CYAN),
        Large(Color.DKGRAY),
        Small(Color.GRAY),
        Small(Color.GREEN),
        Small(Color.LTGRAY),
        Medium(Color.MAGENTA),
        Medium(Color.RED),
        Large(Color.YELLOW)
    )

    fun getMaxCount(position: Int): Int {
        if (IntRange(0, colors.size - 1).contains(position)) {
            return colors[position].maxCount
        }
        throw IndexOutOfBoundsException()
    }

    override fun getItemCount(): Int = colors.size

    override fun getItemViewType(position: Int): Int {
        return when (colors[position]) {
            is Large -> TYPE_LARGE
            is Medium -> TYPE_MEDIUM
            is Small -> TYPE_SMALL
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return when (viewType) {
            TYPE_LARGE -> LargeViewHolder(parent, R.layout.item_large_grid)
            TYPE_MEDIUM -> MediumViewHolder(parent, R.layout.item_medium_grid)
            TYPE_SMALL -> SmallViewHolder(parent, R.layout.item_small_grid)
            else -> throw IllegalStateException()
        }
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val item = colors[position]
        when (holder) {
            is LargeViewHolder -> holder.bind(item.color)
            is MediumViewHolder -> holder.bind(item.color)
            is SmallViewHolder -> holder.bind(item.color)
        }
    }
}

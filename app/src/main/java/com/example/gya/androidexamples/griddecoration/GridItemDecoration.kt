package com.example.gya.androidexamples.griddecoration

import android.content.Context
import android.graphics.Rect
import android.support.v7.widget.GridLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.View
import com.example.gya.androidexamples.R

/**
 * [LargeViewHolder] と [MediumViewHolder] と [SmallViewHolder] によって
 * SpanSizeが異なるときも上下左右に等しい余白をもたせる [RecyclerView.ItemDecoration]
 */
class GridItemDecoration(context: Context) : RecyclerView.ItemDecoration() {

    private val margin = context.resources.getDimensionPixelSize(R.dimen.grid_decoration_margin)

    override fun getItemOffsets(
        outRect: Rect,
        view: View,
        parent: RecyclerView,
        state: RecyclerView.State
    ) {
        val params = view.layoutParams as? GridLayoutManager.LayoutParams
        val spanIndex = params?.spanIndex ?: 0
        val spanSize = params?.spanSize ?: 1
        val spanCount = (parent.layoutManager as? GridLayoutManager)?.spanCount ?: 1
        val virtualColumns = spanCount / spanSize

        val isTop = parent.getChildAdapterPosition(view) == 0
        val isStart = spanIndex == 0
        val isEnd = spanIndex + spanSize == spanCount

        outRect.set(
            calculateOffsetStart(isStart, isEnd, virtualColumns),
            calculateOffsetTop(isTop),
            calculateOffsetEnd(isStart, isEnd, virtualColumns),
            margin
        )
    }

    private fun calculateOffsetTop(isTop: Boolean): Int {
        return if (isTop) margin else 0
    }

    private fun calculateOffsetStart(isStart: Boolean, isEnd: Boolean, columns: Int): Int {
        return when {
            isStart -> margin
            isEnd -> margin / columns
            else -> margin * (columns - 1) / columns
        }
    }

    private fun calculateOffsetEnd(isStart: Boolean, isEnd: Boolean, columns: Int): Int {
        return when {
            isStart -> margin / columns
            isEnd -> margin
            else -> margin * (columns - 1) / columns
        }
    }
}

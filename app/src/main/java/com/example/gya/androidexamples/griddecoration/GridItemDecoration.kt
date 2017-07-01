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

    override fun getItemOffsets(outRect: Rect, view: View, parent: RecyclerView, state: RecyclerView.State) {
        val manager = parent.layoutManager as? GridLayoutManager
        val params = view.layoutParams as? GridLayoutManager.LayoutParams
        val isTop = parent.getChildAdapterPosition(view) == 0
        val isStart = params?.spanIndex?.let { it == 0 } ?: false
        val isEnd = params?.let { it.spanIndex + it.spanSize == manager?.spanCount } ?: false

        when (parent.findContainingViewHolder(view)) {
            is LargeViewHolder -> {
                outRect.set(margin, if (isTop) margin else 0, margin, margin)
            }
            is MediumViewHolder -> {
                outRect.set(
                        if (isStart) margin else margin / 2,
                        if (isTop) margin else 0,
                        if (isEnd) margin else margin / 2,
                        margin)
            }
            is SmallViewHolder -> {
                outRect.set(
                        if (isStart) margin else if (isEnd) margin / 3 else margin * 2 / 3,
                        if (isTop) margin else 0,
                        if (isStart) margin / 3 else if (isEnd) margin else margin * 2 / 3,
                        margin)
            }
        }
    }
}

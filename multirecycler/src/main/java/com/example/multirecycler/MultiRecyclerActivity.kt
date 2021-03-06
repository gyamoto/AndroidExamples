package com.example.multirecycler

import android.animation.ValueAnimator
import android.content.Context
import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.RecyclerView
import android.widget.LinearLayout

/**
 * [RecyclerView]を2重に表示する[AppCompatActivity]
 */
class MultiRecyclerActivity : AppCompatActivity() {

    companion object {
        fun start(context: Context) {
            context.startActivity(Intent(context, MultiRecyclerActivity::class.java))
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_multi_recycler)

        val first: CollapsableRecyclerView = findViewById(R.id.first)
        val second: CollapsableRecyclerView = findViewById(R.id.second)

        first.background = Color.GRAY
        first.collapsedColor = Color.WHITE
        first.colors = listOf(
            Color.RED, Color.GREEN, Color.BLUE,
            Color.RED, Color.GREEN, Color.BLUE,
            Color.RED, Color.GREEN, Color.BLUE
        )
        first.collapsed = true
        first.setItemClickListener { collapse(first, second) }

        second.background = Color.GRAY
        second.collapsedColor = Color.BLACK
        second.colors = listOf(
            Color.CYAN, Color.MAGENTA, Color.YELLOW,
            Color.CYAN, Color.MAGENTA, Color.YELLOW,
            Color.CYAN, Color.MAGENTA, Color.YELLOW
        )
        second.collapsed = false
        second.setItemClickListener { collapse(second, first) }
    }

    private fun collapse(own: CollapsableRecyclerView, other: CollapsableRecyclerView) {

        if (own.collapsed.not()) return

        own.collapsed = false
        other.collapsed = true

        ValueAnimator.ofFloat(0f, 1f)
            .apply {
                duration = 300L
                addUpdateListener {
                    val weight = it.animatedValue as Float
                    own.layoutParams = (own.layoutParams as LinearLayout.LayoutParams)
                        .apply { this.weight = weight }
                    other.layoutParams = (other.layoutParams as LinearLayout.LayoutParams)
                        .apply { this.weight = 1f - weight }
                }
            }.start()

    }
}

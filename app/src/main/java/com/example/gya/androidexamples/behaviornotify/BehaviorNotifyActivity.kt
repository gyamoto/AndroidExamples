package com.example.gya.androidexamples.behaviornotify

import android.content.Context
import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import android.support.annotation.ColorInt
import android.support.design.widget.CoordinatorLayout
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.GridLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import com.example.gya.androidexamples.R

/**
 * [RecyclerView] のスクロールを [CoordinatorLayout.Behavior] でうけとる [AppCompatActivity]
 */
class BehaviorNotifyActivity : AppCompatActivity() {

    companion object {
        fun start(context: Context) {
            context.startActivity(Intent(context, BehaviorNotifyActivity::class.java))
        }
    }

    private val recycler by lazy { findViewById<RecyclerView>(R.id.recycler) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_behavior_notify)

        val adapter = object : RecyclerView.Adapter<ImageViewHolder>() {

            private val colors = listOf(
                    Color.BLACK, Color.BLUE, Color.CYAN, Color.DKGRAY, Color.GRAY,
                    Color.GREEN, Color.LTGRAY, Color.MAGENTA, Color.RED, Color.YELLOW)

            override fun getItemCount(): Int = colors.size

            override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ImageViewHolder {
                return ImageViewHolder(
                        LayoutInflater.from(parent.context).inflate(R.layout.item_behavior_notify_color, parent, false))
            }

            override fun onBindViewHolder(holder: ImageViewHolder, position: Int) {
                holder.bind(colors[position])
            }
        }
        recycler.adapter = adapter
        recycler.layoutManager = GridLayoutManager(this, 2)
        recycler.setHasFixedSize(true)
    }

    inner class ImageViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val image by lazy { itemView.findViewById<ImageView>(R.id.image) }

        fun bind(@ColorInt color: Int) {
            image.setBackgroundColor(color)
        }
    }
}

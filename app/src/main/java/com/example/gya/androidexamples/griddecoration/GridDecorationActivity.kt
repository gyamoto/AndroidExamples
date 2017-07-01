package com.example.gya.androidexamples.griddecoration

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.RecyclerView
import com.example.gya.androidexamples.R

/**
 * GridLayoutManagerでViewHolder/SpanSizeごとにDecorationするActivity
 */
class GridDecorationActivity : AppCompatActivity() {

    private val recycler by lazy { findViewById(R.id.recycler) as RecyclerView }

    companion object {
        fun start(context: Context) {
            context.startActivity(Intent(context, GridDecorationActivity::class.java))
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_grid_decoration)

        val adapter = GridItemAdapter()
        recycler.adapter = adapter
        recycler.layoutManager = GridDecorationLayoutManager(this, adapter)
        recycler.setHasFixedSize(true)
    }
}

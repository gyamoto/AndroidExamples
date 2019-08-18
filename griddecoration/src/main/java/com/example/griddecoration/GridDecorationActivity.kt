package com.example.griddecoration

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView

/**
 * [RecyclerView.ViewHolder] と[GridLayoutManager.getSpanSize] によって
 * [RecyclerView.ItemDecoration] を切り替える [AppCompatActivity]
 */
class GridDecorationActivity : AppCompatActivity() {

    private val recycler by lazy { findViewById<RecyclerView>(R.id.recycler) }

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
        recycler.layoutManager =
                GridDecorationLayoutManager(this, adapter)
        recycler.addItemDecoration(GridItemDecoration(this))
        recycler.setHasFixedSize(true)
    }
}

package com.example.overscrolltodismiss

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_over_scroll_to_dismiss_list.*

class OverScrollToDismissListActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_over_scroll_to_dismiss_list)

        recycler.setOnClickListener {
            DismissRecyclerActivity.start(this)
        }
        coordinator.setOnClickListener {
            DismissCoordinatorActivity.start(this)
        }
        nestedScroll.setOnClickListener {
            DismissNestedScrollActivity.start(this)
        }
    }

    companion object {

        fun start(context: Context) {
            context.startActivity(
                Intent(context, OverScrollToDismissListActivity::class.java)
            )
        }
    }
}

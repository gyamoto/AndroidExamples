package com.example.overscrolltodismiss

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_over_scroll_to_dismiss.*

class OverScrollToDismissActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_over_scroll_to_dismiss)

        val chromeFader = ElasticDragDismissFrameLayout.SystemChromeFader(this)
        elasticContainer.callback = chromeFader
    }

    companion object {

        fun start(context: Context) {
            context.startActivity(
                Intent(context, OverScrollToDismissActivity::class.java)
            )
        }
    }
}

package com.example.overscrolltodismiss

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_dismiss_coordinator.*

class DismissCoordinatorActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_dismiss_coordinator)

        val chromeFader = ElasticDragDismissFrameLayout.SystemChromeFader(this)
        elasticContainer.callback = chromeFader
    }

    companion object {

        fun start(context: Context) {
            context.startActivity(
                Intent(context, DismissCoordinatorActivity::class.java)
            )
        }
    }
}

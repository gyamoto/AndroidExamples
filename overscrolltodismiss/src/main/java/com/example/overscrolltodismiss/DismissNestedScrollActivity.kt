package com.example.overscrolltodismiss

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_dismiss_nested_scroll.*

class DismissNestedScrollActivity : AppCompatActivity() {

    private val chromeFader by lazy { ElasticDragDismissFrameLayout.SystemChromeFader(this) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_dismiss_nested_scroll)
    }

    override fun onStart() {
        super.onStart()
        elasticContainer.callback = chromeFader
    }

    override fun onStop() {
        elasticContainer.callback = null
        super.onStop()
    }

    companion object {

        fun start(context: Context) {
            context.startActivity(
                Intent(context, DismissNestedScrollActivity::class.java)
            )
        }
    }
}

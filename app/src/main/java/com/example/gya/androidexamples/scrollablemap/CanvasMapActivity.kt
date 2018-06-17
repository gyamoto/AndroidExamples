package com.example.gya.androidexamples.scrollablemap

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.example.gya.androidexamples.R

class CanvasMapActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_canvas_map)
    }

    companion object {

        fun start(context: Context) {
            context.startActivity(Intent(context, CanvasMapActivity::class.java))
        }
    }

}

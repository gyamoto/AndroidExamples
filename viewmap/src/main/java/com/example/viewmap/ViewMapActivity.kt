package com.example.viewmap

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity

class ViewMapActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_view_map)
    }

    companion object {

        fun start(context: Context) {
            context.startActivity(Intent(context, ViewMapActivity::class.java))
        }
    }

}

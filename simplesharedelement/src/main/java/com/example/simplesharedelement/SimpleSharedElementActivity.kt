package com.example.simplesharedelement

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity

class SimpleSharedElementActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_simple_shared_element)

        if (savedInstanceState == null) {
            supportFragmentManager.beginTransaction()
                .replace(R.id.container, FirstFragment())
                .commit()
        }
    }

    companion object {

        const val TRANSITION_NAME = "logo"

        fun start(context: Context) {
            context.startActivity(
                Intent(context, SimpleSharedElementActivity::class.java)
            )
        }
    }
}

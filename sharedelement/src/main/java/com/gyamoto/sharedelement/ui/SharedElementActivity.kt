package com.gyamoto.sharedelement.ui

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.gyamoto.sharedelement.R
import com.gyamoto.sharedelement.ui.parent.SharedParentRecyclerView
import kotlinx.android.synthetic.main.activity_shared_element.*
import kotlinx.android.synthetic.main.content_shared_element.*

class SharedElementActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_shared_element)
        setSupportActionBar(toolbar)

        list.images = listOf(

            SharedParentRecyclerView.Images(
                title = "Animal",
                urls = List(10) { "https://placeimg.com/720/720/animals#$it" }
            ),
            SharedParentRecyclerView.Images(
                title = "Nature",
                urls = List(10) { "https://placeimg.com/720/720/nature#$it" }
            ),
            SharedParentRecyclerView.Images(
                title = "Tech",
                urls = List(10) { "https://placeimg.com/720/720/tech#$it" }
            )
        )
    }

    companion object {

        fun start(context: Context) {
            context.startActivity(
                Intent(context, SharedElementActivity::class.java)
            )
        }
    }

}

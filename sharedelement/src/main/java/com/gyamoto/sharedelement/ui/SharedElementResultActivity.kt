package com.gyamoto.sharedelement.ui

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityOptionsCompat
import androidx.core.os.bundleOf
import com.bumptech.glide.Glide
import com.gyamoto.sharedelement.R
import kotlinx.android.synthetic.main.activity_shared_element_result.*
import kotlinx.android.synthetic.main.content_shared_element_result.*

class SharedElementResultActivity : AppCompatActivity() {

    private val title: String? by lazy { intent.getStringExtra(EXTRA_TITLE) }
    private val imageUrl: String? by lazy { intent.getStringExtra(EXTRA_IMAGE_URL) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_shared_element_result)
        setSupportActionBar(toolbar)

        Glide.with(this)
            .load(imageUrl)
            .into(image)
        imageTitle.text = title
        imageDescription.text = imageUrl
    }

    companion object {

        private const val EXTRA_TITLE = "title"
        private const val EXTRA_IMAGE_URL = "imageUrl"

        fun start(activity: AppCompatActivity, view: View, title: String, imageUrl: String) {
            activity.startActivity(
                Intent(activity, SharedElementResultActivity::class.java).putExtras(
                    bundleOf(
                        EXTRA_TITLE to title,
                        EXTRA_IMAGE_URL to imageUrl
                    )
                ),
                ActivityOptionsCompat.makeSceneTransitionAnimation(
                    activity,
                    view,
                    activity.getString(R.string.transition_card)
                ).toBundle()
            )
        }
    }

}

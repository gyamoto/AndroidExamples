package com.gyamoto.viewpagertransformer

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import com.gyamoto.viewpagertransformer.databinding.ActivityViewPagerTransformerBinding
import com.gyamoto.viewpagertransformer.one.DepthPageTransformerOne
import com.gyamoto.viewpagertransformer.one.OneAdapter
import com.gyamoto.viewpagertransformer.one.ZoomOutPageTransformerOne
import com.gyamoto.viewpagertransformer.two.DepthPageTransformerTwo
import com.gyamoto.viewpagertransformer.two.TwoAdapter
import com.gyamoto.viewpagertransformer.two.ZoomOutPageTransformerTwo

class ViewPagerTransformerActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val binding: ActivityViewPagerTransformerBinding =
            DataBindingUtil.setContentView(this, R.layout.activity_view_pager_transformer)

        binding.apply {

            val horizontalPadding = (resources.displayMetrics.density * 32).toInt()

            val oneAdapter = OneAdapter()
            oneNormal.adapter = oneAdapter
            oneZoom.apply {
                adapter = oneAdapter
                setPageTransformer(true, ZoomOutPageTransformerOne())
            }
            oneDepth.apply {
                adapter = oneAdapter
                setPageTransformer(true, DepthPageTransformerOne())
            }
            onePadding.apply {
                adapter = oneAdapter
                clipToPadding = false
                setPadding(horizontalPadding, 0, horizontalPadding, 0)
            }

            val twoAdapter = TwoAdapter()
            twoNormal.adapter = twoAdapter
            twoZoom.apply {
                adapter = twoAdapter
                setPageTransformer(ZoomOutPageTransformerTwo())
            }
            twoDepth.apply {
                adapter = twoAdapter
                setPageTransformer(DepthPageTransformerTwo())
            }
            twoPadding.apply {
                adapter = twoAdapter
                clipToPadding = false
                clipChildren = false
                offscreenPageLimit = 3
                setPadding(horizontalPadding, 0, horizontalPadding, 0)
            }
        }
    }

    companion object {

        fun start(context: Context) {
            context.startActivity(
                Intent(context, ViewPagerTransformerActivity::class.java)
            )
        }
    }
}
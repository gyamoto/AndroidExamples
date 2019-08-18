package com.gyamoto.viewpagertransformer

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import com.gyamoto.viewpagertransformer.databinding.ActivityViewPagerTransformerBinding

class ViewPagerTransformerActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val binding: ActivityViewPagerTransformerBinding =
            DataBindingUtil.setContentView(this, R.layout.activity_view_pager_transformer)

        binding.apply {

            one.adapter = OneAdapter().apply {
//                notifyDataSetChanged()
            }

            two.adapter = TwoAdapter()
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
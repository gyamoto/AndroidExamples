package com.example.gya.androidexamples.scrollablemap

import android.content.Context
import android.support.design.widget.FloatingActionButton
import android.util.AttributeSet
import android.widget.FrameLayout

class ViewMapMarkerView @JvmOverloads constructor(
    context: Context, attrs: AttributeSet? = null, defStyle: Int = 0
) : FloatingActionButton(context, attrs, defStyle) {

    init {
        val size = (resources.displayMetrics.density * 24).toInt()
        layoutParams = FrameLayout.LayoutParams(size, size)
    }

    var positionRatioX: Float = 0.5f
    var positionRatioY: Float = 0.5f

    fun updateScaleAndFocus(
        scaleX: Float,
        scaleY: Float,
        translateX: Float,
        translateY: Float
    ) {
        translationX = positionRatioX * scaleX + translateX
        translationY = positionRatioY * scaleY + translateY
    }

}

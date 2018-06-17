package com.example.gya.androidexamples.scrollablemap

import android.content.Context
import android.support.design.widget.FloatingActionButton
import android.util.AttributeSet
import android.widget.FrameLayout

class ViewMapMarkerView @JvmOverloads constructor(
    context: Context, attrs: AttributeSet? = null, defStyle: Int = 0
) : FloatingActionButton(context, attrs, defStyle) {

    private val ovalSize = (resources.displayMetrics.density * 24).toInt()

    init {
        layoutParams = FrameLayout.LayoutParams(ovalSize, ovalSize)
    }

    var positionRatioX: Float = 0.5f
    var positionRatioY: Float = 0.5f

    fun updateScaleAndFocus(
        scaleX: Float,
        scaleY: Float,
        translateX: Float,
        translateY: Float
    ) {
        translationX = positionRatioX * scaleX + translateX - ovalSize / 2f
        translationY = positionRatioY * scaleY + translateY - ovalSize / 2f
    }

}

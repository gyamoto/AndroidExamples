package com.example.overscrolltodismiss

import android.support.annotation.ColorInt
import android.support.annotation.FloatRange
import android.support.annotation.IntRange

@ColorInt
fun Int.modifyAlpha(@IntRange(from = 0, to = 255) alpha: Int): Int {
    return (this and 0x00ffffff) or alpha.shl(24)
}

@ColorInt
fun Int.modifyAlpha(@FloatRange(from = 0.0, to = 1.0) alpha: Float): Int {
    return this.modifyAlpha((255 * alpha).toInt())
}

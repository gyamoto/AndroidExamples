package com.example.overscrolltodismiss

import android.content.Context

fun Context.isNavBarOnBottom(): Boolean {

    val displayMetrics = resources.displayMetrics
    val configuration = resources.configuration
    val canMove = displayMetrics.widthPixels != displayMetrics.heightPixels &&
            configuration.smallestScreenWidthDp < 600
    return !canMove || displayMetrics.widthPixels < displayMetrics.heightPixels
}

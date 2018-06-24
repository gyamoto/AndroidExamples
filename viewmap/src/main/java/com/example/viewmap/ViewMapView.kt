package com.example.viewmap

import android.annotation.SuppressLint
import android.content.Context
import android.graphics.Matrix
import android.support.constraint.ConstraintLayout
import android.support.v4.view.GestureDetectorCompat
import android.util.AttributeSet
import android.view.GestureDetector
import android.view.MotionEvent
import android.view.ScaleGestureDetector
import android.view.View
import androidx.core.graphics.values

class ViewMapView @JvmOverloads constructor(
    context: Context, attrs: AttributeSet? = null, defStyle: Int = 0
) : ConstraintLayout(context, attrs, defStyle),
    View.OnTouchListener,
    GestureDetector.OnGestureListener,
    ScaleGestureDetector.OnScaleGestureListener {

    private val gestureDetector = GestureDetectorCompat(context, this)
    private val scaleGestureDetector = ScaleGestureDetector(context, this)

    private var mapWidth: Int
    private var mapHeight: Int
    private var focusX: Float
    private var focusY: Float
    private var scaleFactor: Float = 1f

    private var mapMatrix: Matrix = Matrix()

    init {
        View.inflate(context, R.layout.view_map_view, this)

        bg_map.setOnTouchListener(this)

        val count = 50
        (0 until count)
            .map { it * 360f / count + 90f }
            .map { it % 360 }
            .map { it / 180 * Math.PI }
            .map {
                ViewMapMarkerView(context).apply {
                    positionRatioX = Math.cos(it).toFloat() * 0.3f + 0.5f
                    positionRatioY = Math.sin(it).toFloat() * 0.3f + 0.5f
                }
            }.forEach {
                marker_container += it
            }

        mapHeight = bg_map.drawable.intrinsicHeight
        mapWidth = bg_map.drawable.intrinsicWidth
        focusX = mapWidth / 2f
        focusY = mapHeight / 2f

        updateMatrix()
    }

    @SuppressLint("ClickableViewAccessibility")
    override fun onTouch(v: View, event: MotionEvent): Boolean {
        gestureDetector.onTouchEvent(event)
        scaleGestureDetector.onTouchEvent(event)
        updateMatrix()

        return true
    }

    @SuppressLint("SetTextI18n")
    private fun updateMatrix() {

        // update background image
        bg_map.imageMatrix = mapMatrix

        // update foreground marker
        val matrixArray = mapMatrix.values()
        marker_container.children.forEach {
            (it as? ViewMapMarkerView)?.updateScaleAndFocus(
                matrixArray[0] * mapWidth,
                matrixArray[4] * mapHeight,
                matrixArray[2],
                matrixArray[5]
            )
        }

        // notify debug log
        debug_info.text = """
            x: $focusX
            y: $focusY
            scale: $scaleFactor

            matrix:
            ${matrixArray[0]}, ${matrixArray[1]}, ${matrixArray[2]}
            ${matrixArray[3]}, ${matrixArray[4]}, ${matrixArray[5]}
            ${matrixArray[6]}, ${matrixArray[7]}, ${matrixArray[8]}
        """.trimIndent()
    }

    override fun onShowPress(e: MotionEvent?) = Unit

    override fun onSingleTapUp(e: MotionEvent?): Boolean = true

    override fun onDown(e: MotionEvent?): Boolean = true

    override fun onFling(
        e1: MotionEvent?,
        e2: MotionEvent?,
        velocityX: Float,
        velocityY: Float
    ): Boolean = true

    override fun onScroll(
        e1: MotionEvent?,
        e2: MotionEvent?,
        distanceX: Float,
        distanceY: Float
    ): Boolean {
        mapMatrix.postTranslate(-distanceX, -distanceY)

        return true
    }

    override fun onLongPress(e: MotionEvent?) = Unit

    override fun onScaleBegin(detector: ScaleGestureDetector): Boolean = true

    override fun onScaleEnd(detector: ScaleGestureDetector) = Unit

    override fun onScale(detector: ScaleGestureDetector): Boolean {
        mapMatrix.postScale(
            detector.scaleFactor, detector.scaleFactor, detector.focusX, detector.focusY
        )

        return true
    }
}

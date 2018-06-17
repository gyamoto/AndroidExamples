package com.example.gya.androidexamples.scrollablemap

import android.annotation.SuppressLint
import android.content.Context
import android.graphics.Canvas
import android.graphics.Matrix
import android.support.v4.view.GestureDetectorCompat
import android.util.AttributeSet
import android.view.GestureDetector
import android.view.MotionEvent
import android.view.ScaleGestureDetector
import android.view.View
import android.widget.ImageView

class CanvasMapView @JvmOverloads constructor(
    context: Context, attrs: AttributeSet? = null, defStyle: Int = 0
) : ImageView(context, attrs, defStyle),
    View.OnTouchListener,
    GestureDetector.OnGestureListener,
    ScaleGestureDetector.OnScaleGestureListener {

    private val gestureDetector = GestureDetectorCompat(context, this)
    private val scaleGestureDetector = ScaleGestureDetector(context, this)

    private var mapWidth: Float = width.toFloat()
    private var mapHeight: Float = height.toFloat()
    private var mapMatrix: Matrix = Matrix()

    private var scaleFactor: Float = 1f
    private var focusX: Float = 0f
    private var focusY: Float = 0f

    override fun onSizeChanged(w: Int, h: Int, oldw: Int, oldh: Int) {
        super.onSizeChanged(w, h, oldw, oldh)

        mapWidth = w.toFloat()
        mapHeight = h.toFloat()
    }

    override fun onDraw(canvas: Canvas?) {
        super.onDraw(canvas)


    }

    @SuppressLint("ClickableViewAccessibility")
    override fun onTouch(v: View, event: MotionEvent): Boolean {
        gestureDetector.onTouchEvent(event)
        scaleGestureDetector.onTouchEvent(event)

        return true
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
        focusX -= distanceX
        focusY -= distanceY

        return true
    }

    override fun onLongPress(e: MotionEvent?) = Unit

    override fun onScaleBegin(detector: ScaleGestureDetector): Boolean = true

    override fun onScaleEnd(detector: ScaleGestureDetector) = Unit

    override fun onScale(detector: ScaleGestureDetector): Boolean {
        scaleFactor *= detector.scaleFactor

        return true
    }
}

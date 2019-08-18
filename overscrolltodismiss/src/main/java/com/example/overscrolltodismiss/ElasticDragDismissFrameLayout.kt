package com.example.overscrolltodismiss

import android.content.Context
import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.util.AttributeSet
import android.view.MotionEvent
import android.view.View
import android.view.animation.AnimationUtils
import android.widget.FrameLayout
import androidx.core.content.res.getDimensionOrThrow
import androidx.core.content.res.getFloatOrThrow
import androidx.core.content.withStyledAttributes
import com.example.overscrolltodismiss.ElasticDragDismissFrameLayout.ElasticDragDismissCallback

/**
 * NestedScrollすると[ElasticDragDismissCallback]をコールする[FrameLayout]
 *
 * https://github.com/nickbutcher/plaid/blob/aea144/app/src/main/java/io/plaidapp/ui/widget/ElasticDragDismissFrameLayout.java
 *
 * TODO: resume/pauseまわりをLifecycleObserverで https://developer.android.com/topic/libraries/architecture/lifecycle
 */
class ElasticDragDismissFrameLayout @JvmOverloads constructor(
    context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0
) : FrameLayout(context, attrs, defStyleAttr) {

    private var dragDismissDistance = Float.MAX_VALUE
    private var dragDismissFraction = -1f
    private var dragDismissScale = 1f
    private var dragElasticity = 0.8f
    private var shouldScale = false

    private var totalDrag = 0f
    private var draggingUp = false
    private var draggingDown = false
    private var lastActionEvent = 0

    private val interpolator =
        AnimationUtils.loadInterpolator(context, android.R.interpolator.fast_out_slow_in)

    var callback: ElasticDragDismissCallback? = null

    init {

        context.withStyledAttributes(
            attrs,
            R.styleable.ElasticDragDismissFrameLayout
        ) {

            if (hasValue(R.styleable.ElasticDragDismissFrameLayout_dragDismissDistance)) {
                dragDismissDistance = getDimensionOrThrow(
                    R.styleable.ElasticDragDismissFrameLayout_dragDismissDistance
                )
            } else if (hasValue(R.styleable.ElasticDragDismissFrameLayout_dragDismissFraction)) {
                dragDismissFraction = getFloatOrThrow(
                    R.styleable.ElasticDragDismissFrameLayout_dragDismissFraction
                )
            }

            if (hasValue(R.styleable.ElasticDragDismissFrameLayout_dragDismissScale)) {
                dragDismissScale = getFloatOrThrow(
                    R.styleable.ElasticDragDismissFrameLayout_dragDismissScale
                )
                shouldScale = dragDismissScale != 1f
            }

            if (hasValue(R.styleable.ElasticDragDismissFrameLayout_dragElasticity)) {
                dragElasticity = getFloatOrThrow(
                    R.styleable.ElasticDragDismissFrameLayout_dragElasticity
                )
            }
        }

    }

    override fun onStartNestedScroll(child: View, target: View, nestedScrollAxes: Int): Boolean {
        return (nestedScrollAxes and View.SCROLL_AXIS_VERTICAL) != 0
    }

    override fun onNestedPreScroll(target: View, dx: Int, dy: Int, consumed: IntArray) {
        if (draggingUp && dy < 0 || draggingDown && dy > 0) {
            dragScale(dy)
            consumed[1] = dy
        }
    }

    override fun onNestedScroll(
        target: View, dxConsumed: Int, dyConsumed: Int, dxUnconsumed: Int, dyUnconsumed: Int
    ) {
        dragScale(dyUnconsumed)
    }

    override fun onInterceptTouchEvent(ev: MotionEvent): Boolean {
        lastActionEvent = ev.action
        return super.onInterceptTouchEvent(ev)
    }

    override fun onStopNestedScroll(child: View) {
        if (Math.abs(totalDrag) >= dragDismissDistance) {
            dispatchDismissCallback()
        } else {
            when (lastActionEvent) {
                MotionEvent.ACTION_DOWN -> {
                    translationY = 0f
                    scaleX = 1f
                    scaleY = 1f
                }
                else -> {
                    animate()
                        .translationY(0f)
                        .scaleX(1f)
                        .scaleY(1f)
                        .setDuration(200L)
                        .setInterpolator(interpolator)
                        .setListener(null)
                        .start()
                }
            }
            totalDrag = 0f
            draggingUp = false
            draggingDown = false
            dispatchDragCallback(0f, 0f, 0f, 0f)
        }
    }

    override fun onSizeChanged(w: Int, h: Int, oldw: Int, oldh: Int) {
        super.onSizeChanged(w, h, oldw, oldh)
        if (dragDismissFraction > 0f) {
            dragDismissDistance = h * dragDismissFraction
        }
    }

    private fun dragScale(scroll: Int) {
        if (scroll == 0) return

        totalDrag += scroll

        if (scroll > 0 && !draggingUp && !draggingDown) {
            draggingUp = true
            if (shouldScale) pivotY = 0f
        } else if (scroll < 0 && !draggingUp && !draggingDown) {
            draggingDown = true
            if (shouldScale) pivotY = height.toFloat()
        }

        var dragFraction = Math.log10(1.0 + Math.abs(totalDrag) / dragDismissDistance).toFloat()
        var dragTo = (dragFraction * dragDismissDistance * dragElasticity)

        dragTo = if (draggingUp) dragTo * -1f else dragTo

        translationY = dragTo

        if (shouldScale) {
            val scale = 1 - (1 - dragDismissScale) * dragFraction
            scaleX = scale
            scaleY = scale
        }

        if ((draggingUp && totalDrag <= 0) || (draggingDown && totalDrag >= 0)) {
            totalDrag = 0f
            dragTo = 0f
            dragFraction = 0f
            draggingUp = false
            draggingDown = false

            translationY = 0f
            scaleX = 1f
            scaleY = 1f
        }

        dispatchDragCallback(
            elasticOffset = dragFraction,
            elasticOffsetPixels = dragTo,
            rawOffset = Math.min(1f, Math.abs(totalDrag) / dragDismissDistance),
            rawOffsetPixels = totalDrag
        )

    }

    private fun dispatchDragCallback(
        elasticOffset: Float,
        elasticOffsetPixels: Float,
        rawOffset: Float,
        rawOffsetPixels: Float
    ) {

        callback?.onDrag(elasticOffset, elasticOffsetPixels, rawOffset, rawOffsetPixels)
    }

    private fun dispatchDismissCallback() {
        callback?.onDragDismissed()
    }

    interface ElasticDragDismissCallback {

        fun onDrag(
            elasticOffset: Float,
            elasticOffsetPixels: Float,
            rawOffset: Float,
            rawOffsetPixels: Float
        )

        fun onDragDismissed()
    }

    /**
     * [ElasticDragDismissFrameLayout]がNestedScrollしたとき、
     * [activity]を閉じる[ElasticDragDismissCallback]
     */
    class SystemChromeFader(private val activity: AppCompatActivity) : ElasticDragDismissCallback {

        private val statusBarAlpha: Float = Color.alpha(activity.window.statusBarColor) / 255f
        private val navBarAlpha: Float = Color.alpha(activity.window.navigationBarColor) / 255f
        private val fadeNavBar: Boolean = activity.isNavBarOnBottom()

        override fun onDrag(
            elasticOffset: Float,
            elasticOffsetPixels: Float,
            rawOffset: Float,
            rawOffsetPixels: Float
        ) {

            when {
                elasticOffsetPixels > 0 -> {
                    activity.window.statusBarColor =
                            activity.window.statusBarColor.modifyAlpha((1f - rawOffset) * statusBarAlpha)
                }
                elasticOffsetPixels == 0f -> {
                    activity.window.statusBarColor =
                            activity.window.statusBarColor.modifyAlpha(statusBarAlpha)
                    activity.window.navigationBarColor =
                            activity.window.navigationBarColor.modifyAlpha(navBarAlpha)
                }
                fadeNavBar -> {
                    activity.window.navigationBarColor =
                            activity.window.navigationBarColor.modifyAlpha((1f - rawOffset) * navBarAlpha)
                }
            }
        }

        override fun onDragDismissed() {
            activity.finishAfterTransition()
        }
    }

}

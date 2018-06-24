package com.example.behaviornotify

import android.content.Context
import android.support.design.widget.CoordinatorLayout
import android.support.v7.widget.RecyclerView
import android.util.AttributeSet
import android.util.Log
import android.view.View
import android.widget.FrameLayout
import android.widget.TextView

class ScrollNotifyView @JvmOverloads constructor(
    context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0
) : FrameLayout(context, attrs, defStyleAttr), CoordinatorLayout.AttachedBehavior {

    private val status by lazy { findViewById<TextView>(R.id.status) }

    init {
        View.inflate(context, R.layout.view_behavior_notify_scroll, this)
        status.text = "scroll status"
    }

    override fun getBehavior(): CoordinatorLayout.Behavior<*> =
        NotifyBehavior()

    class NotifyBehavior : CoordinatorLayout.Behavior<ScrollNotifyView>() {

        override fun layoutDependsOn(
            parent: CoordinatorLayout?,
            child: ScrollNotifyView?,
            dependency: View?
        ): Boolean {
            return dependency is RecyclerView
        }

        override fun onStartNestedScroll(
            coordinatorLayout: CoordinatorLayout,
            child: ScrollNotifyView,
            directTargetChild: View,
            target: View,
            axes: Int,
            type: Int
        ): Boolean {
            Log.i("NotifyBehavior", "onStartNestedScroll")
            return axes == View.SCROLL_AXIS_VERTICAL
        }

        override fun onNestedScroll(
            coordinatorLayout: CoordinatorLayout,
            child: ScrollNotifyView,
            target: View,
            dxConsumed: Int,
            dyConsumed: Int,
            dxUnconsumed: Int,
            dyUnconsumed: Int,
            type: Int
        ) {
            super.onNestedScroll(
                coordinatorLayout,
                child,
                target,
                dxConsumed,
                dyConsumed,
                dxUnconsumed,
                dyUnconsumed,
                type
            )
            Log.i("NotifyBehavior", "onNestedScroll")
            Log.i("NotifyBehavior", "    dyConsumed: $dyConsumed")
            Log.i("NotifyBehavior", "    dyUnsonsumed: $dyUnconsumed")

            if (0 < dyConsumed) {
                child.status.text = "DOWN"
            } else if (dyConsumed == 0) {
                child.status.text = "STOP"
            } else if (dyConsumed < 0) {
                child.status.text = "UP"
            }
        }
    }
}

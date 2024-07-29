package com.renard.annuairealfa3a

import android.view.MotionEvent
import android.view.View
import kotlin.math.abs

class SwipeGestureListener(private val onSwipeRight: () -> Unit, private val onSwipeLeft: () -> Unit) : View.OnTouchListener {

    private var downX: Float = 0.0f
    private var downY: Float = 0.0f
    private val swipeThreshold = 150

    override fun onTouch(v: View?, event: MotionEvent?): Boolean {
        when (event?.action) {
            MotionEvent.ACTION_DOWN -> {
                downX = event.x
                downY = event.y
                return false
            }
            MotionEvent.ACTION_UP -> {
                val upX = event.x
                val upY = event.y

                val deltaX = upX - downX
                val deltaY = upY - downY

                if (abs(deltaX) > abs(deltaY) && abs(deltaX) > swipeThreshold) {
                    if (deltaX > 0) {
                        onSwipeRight()
                    } else {
                        onSwipeLeft()
                    }
                    return true
                }
            }
        }
        return false
    }
}

package com.phoenixoverlord.pravega.utils

import android.content.Context
import android.util.AttributeSet
import android.view.MotionEvent
import androidx.viewpager.widget.ViewPager


class SwipeDisabledViewPager(context: Context, attrs: AttributeSet) : ViewPager(context, attrs) {

    var shouldInterceptTouch: Boolean = false

    override fun onTouchEvent(event: MotionEvent): Boolean {
        return if (shouldInterceptTouch) {
            super.onTouchEvent(event)
        } else false
    }

    override fun onInterceptTouchEvent(event: MotionEvent): Boolean {
        return if (shouldInterceptTouch) {
            super.onInterceptTouchEvent(event)
        } else false
    }
}
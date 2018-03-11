package com.ladevelopers.wayv.drivers.qa.views

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.AccelerateInterpolator
import android.view.animation.AlphaAnimation
import android.view.animation.Animation
import android.widget.FrameLayout
import com.ladevelopers.wayv.drivers.qa.R
import kotlinx.android.synthetic.main.view_busy_layout.view.*

class BusyLayout : FrameLayout {
    constructor(context: Context) : super(context) {
        init(null, 0)
    }

    constructor(context: Context, attrs: AttributeSet) : super(context, attrs) {
        init(attrs, 0)
    }

    constructor(context: Context, attrs: AttributeSet, defStyle: Int) : super(context, attrs, defStyle) {
        init(attrs, defStyle)
    }

    private var isBusy: Boolean = false
    private var busyChildCount = 0

    private fun init(attrs: AttributeSet?, defStyle: Int) {
        val a = context.obtainStyledAttributes(
                attrs, R.styleable.BusyLayout, defStyle, 0)
        setIsBusy(a.getBoolean(R.styleable.BusyLayout_isBusy, false))
        a.recycle()
        val inflater = context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
        inflater.inflate(R.layout.view_busy_layout, this)
        busyChildCount = childCount
    }

    fun setIsBusy(value: Boolean) {
        if (value == isBusy) return
        isBusy = value

        backdrop_view.clearAnimation()
        val animation: Animation

        if (value) {
            progress_bar.visibility = View.VISIBLE
            backdrop_view.visibility = View.VISIBLE
            animation = AlphaAnimation(0F, 0.7F).apply {
                interpolator = AccelerateInterpolator()
                duration = 500
                fillAfter = true
            }
        } else {
            progress_bar.visibility = View.GONE
            animation = AlphaAnimation(0.7F, 0F).apply { duration = 50 }
            animation.setAnimationListener(object : Animation.AnimationListener {
                override fun onAnimationRepeat(p0: Animation?) {}
                override fun onAnimationStart(p0: Animation?) {}
                override fun onAnimationEnd(p0: Animation?) {
                    backdrop_view.visibility = View.GONE
                }
            })
        }

        backdrop_view.startAnimation(animation)
    }

    override fun addView(child: View?, index: Int, params: ViewGroup.LayoutParams?) =
            super.addView(child, if (busyChildCount == 0) -1 else childCount - busyChildCount, params)
}

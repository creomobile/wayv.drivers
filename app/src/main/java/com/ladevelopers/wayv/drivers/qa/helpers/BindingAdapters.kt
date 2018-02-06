package com.ladevelopers.wayv.drivers.qa.helpers

import android.databinding.BindingAdapter
import android.widget.ViewSwitcher

@BindingAdapter("android:showFirstView")
fun setShowFirstView(viewSwitcher: ViewSwitcher, showFirstView: Boolean?) {
    viewSwitcher.displayedChild = if (showFirstView == false) 1 else 0
}
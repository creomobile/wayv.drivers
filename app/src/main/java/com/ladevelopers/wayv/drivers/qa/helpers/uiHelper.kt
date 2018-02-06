package com.ladevelopers.wayv.drivers.qa.helpers

import android.text.Editable
import android.text.TextWatcher
import android.view.animation.AlphaAnimation
import android.widget.EditText
import android.widget.ViewSwitcher

fun EditText.moveCursorToEndAfterTextChanged() {
    this.addTextChangedListener(object : TextWatcher {
        override fun afterTextChanged(p0: Editable?) {
            this@moveCursorToEndAfterTextChanged.setSelection(this@moveCursorToEndAfterTextChanged.length())
        }

        override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
        }

        override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
        }
    })
}

fun ViewSwitcher.setFadeAnimation() {
    val inAnimation = AlphaAnimation(0f, 1f)
    inAnimation.duration = 200
    val outAnimation = AlphaAnimation(1f, 0f)
    outAnimation.duration = 200

    this.inAnimation = inAnimation
    this.outAnimation = outAnimation
}

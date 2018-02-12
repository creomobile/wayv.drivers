package com.ladevelopers.wayv.drivers.qa.helpers

import android.databinding.ObservableBoolean

class ProcessIndicator : ObservableBoolean() {

    private var counter: Int = 0
        set(value) {
            field = value
            super.set(value > 0)
        }

    override fun set(value: Boolean) = throw UnsupportedOperationException()

    fun begin(): AutoCloseable {
        counter++
        return AutoCloseable { counter-- }
    }
}

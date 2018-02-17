package com.ladevelopers.wayv.drivers.qa.services

import android.content.Context
import android.os.Handler
import android.util.Log
import com.ladevelopers.wayv.drivers.qa.contracts.ErrorHandler
import android.widget.Toast
import android.os.Looper
import javax.inject.Inject

class ErrorHandlerImpl @Inject constructor(private val context: Context) : ErrorHandler {

    override fun handle(throwable: Throwable) {
        Log.d("App exception", throwable.toString())
        val handler = Handler(Looper.getMainLooper())
        handler.post({
            Toast.makeText(context, throwable.message ?: throwable.javaClass.simpleName,
                    Toast.LENGTH_SHORT).show()
        })
    }
}

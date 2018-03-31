package com.ladevelopers.wayv.drivers.qa.features.login

import android.annotation.SuppressLint
import android.arch.lifecycle.ViewModel
import android.content.Context
import android.databinding.ObservableBoolean
import android.databinding.ObservableField
import android.os.Handler
import android.os.Looper
import android.widget.Toast
import com.ladevelopers.wayv.drivers.qa.contracts.*
import com.ladevelopers.wayv.drivers.qa.dto.SignedInDto
import com.ladevelopers.wayv.drivers.qa.helpers.ProcessIndicator
import com.ladevelopers.wayv.drivers.qa.helpers.TelephonyHelper
import io.reactivex.schedulers.Schedulers
import kotlinx.coroutines.experimental.CompletableDeferred
import kotlinx.coroutines.experimental.android.UI
import kotlinx.coroutines.experimental.launch
import kotlinx.coroutines.experimental.rx2.await
import kotlinx.coroutines.experimental.rx2.awaitSingle
import javax.inject.Inject

@SuppressLint("StaticFieldLeak")
class LoginViewModel @Inject constructor(
        private val authApiService: AuthApiService,
        private val errorHandler: ErrorHandler,
        private val context: Context)
    : ViewModel() {

    val codeRequestBusy = ProcessIndicator()
    private lateinit var codeRequestClosable: AutoCloseable
    val signinBusy = ProcessIndicator()
    val phone: ObservableField<String> = object : ObservableField<String>() {
        override fun set(value: String?) {
            super.set(TelephonyHelper.formatPhone(value))
            canRequestCode.set(TelephonyHelper.unformatPhone(value).length == 10)
        }
    }
    val canRequestCode = ObservableBoolean()
    val showCodeEntering = ObservableBoolean(false)
    val code: ObservableField<String> = object : ObservableField<String>() {
        override fun set(value: String?) {
            super.set(value)

            fun setChar(observable: ObservableField<String>, index: Int) =
                    observable.set(if (value?.length ?: 0 > index) value!![index].toString() else "")
            setChar(code1, 0)
            setChar(code2, 1)
            setChar(code3, 2)
            setChar(code4, 3)

            if (value?.length == 4)
                signin()
        }
    }
    val code1 = ObservableField<String>()
    val code2 = ObservableField<String>()
    val code3 = ObservableField<String>()
    val code4 = ObservableField<String>()

    val result = CompletableDeferred<SignedInDto>()

    fun requestCode() = launch(UI) {
        try {
            codeRequestClosable = codeRequestBusy.begin()
            val result = authApiService
                    .requestCode(TelephonyHelper.unformatPhone(phone.get()))
                    .subscribeOn(Schedulers.io())
                    //.observeOn(AndroidSchedulers.mainThread())
                    .awaitSingle()

            code.set(null)

            if (result.otp.isNotEmpty())
                Toast.makeText(this@LoginViewModel.context, result.otp, Toast.LENGTH_SHORT).show()

            showCodeEntering.set(true)
        } catch (ex: Exception) {
            errorHandler.handle(ex)
            codeRequestClosable.close()
        }
    }

    fun back() {
        codeRequestClosable.close()
        showCodeEntering.set(false)
    }

    fun signin() = launch(UI) {
        try {
            signinBusy.begin().use {
                val dto = authApiService
                        .signin(code.get()!!, TelephonyHelper.unformatPhone(phone.get()))
                        .subscribeOn(Schedulers.io())
                        //.observeOn(AndroidSchedulers.mainThread())
                        .awaitSingle()
                result.complete(dto)
            }
        } catch (ex: Exception) {
            errorHandler.handle(ex)
            back()
        }
    }
}

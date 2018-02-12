package com.ladevelopers.wayv.drivers.qa.features.login

import android.arch.lifecycle.ViewModel
import android.databinding.ObservableBoolean
import android.databinding.ObservableField
import com.google.gson.Gson
import com.ladevelopers.wayv.drivers.qa.contracts.AuthService
import com.ladevelopers.wayv.drivers.qa.contracts.ErrorHandler
import com.ladevelopers.wayv.drivers.qa.dto.CompanyLicenseDto
import com.ladevelopers.wayv.drivers.qa.dto.LicenseType
import com.ladevelopers.wayv.drivers.qa.dto.LocationDto
import com.ladevelopers.wayv.drivers.qa.helpers.ProcessIndicator
import com.ladevelopers.wayv.drivers.qa.helpers.TelephonyHelper
import kotlinx.coroutines.experimental.android.UI
import kotlinx.coroutines.experimental.launch
import kotlinx.coroutines.experimental.rx2.await
import javax.inject.Inject
import com.google.gson.GsonBuilder


class LoginViewModel @Inject constructor(
        private val authService: AuthService,
        private val errorHandler: ErrorHandler)
    : ViewModel() {

    val codeRequestBusy = ProcessIndicator()
    private lateinit var codeRequestClosable: AutoCloseable
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
        }
    }
    val code1 = ObservableField<String>()
    val code2 = ObservableField<String>()
    val code3 = ObservableField<String>()
    val code4 = ObservableField<String>()

    fun requestCode() = launch(UI) {
        try {
            codeRequestClosable = codeRequestBusy.begin()
            authService.requestCode(TelephonyHelper.unformatPhone(phone.get())).await()
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
}

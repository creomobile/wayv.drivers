package com.ladevelopers.wayv.drivers.qa.features.login

import android.arch.lifecycle.ViewModel
import android.databinding.ObservableBoolean
import android.databinding.ObservableField
import android.view.View
import com.ladevelopers.wayv.drivers.qa.contracts.LoginService
import com.ladevelopers.wayv.drivers.qa.helpers.TelephonyHelper
import com.ladevelopers.wayv.drivers.qa.infrastructure.App
import javax.inject.Inject

class LoginViewModel @Inject constructor(private val loginService: LoginService) : ViewModel() {

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

    fun requestCode() {
        loginService.requestCode(TelephonyHelper.unformatPhone(phone.get()))
        showCodeEntering.set(true)

//        AlertDialog.Builder(view.context)
//                .setTitle("LoginVm")
//                .setMessage("Request '${TelephonyHelper.unformatPhone(phone.get())}'...")
//                .setIcon(android.R.drawable.ic_dialog_info)
//                .show()
    }

    fun back() = showCodeEntering.set(false)
}

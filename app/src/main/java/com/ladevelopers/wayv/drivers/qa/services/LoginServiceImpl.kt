package com.ladevelopers.wayv.drivers.qa.services

import android.util.Log
import com.ladevelopers.wayv.drivers.qa.contracts.LoginService


class LoginServiceImpl : LoginService {

    override fun requestCode(phoneNumber: String) {
        Log.d("Login Service", phoneNumber)
    }
}

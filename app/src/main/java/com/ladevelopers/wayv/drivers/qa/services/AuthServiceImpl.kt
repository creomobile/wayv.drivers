package com.ladevelopers.wayv.drivers.qa.services

import com.ladevelopers.wayv.drivers.qa.contracts.AuthApiService
import com.ladevelopers.wayv.drivers.qa.contracts.AuthService
import com.ladevelopers.wayv.drivers.qa.contracts.requestCode
import io.reactivex.Completable
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

class AuthServiceImpl
@Inject constructor(private val authApiService: AuthApiService)
    : AuthService {

    override fun requestCode(phoneNumber: String): Completable {
        return authApiService.requestCode(phoneNumber)
                //.observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
    }
}

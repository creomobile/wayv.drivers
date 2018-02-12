package com.ladevelopers.wayv.drivers.qa.contracts

import io.reactivex.Completable

interface AuthService {
    fun requestCode(phoneNumber: String): Completable
}

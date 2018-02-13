package com.ladevelopers.wayv.drivers.qa.contracts

import com.ladevelopers.wayv.drivers.qa.dto.SignedInDto
import com.ladevelopers.wayv.drivers.qa.helpers.Optional
import io.reactivex.Observable

interface AuthService {
    val authInfo: Observable<Optional<SignedInDto>>
    val currentAuthInfo: SignedInDto?
    fun logout()
    fun login(authInfo: SignedInDto)
}

val AuthService.token: String
    get() = this.currentAuthInfo?.auth?.token
            ?: throw throw IllegalStateException("Cannot get auth token")

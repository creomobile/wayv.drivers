package com.ladevelopers.wayv.drivers.qa.contracts

import com.ladevelopers.wayv.drivers.qa.dto.SignedInDto
import com.ladevelopers.wayv.drivers.qa.helpers.Optional
import io.reactivex.Observable

interface AuthService {
    val authInfo: Observable<Optional<SignedInDto>>
    val currentAuthInfo: SignedInDto?
    fun signout()
    fun signin(authInfo: SignedInDto)
}

val AuthService.isSignedIn: Boolean
    get() = this.currentAuthInfo != null
val AuthService.token: String
    get() = this.currentAuthInfo?.auth?.token
            ?: throw throw IllegalStateException("Cannot get auth token")

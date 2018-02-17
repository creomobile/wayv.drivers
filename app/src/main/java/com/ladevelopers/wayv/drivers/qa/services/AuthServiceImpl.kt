package com.ladevelopers.wayv.drivers.qa.services

import com.ladevelopers.wayv.drivers.qa.contracts.AuthService
import com.ladevelopers.wayv.drivers.qa.contracts.LocalStorage
import com.ladevelopers.wayv.drivers.qa.contracts.getOrNull
import com.ladevelopers.wayv.drivers.qa.dto.SignedInDto
import com.ladevelopers.wayv.drivers.qa.helpers.Optional
import io.reactivex.subjects.BehaviorSubject
import javax.inject.Inject

class AuthServiceImpl
@Inject constructor(private val localStorage: LocalStorage) : AuthService {
    private val authKey = "authInfo"
    private val subject = BehaviorSubject.createDefault(
            Optional(localStorage.getOrNull<SignedInDto>(authKey)))
    override val authInfo = this.subject!!
    override val currentAuthInfo: SignedInDto? get() = subject.value.value

    override fun signout() {
        localStorage.remove(authKey)
        subject.onNext(Optional(null))
    }

    override fun signin(authInfo: SignedInDto) {
        localStorage.set(authKey, authInfo)
        subject.onNext(Optional(authInfo))
    }
}

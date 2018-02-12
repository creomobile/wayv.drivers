package com.ladevelopers.wayv.drivers.qa.services

import com.ladevelopers.wayv.drivers.qa.contracts.*
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

    override fun logout() {
        localStorage.remove(authKey)
        subject.onNext(Optional(null))
    }

    override fun login(authInfo: SignedInDto) {
        localStorage.set(authKey, authInfo)
        subject.onNext(Optional(authInfo))
    }
}

package com.ladevelopers.wayv.drivers.qa.contracts

import com.ladevelopers.wayv.drivers.qa.dto.*
import io.reactivex.Completable
import io.reactivex.Observable
import retrofit2.http.*

interface AuthApiService {

    @POST("auth/otp")
    fun requestCode(@Body dto: OtpDto): Completable

    @POST("auth/otpsignin")
    fun signin(@Body dto: OtpSignInDto): Observable<SignedInDto>
}

fun AuthApiService.requestCode(phone: String) = this.requestCode(OtpDto(phone))
fun AuthApiService.signin(otp: String, phone: String) = this.signin(OtpSignInDto(otp, phone))

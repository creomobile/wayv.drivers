package com.ladevelopers.wayv.drivers.qa.contracts

import com.ladevelopers.wayv.drivers.qa.dto.OtpDto
import io.reactivex.Completable
import retrofit2.http.*

interface AuthApiService {

    @POST("auth/otp")
    fun requestCode(@Body otpDto: OtpDto) : Completable

    //auth/otpsignin
}

fun AuthApiService.requestCode(phone: String) = this.requestCode(OtpDto(phone))

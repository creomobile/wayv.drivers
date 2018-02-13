package com.ladevelopers.wayv.drivers.qa.contracts

import com.ladevelopers.wayv.drivers.qa.dto.SignedInDto
import io.reactivex.Observable
import retrofit2.http.POST

interface ApiService {

    @POST("auth/renew")
    fun renewToken(): Observable<SignedInDto>
}

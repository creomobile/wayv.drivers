package com.ladevelopers.wayv.drivers.qa.dto

import com.google.gson.annotations.SerializedName

data class JwtAuthDto(
        @SerializedName("expires_in") val expiresIn: Int,
        val token: String
)

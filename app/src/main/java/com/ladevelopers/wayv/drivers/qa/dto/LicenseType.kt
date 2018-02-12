package com.ladevelopers.wayv.drivers.qa.dto

import com.google.gson.annotations.SerializedName

enum class LicenseType(private val value: Int) {
    @SerializedName("1")
    Recreational(1),
    @SerializedName("2")
    Medical(2)
}

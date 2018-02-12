package com.ladevelopers.wayv.drivers.qa.dto

import com.google.gson.annotations.SerializedName

data class WarehouseDto(
        @SerializedName("location_id") val locationId: Int?,
        val name: String?
)


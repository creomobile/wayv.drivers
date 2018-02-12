package com.ladevelopers.wayv.drivers.qa.dto

import com.google.gson.annotations.SerializedName

abstract class LocationDtoBase {
    abstract val id: Int
    abstract val streetAddress: String?
    abstract val city: String?
    abstract val zipCode: String?
    abstract val state: String?
    abstract val phone: String?
    abstract val latitude: Double?
    abstract val longitude: Double?
}

data class LocationDto(
        override val id: Int,
        @SerializedName("street_address") override val streetAddress: String?,
        override val city: String?,
        @SerializedName("zip_code") override val zipCode: String?,
        override val state: String?,
        override val phone: String?,
        @SerializedName("lat") override val latitude: Double?,
        @SerializedName("lng") override val longitude: Double?
) : LocationDtoBase()


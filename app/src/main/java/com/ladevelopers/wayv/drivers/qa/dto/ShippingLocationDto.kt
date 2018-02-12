package com.ladevelopers.wayv.drivers.qa.dto

import com.google.gson.annotations.SerializedName

data class ShippingLocationDto(

        //LocationDto
        override val id: Int,
        @SerializedName("street_address") override val streetAddress: String?,
        override val city: String?,
        @SerializedName("zip_code") override val zipCode: String?,
        override val state: String?,
        override val phone: String?,
        @SerializedName("lat") override val latitude: Double?,
        @SerializedName("lng") override val longitude: Double?,

        @SerializedName("is_verified") val isVerified: Boolean,
        @SerializedName("is_default") val isDefault: Boolean,
        @SerializedName("company_id") val companyId: Int?

) : LocationDtoBase()
package com.ladevelopers.wayv.drivers.qa.dto

import com.google.gson.annotations.SerializedName

data class CompanyDto(
        val id: Int,
        val name: String,
        val roles: List<CompanyRoleDto>,
        val licenses: List<CompanyLicenseDto>,
        @SerializedName("default_shipping_location_id") val defaultShippingLocationId: Int?,
        @SerializedName("shipping_locations") val shippingLocations: List<ShippingLocationDto>,
        val verified: Boolean?,
        val phone: String,
        val email: String,
        @SerializedName("payments_cards") val paymentsCards: List<PaymentInfoDto>
)

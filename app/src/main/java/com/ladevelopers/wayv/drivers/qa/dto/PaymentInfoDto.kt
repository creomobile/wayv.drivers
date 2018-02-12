package com.ladevelopers.wayv.drivers.qa.dto

import com.google.gson.annotations.SerializedName

data class PaymentInfoDto(
        @SerializedName("card_id") val cardId: String?,
        @SerializedName("verification_code") val verificationCode: String?,
        val owner: String?,
        @SerializedName("payment_name") val paymentName: String?,
        @SerializedName("postal_code") val postalCode: String?,
        @SerializedName("expiration_date") val expirationDate: String?,
        val street: String?,
        val city: String?,
        @SerializedName("company_id") val companyId: Int?,
        @SerializedName("created_by") val createdBy: Int?
)

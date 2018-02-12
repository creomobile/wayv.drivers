package com.ladevelopers.wayv.drivers.qa.dto

import com.google.gson.annotations.SerializedName

data class CompanyLicenseDto(

        //CreateCompanyLicenseDto
        @SerializedName("license_no") override val licenseNo: String?,
        @SerializedName("license_type_id") override val licenseType: LicenseType?,
        override val location: LocationDto?,
        @SerializedName("company_id") override val companyId: Int?,

        val id: Int,
        @SerializedName("is_active") val isActive: Boolean,
        @SerializedName("is_verified") val isVerified: Boolean
) : CreateCompanyLicenseDtoBase()

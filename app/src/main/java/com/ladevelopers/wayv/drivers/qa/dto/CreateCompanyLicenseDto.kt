package com.ladevelopers.wayv.drivers.qa.dto

abstract class CreateCompanyLicenseDtoBase {
    abstract val licenseNo: String?
    abstract val licenseType: LicenseType?
    abstract val location: LocationDto?
    abstract val companyId: Int?
}

/*data class CreateCompanyLicenseDto(
        @SerializedName("license_no") override val licenseNo: String?,
        @SerializedName("license_type_id") override val licenseType: LicenseType?,
        override val location: LocationDto?,
        @SerializedName("company_id") override val companyId: Int?
) : CreateCompanyLicenseDtoBase()*/

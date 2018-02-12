package com.ladevelopers.wayv.drivers.qa.dto

import com.google.gson.annotations.SerializedName

data class SignedInDto(

        //UserDto
        override val id: Int,
        @SerializedName("first_name") override val firstName: String?,
        @SerializedName("last_name") override val lastName: String?,
        override val email: String?,
        override val phone: String?,
        @SerializedName("photo_public_image_id") override val photoPublicImageId: String?,
        override val employee: EmployeeDto?,

        val auth: JwtAuthDto,
        @SerializedName("active_company") val activeCompany: CompanyDto

) : UserDtoBase()

package com.ladevelopers.wayv.drivers.qa.dto

import com.google.gson.annotations.SerializedName

abstract class UserDtoBase {
    abstract val id: Int
    abstract val firstName: String?
    abstract val lastName: String?
    abstract val email: String?
    abstract val phone: String?
    abstract val photoPublicImageId: String?
    abstract val employee: EmployeeDto?
}

data class UserDto(
        override val id: Int,
        @SerializedName("first_name") override val firstName: String?,
        @SerializedName("last_name") override val lastName: String?,
        override val email: String?,
        override val phone: String?,
        @SerializedName("photo_public_image_id") override val photoPublicImageId: String?,
        override val employee: EmployeeDto?
) : UserDtoBase()

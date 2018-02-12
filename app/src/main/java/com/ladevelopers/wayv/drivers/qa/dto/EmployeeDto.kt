package com.ladevelopers.wayv.drivers.qa.dto

import com.google.gson.annotations.SerializedName

data class EmployeeDto(
        val user: UserDto?,
        val roles: List<EmployeeRoleDto>?,
        @SerializedName("active_role") val activeRole: EmployeeRoleDto?,
        val warehouse: WarehouseDto?,
        @SerializedName("driver_license") val driverLicense: String?
)


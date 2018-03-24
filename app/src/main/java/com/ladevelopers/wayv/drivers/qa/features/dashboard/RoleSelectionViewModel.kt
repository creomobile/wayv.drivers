package com.ladevelopers.wayv.drivers.qa.features.dashboard

import android.arch.lifecycle.ViewModel
import com.ladevelopers.wayv.drivers.qa.dto.EmployeeRoleDto

class RoleSelectionViewModel(
        private val role: EmployeeRoleDto,
        private val selectListener: (EmployeeRoleDto) -> Unit) : ViewModel() {
    val roleName = role.name.capitalize()
    fun select() = selectListener(role)
}
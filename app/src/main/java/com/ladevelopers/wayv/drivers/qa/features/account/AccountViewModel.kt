package com.ladevelopers.wayv.drivers.qa.features.account

import android.arch.lifecycle.ViewModel
import com.ladevelopers.wayv.drivers.qa.contracts.AuthService
import com.ladevelopers.wayv.drivers.qa.helpers.TelephonyHelper
import javax.inject.Inject

class AccountViewModel @Inject constructor(authService: AuthService) : ViewModel() {

    val userId: Int?
    val imageId: String?
    val userName: String?
    val email: String?
    val phone: String?
    val driverLicense: String?

    init {
        val info = authService.currentAuthInfo
        userId = info?.id
        imageId = info?.photoPublicImageId
        userName = if (info == null) null else "${info.firstName} ${info.lastName}"
        email = info?.email
        phone = TelephonyHelper.formatPhone(info?.phone)
        driverLicense = info?.employee?.driverLicense
    }
}

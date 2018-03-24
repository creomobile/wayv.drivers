package com.ladevelopers.wayv.drivers.qa.features.dashboard

import android.annotation.SuppressLint
import android.arch.lifecycle.ViewModel
import android.content.Context
import android.content.Intent
import android.databinding.ObservableBoolean
import android.databinding.ObservableField
import android.net.Uri
import android.support.v4.content.ContextCompat.startActivity
import com.ladevelopers.wayv.drivers.qa.contracts.AuthService
import com.ladevelopers.wayv.drivers.qa.dto.EmployeeRoleDto
import com.ladevelopers.wayv.drivers.qa.helpers.ProcessIndicator
import io.reactivex.Completable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import kotlinx.coroutines.experimental.android.UI
import kotlinx.coroutines.experimental.launch
import kotlinx.coroutines.experimental.rx2.await
import java.util.concurrent.TimeUnit
import javax.inject.Inject

@SuppressLint("StaticFieldLeak")
class DashboardViewModel @Inject constructor(
        authService: AuthService,
        private val context: Context)
    : ViewModel() {

    val warehouseName = ObservableField<String>()
    val warehouseLocation = ObservableField<String>()
    val availableRoles = ObservableField<String>()
    val isRolesSelectionShowing = ObservableBoolean(true)
    val roleSelections = ObservableField<List<RoleSelectionViewModel>>()
    val busy = ProcessIndicator()

    init {
        authService.authInfo
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe {
                    val employee = it.value?.employee
                    val warehouse = employee?.warehouse
                    warehouseName.set(warehouse?.name)
                    warehouseLocation.set(warehouse?.location?.toString())
                    val roles = employee?.roles
                    availableRoles.set(roles?.joinToString { it.name.capitalize() })
                    roleSelections.set(
                            roles?.map { RoleSelectionViewModel(it, { role -> selectRole(role) }) })
                }
    }

    fun callToWarehouse() {
        val intent = Intent(Intent.ACTION_DIAL, Uri.parse("tel:+1 833-843-9298"))
        startActivity(context, intent, null)
    }

    fun mailToWarehouse() {
        val intent = Intent(Intent.ACTION_SENDTO, Uri.fromParts(
                "mailto", "support@wayv.com", null))
        startActivity(context, Intent.createChooser(intent, "Send email..."), null)
    }

    private fun selectRole(role: EmployeeRoleDto) = launch(UI) {
        busy.begin().use {
            Completable.timer(1, TimeUnit.SECONDS, Schedulers.io()).await()
            isRolesSelectionShowing.set(false)
        }
    }

    fun goOffline() = launch(UI) {
        busy.begin().use {
            Completable.timer(1, TimeUnit.SECONDS, Schedulers.io()).await()
            isRolesSelectionShowing.set(false)
        }
    }

    fun showRolesSelection() = isRolesSelectionShowing.set(true)
}

package com.ladevelopers.wayv.drivers.qa.infrastructure.modules

import android.arch.lifecycle.*
import com.ladevelopers.wayv.drivers.qa.features.account.AccountViewModel
import com.ladevelopers.wayv.drivers.qa.features.dashboard.DashboardViewModel
import com.ladevelopers.wayv.drivers.qa.features.login.LoginViewModel
import com.ladevelopers.wayv.drivers.qa.infrastructure.*
import dagger.*
import dagger.multibindings.IntoMap

@Module
abstract class ViewModelModule {

    @Binds
    @IntoMap
    @ViewModelKey(LoginViewModel::class)
    abstract fun bindLoginViewModel(loginViewModel: LoginViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(AccountViewModel::class)
    abstract fun bindAccountViewModel(accountViewModel: AccountViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(DashboardViewModel::class)
    abstract fun bindDashboardViewModel(dashboardViewModel: DashboardViewModel): ViewModel

    @Binds
    abstract fun bindViewModelFactory(factory: ViewModelFactory):
            ViewModelProvider.Factory
}
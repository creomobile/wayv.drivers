package com.ladevelopers.wayv.drivers.qa.infrastructure

import com.ladevelopers.wayv.drivers.qa.features.account.AccountFragment
import com.ladevelopers.wayv.drivers.qa.features.dashboard.DashboardFragment
import com.ladevelopers.wayv.drivers.qa.features.login.LoginActivity
import com.ladevelopers.wayv.drivers.qa.infrastructure.modules.*
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [
    AppModule::class,
    ServicesModule::class,
    ViewModelModule::class])
interface AppComponent {
    fun inject(target: LoginActivity)
    fun inject(target: SplashActivity)
    fun inject(target: AccountFragment)
    fun inject(target: DashboardFragment)
}

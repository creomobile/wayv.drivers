package com.ladevelopers.wayv.drivers.qa.infrastructure

import com.ladevelopers.wayv.drivers.qa.features.account.AccountFragment
import com.ladevelopers.wayv.drivers.qa.features.dashboard.DashboardFragment
import com.ladevelopers.wayv.drivers.qa.features.login.LoginActivity
import com.ladevelopers.wayv.drivers.qa.features.main.MainActivity
import com.ladevelopers.wayv.drivers.qa.infrastructure.modules.*
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [
    AppModule::class,
    ServicesModule::class,
    ViewModelModule::class,
    FeaturesModule::class])
interface AppComponent {
    fun inject(target: App)
    fun inject(target: LoginActivity)
    fun inject(target: SplashActivity)
    fun inject(target: MainActivity)
    fun inject(target: AccountFragment)
    fun inject(target: DashboardFragment)
}

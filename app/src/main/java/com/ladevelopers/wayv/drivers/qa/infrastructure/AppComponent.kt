package com.ladevelopers.wayv.drivers.qa.infrastructure

import com.ladevelopers.wayv.drivers.qa.features.login.LoginViewModel
import com.ladevelopers.wayv.drivers.qa.infrastructure.modules.*
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [
    AppModule::class,
    ServicesModule::class])
interface AppComponent {
    fun inject(target: LoginViewModel)
}

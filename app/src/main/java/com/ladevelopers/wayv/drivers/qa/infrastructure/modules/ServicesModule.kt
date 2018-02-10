package com.ladevelopers.wayv.drivers.qa.infrastructure.modules

import com.ladevelopers.wayv.drivers.qa.contracts.LoginService
import com.ladevelopers.wayv.drivers.qa.services.LoginServiceImpl
import dagger.*
import javax.inject.Singleton

@Module
class ServicesModule {

    @Provides
    @Singleton
    fun provideLoginService(): LoginService = LoginServiceImpl()
}
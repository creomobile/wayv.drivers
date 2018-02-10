package com.ladevelopers.wayv.drivers.qa.infrastructure.modules

import android.app.Application
import android.content.Context
import dagger.*
import javax.inject.Singleton

@Module
class AppModule(private val application: Application) {

    @Provides
    @Singleton
    fun provideContext(): Context = application
}

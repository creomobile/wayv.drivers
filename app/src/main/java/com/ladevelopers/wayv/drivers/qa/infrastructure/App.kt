package com.ladevelopers.wayv.drivers.qa.infrastructure

import android.app.Application
import com.ladevelopers.wayv.drivers.qa.infrastructure.modules.AppModule

class App : Application() {

    lateinit var component: AppComponent

    private fun initComponent(): AppComponent = DaggerAppComponent
            .builder()
            .appModule(AppModule(this))
            .build()

    override fun onCreate() {
        super.onCreate()
        component = initComponent()
    }
}
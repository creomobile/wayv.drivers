package com.ladevelopers.wayv.drivers.qa.infrastructure

import android.app.Activity
import android.app.Application
import android.os.Bundle
import com.ladevelopers.wayv.drivers.qa.features.Feature
import com.ladevelopers.wayv.drivers.qa.features.main.MainActivity
import com.ladevelopers.wayv.drivers.qa.infrastructure.modules.AppModule
import javax.inject.Inject

class App : Application() {

    lateinit var component: AppComponent

    @Inject
    lateinit var features: Set<@JvmSuppressWildcards Feature>

    private fun initComponent(): AppComponent = DaggerAppComponent
            .builder()
            .appModule(AppModule(this))
            .build()

    override fun onCreate() {
        super.onCreate()
        component = initComponent()
        component.inject(this)

        registerActivityLifecycleCallbacks(object : ActivityLifecycleCallbacks {
            override fun onActivityPaused(activity: Activity?) = Unit
            override fun onActivityResumed(activity: Activity?) = Unit
            override fun onActivityStarted(activity: Activity?) = Unit
            override fun onActivityDestroyed(activity: Activity?) = Unit
            override fun onActivitySaveInstanceState(activity: Activity?, outState: Bundle?) = Unit
            override fun onActivityStopped(activity: Activity?) = Unit

            override fun onActivityCreated(activity: Activity?, savedInstanceState: Bundle?) =
                    features.forEach { it.setActivity(activity as? MainActivity) }
        })
    }
}
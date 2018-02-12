package com.ladevelopers.wayv.drivers.qa.infrastructure.modules

import android.content.Context
import com.ladevelopers.wayv.drivers.qa.contracts.AuthApiService
import com.ladevelopers.wayv.drivers.qa.contracts.AuthService
import com.ladevelopers.wayv.drivers.qa.contracts.ErrorHandler
import com.ladevelopers.wayv.drivers.qa.contracts.LocalStorage
import com.ladevelopers.wayv.drivers.qa.services.AuthServiceImpl
import com.ladevelopers.wayv.drivers.qa.services.ErrorHandlerImpl
import com.ladevelopers.wayv.drivers.qa.services.LocalStorageImpl
import dagger.*
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
class ServicesModule {

    @Provides
    @Singleton
    fun provideErrorHandler(context: Context): ErrorHandler = ErrorHandlerImpl(context)

    @Provides
    @Singleton
    fun provideLocalStorage(context: Context): LocalStorage = LocalStorageImpl(context)

    @Provides
    @Singleton
    fun provideAuthApiService(): AuthApiService = Retrofit
            .Builder()
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .addConverterFactory(GsonConverterFactory.create())
            .baseUrl("https://drop-api-qa.azurewebsites.net/")
            .build()
            .create(AuthApiService::class.java)

    @Provides
    @Singleton
    fun provideLoginService(localStorage: LocalStorage): AuthService = AuthServiceImpl(localStorage)
}
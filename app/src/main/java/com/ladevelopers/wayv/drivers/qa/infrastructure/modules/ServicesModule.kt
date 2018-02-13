package com.ladevelopers.wayv.drivers.qa.infrastructure.modules

import android.content.Context
import com.ladevelopers.wayv.drivers.qa.contracts.*
import com.ladevelopers.wayv.drivers.qa.services.AuthServiceImpl
import com.ladevelopers.wayv.drivers.qa.services.ErrorHandlerImpl
import com.ladevelopers.wayv.drivers.qa.services.LocalStorageImpl
import dagger.*
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.Response
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Named
import javax.inject.Singleton

@Module
class ServicesModule {

    companion object {
        private const val NAME_BASE_URL = "NAME_BASE_URL"
    }

    @Provides
    @Singleton
    fun provideErrorHandler(context: Context): ErrorHandler = ErrorHandlerImpl(context)

    @Provides
    @Singleton
    fun provideLocalStorage(context: Context): LocalStorage = LocalStorageImpl(context)

    @Provides
    @Named(NAME_BASE_URL)
    fun provideBaseUrl() = "https://drop-api-qa.azurewebsites.net/"

    @Provides
    @Singleton
    fun provideAuthApiService(@Named(NAME_BASE_URL) baseUrl: String): AuthApiService = Retrofit
            .Builder()
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .addConverterFactory(GsonConverterFactory.create())
            .baseUrl(baseUrl)
            .build()
            .create(AuthApiService::class.java)

    @Provides
    @Singleton
    fun provideAuthService(localStorage: LocalStorage): AuthService = AuthServiceImpl(localStorage)

    @Provides
    @Singleton
    fun provideApiService(@Named(NAME_BASE_URL) baseUrl: String, authService: AuthService)
            : ApiService {

        val client = OkHttpClient.Builder()
                .addInterceptor { chain ->
                    val request = chain.request()
                            .newBuilder()
                            .addHeader("Authorization", "Bearer ${authService.token}")
                            .build()
                    chain.proceed(request)
                }
                .build()

        return Retrofit.Builder()
                .client(client)
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .baseUrl(baseUrl)
                .build()
                .create(ApiService::class.java)
    }
}
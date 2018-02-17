package com.ladevelopers.wayv.drivers.qa.infrastructure

import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.ladevelopers.wayv.drivers.qa.contracts.ApiService
import com.ladevelopers.wayv.drivers.qa.contracts.AuthService
import com.ladevelopers.wayv.drivers.qa.contracts.ErrorHandler
import com.ladevelopers.wayv.drivers.qa.contracts.isSignedIn
import com.ladevelopers.wayv.drivers.qa.features.login.LoginActivity
import com.ladevelopers.wayv.drivers.qa.features.main.MainActivity
import io.reactivex.schedulers.Schedulers
import kotlinx.coroutines.experimental.android.UI
import kotlinx.coroutines.experimental.launch
import kotlinx.coroutines.experimental.rx2.awaitSingle
import javax.inject.Inject

class SplashActivity : AppCompatActivity() {

    @Inject
    lateinit var authService: AuthService
    @Inject
    lateinit var apiService: ApiService
    @Inject
    lateinit var errorHandler: ErrorHandler

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        (application as App).component.inject(this)
        initAuth()
    }

    private fun initAuth() = launch(UI) {
        if (!authService.isSignedIn) {
            startLogin()
            return@launch
        }
        try {
            val dto = apiService.renewToken()
                    .subscribeOn(Schedulers.io())
                    .awaitSingle()
            authService.signin(dto)
            startMain()
        } catch (ex: Exception) {
            errorHandler.handle(ex)
            startLogin()
        }
    }

    private fun startLogin() {
        val intent = Intent(this, LoginActivity::class.java)
        startActivity(intent)
    }

    private fun startMain() {
        val intent = Intent(this, MainActivity::class.java)
        startActivity(intent)
    }
}

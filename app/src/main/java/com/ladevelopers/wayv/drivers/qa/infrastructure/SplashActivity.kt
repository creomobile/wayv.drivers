package com.ladevelopers.wayv.drivers.qa.infrastructure

import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.ladevelopers.wayv.drivers.qa.features.login.LoginActivity

class SplashActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val intent = Intent(this, LoginActivity::class.java)
        startActivity(intent)
        finish()
    }
}

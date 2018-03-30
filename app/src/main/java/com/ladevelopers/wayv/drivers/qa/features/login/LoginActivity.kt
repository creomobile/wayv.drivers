package com.ladevelopers.wayv.drivers.qa.features.login

import android.arch.lifecycle.ViewModelProviders
import android.content.Context
import android.content.Intent
import android.databinding.DataBindingUtil
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.View
import android.view.animation.Animation
import android.view.inputmethod.InputMethodManager
import android.widget.EditText
import com.ladevelopers.wayv.drivers.qa.R
import com.ladevelopers.wayv.drivers.qa.contracts.AuthService
import com.ladevelopers.wayv.drivers.qa.databinding.ActivityLoginBinding
import com.ladevelopers.wayv.drivers.qa.features.main.MainActivity
import com.ladevelopers.wayv.drivers.qa.helpers.moveCursorToEndAfterTextChanged
import com.ladevelopers.wayv.drivers.qa.helpers.setFadeAnimation
import com.ladevelopers.wayv.drivers.qa.infrastructure.App
import com.ladevelopers.wayv.drivers.qa.infrastructure.ViewModelFactory
import kotlinx.android.synthetic.main.activity_login.*
import kotlinx.coroutines.experimental.android.UI
import kotlinx.coroutines.experimental.launch
import javax.inject.Inject

class LoginActivity : AppCompatActivity() {

    @Inject
    lateinit var viewModelFactory: ViewModelFactory
    @Inject
    lateinit var authService: AuthService

    private lateinit var viewModel: LoginViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        (application as App).component.inject(this)

        val binding = DataBindingUtil.setContentView<ActivityLoginBinding>(
                this, R.layout.activity_login)
        viewModel = ViewModelProviders
                .of(this, viewModelFactory)
                .get(LoginViewModel::class.java)
        binding.vm = viewModel
        binding.phoneText.moveCursorToEndAfterTextChanged()

        viewSwitcher.apply {
            setFadeAnimation()
            inAnimation.setAnimationListener(object : Animation.AnimationListener {
                override fun onAnimationRepeat(p0: Animation?) {
                }

                override fun onAnimationEnd(p0: Animation?) {
                    onViewSwitched()
                }

                override fun onAnimationStart(p0: Animation?) {
                }
            })
        }

        val listener = View.OnClickListener { setFocus(binding.codeText) }
        arrayOf(codePanel1, codePanel2, codePanel3, codePanel4).forEach {
            it.setOnClickListener(listener)
        }

        setFocus(phoneText)

        initAuth()
    }

    private fun initAuth() = launch(UI) {
        val dto = viewModel.result.await()
        authService.signin(dto)
        val intent = Intent(this@LoginActivity, MainActivity::class.java)
        startActivity(intent)
    }

    private fun setFocus(editText: EditText) {
        if (editText.requestFocus()) {
            val imm = getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
            imm.showSoftInput(editText, InputMethodManager.SHOW_IMPLICIT)
        }
    }

    private fun onViewSwitched() =
            setFocus(if (viewSwitcher.displayedChild == 0) phoneText else codeText)

    override fun onBackPressed() {
        if (viewModel.showCodeEntering.get())
            viewModel.back()
        else
            super.onBackPressed()
    }
}

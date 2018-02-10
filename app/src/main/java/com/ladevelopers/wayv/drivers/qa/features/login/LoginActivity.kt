package com.ladevelopers.wayv.drivers.qa.features.login

import android.arch.lifecycle.ViewModelProvider
import android.arch.lifecycle.ViewModelProviders
import android.content.Context
import android.databinding.DataBindingUtil
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.View
import android.view.animation.Animation
import android.view.inputmethod.InputMethodManager
import android.widget.EditText
import com.ladevelopers.wayv.drivers.qa.R
import com.ladevelopers.wayv.drivers.qa.databinding.ActivityLoginBinding
import com.ladevelopers.wayv.drivers.qa.helpers.moveCursorToEndAfterTextChanged
import com.ladevelopers.wayv.drivers.qa.helpers.setFadeAnimation
import kotlinx.android.synthetic.main.activity_login.*
import javax.inject.Inject


class LoginActivity : AppCompatActivity() {

    private lateinit var binding: ActivityLoginBinding
    private lateinit var viewModel: LoginViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_login)
        viewModel = ViewModelProviders.of(this).get(LoginViewModel::class.java)
        binding.vm = viewModel
        binding.phoneText.moveCursorToEndAfterTextChanged()
        binding.viewSwitcher.setFadeAnimation()

        binding.viewSwitcher.inAnimation.setAnimationListener(object : Animation.AnimationListener {
            override fun onAnimationRepeat(p0: Animation?) {
            }

            override fun onAnimationEnd(p0: Animation?) {
                onViewSwitched()
            }

            override fun onAnimationStart(p0: Animation?) {
            }
        })

        val listener = View.OnClickListener { setFocus(binding.codeText) }
        arrayOf(codePanel1, codePanel2, codePanel3, codePanel4).forEach {
            it.setOnClickListener(listener)
        }

        setFocus(binding.phoneText)
    }

    private fun setFocus(editText: EditText) {
        if (editText.requestFocus()) {
            val imm = getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
            imm.showSoftInput(editText, InputMethodManager.SHOW_IMPLICIT)
        }
    }

    private fun onViewSwitched() =
            setFocus(if (binding.viewSwitcher.displayedChild == 0) binding.phoneText else binding.codeText)

    override fun onBackPressed() {
        if (viewModel.showCodeEntering.get())
            viewModel.back()
        else
            super.onBackPressed()
    }
}

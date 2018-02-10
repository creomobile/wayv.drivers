package com.ladevelopers.wayv.drivers.qa.infrastructure.modules

import android.arch.lifecycle.*
import com.ladevelopers.wayv.drivers.qa.features.login.LoginViewModel
import com.ladevelopers.wayv.drivers.qa.infrastructure.*
import dagger.*
import dagger.multibindings.IntoMap

@Module
abstract class ViewModelModule {

    @Binds
    @IntoMap
    @ViewModelKey(LoginViewModel::class)
    abstract fun bindLoginViewModel(loginViewModel: LoginViewModel): ViewModel

    @Binds
    abstract fun bindViewModelFactory( factory: ViewModelFactory):
            ViewModelProvider.Factory
}
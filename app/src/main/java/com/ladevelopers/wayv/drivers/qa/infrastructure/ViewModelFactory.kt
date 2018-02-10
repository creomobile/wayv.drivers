package com.ladevelopers.wayv.drivers.qa.infrastructure

import android.arch.lifecycle.ViewModel
import android.arch.lifecycle.ViewModelProvider
import javax.inject.*

@Singleton
class ViewModelFactory @Inject constructor(
        private val creators: Map<Class<out ViewModel>,
                @JvmSuppressWildcards Provider<ViewModel>>)
    : ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        val creator: Provider<out ViewModel>? = creators[modelClass]
                ?: throw IllegalArgumentException("unknown model class " + modelClass)

        try {
            @Suppress("UNCHECKED_CAST")
            return creator!!.get() as T
        } catch (e: Exception) {
            throw RuntimeException(e)
        }
    }
}
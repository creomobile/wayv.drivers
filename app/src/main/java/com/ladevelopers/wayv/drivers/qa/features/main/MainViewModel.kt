package com.ladevelopers.wayv.drivers.qa.features.main

import android.annotation.SuppressLint
import android.arch.lifecycle.ViewModel
import android.databinding.ObservableField
import com.ladevelopers.wayv.drivers.qa.R
import com.ladevelopers.wayv.drivers.qa.features.Feature
import javax.inject.Inject

@SuppressLint("StaticFieldLeak")
class MainViewModel
@Inject constructor(private val featuresMap: Map<Int, @JvmSuppressWildcards Feature>)
    : ViewModel() {

    val menuItems = ObservableField<List<Feature>>()
    val menuTitle = ObservableField<String>("Wave")

    init {
        menuItems.set(listOf(
                featuresMap.getValue(R.string.dashboard),
                featuresMap.getValue(R.string.account)
        ))
    }
}
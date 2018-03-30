package com.ladevelopers.wayv.drivers.qa.features.dashboard

import android.support.v4.app.FragmentManager
import com.ladevelopers.wayv.drivers.qa.R
import com.ladevelopers.wayv.drivers.qa.features.Feature

class DashboardFeature : Feature(R.string.dashboard, R.drawable.dashboard) {
    override fun start() {
        activity.supportFragmentManager.popBackStack(
                null, FragmentManager.POP_BACK_STACK_INCLUSIVE)
        super.start()
    }
}
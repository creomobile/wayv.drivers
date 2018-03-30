package com.ladevelopers.wayv.drivers.qa.features.account

import com.ladevelopers.wayv.drivers.qa.R
import com.ladevelopers.wayv.drivers.qa.features.FragmentFeature
import com.ladevelopers.wayv.drivers.qa.features.account.AccountFragment.Companion.newInstance

class AccountFeature : FragmentFeature(R.string.account, R.drawable.account,
        AccountFragment::class.java, ::newInstance)
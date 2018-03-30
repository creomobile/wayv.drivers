package com.ladevelopers.wayv.drivers.qa.infrastructure.modules

import com.ladevelopers.wayv.drivers.qa.R
import com.ladevelopers.wayv.drivers.qa.features.Feature
import com.ladevelopers.wayv.drivers.qa.features.account.AccountFeature
import com.ladevelopers.wayv.drivers.qa.features.dashboard.DashboardFeature
import dagger.Module
import dagger.Provides
import dagger.multibindings.IntKey
import dagger.multibindings.IntoMap
import dagger.multibindings.IntoSet
import javax.inject.Singleton

@Module
class FeaturesModule {

    // ---------- Dashboard
    @Provides
    @Singleton
    fun provideDashboard() = DashboardFeature()

    @Provides
    @IntoSet
    fun provideDashboardSet(feature: DashboardFeature): Feature = feature

    @Provides
    @IntoMap
    @IntKey(R.string.dashboard)
    fun provideDashboardMap(feature: DashboardFeature): Feature = feature

    // ---------- Account
    @Provides
    @Singleton
    fun provideAccount() = AccountFeature()

    @Provides
    @IntoSet
    fun provideAccountFeature(feature: AccountFeature): Feature = feature

    @Provides
    @IntoMap
    @IntKey(R.string.account)
    fun provideAccountMap(feature: AccountFeature): Feature = feature

}
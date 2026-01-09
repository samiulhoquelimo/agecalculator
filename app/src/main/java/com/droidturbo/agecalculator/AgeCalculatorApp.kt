package com.droidturbo.agecalculator

import android.app.Application
import android.content.Context
import android.content.res.Configuration
import com.droidturbo.agecalculator.utils.LocaleManager
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class AgeCalculatorApp : Application() {

    companion object {
        var localeManager: LocaleManager? = null
    }

    override fun attachBaseContext(base: Context) {
        localeManager = LocaleManager(base)
        super.attachBaseContext(localeManager?.setLocale(base))
    }

    override fun onConfigurationChanged(newConfig: Configuration) {
        super.onConfigurationChanged(newConfig)
        localeManager?.setLocale(this)
    }
}
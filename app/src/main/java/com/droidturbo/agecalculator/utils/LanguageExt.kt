package com.droidturbo.agecalculator.utils

import android.app.Activity
import android.content.Context
import com.droidturbo.agecalculator.AgeCalculatorApp

fun language(): String {
    return AgeCalculatorApp.localeManager?.language ?: LocaleManager.LANGUAGE_EN
}

fun Context.switchLanguage(language: String) {
    AgeCalculatorApp.localeManager?.setNewLocale(this, language)
    (this as? Activity)?.recreate()
}
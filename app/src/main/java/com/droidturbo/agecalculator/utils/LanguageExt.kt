package com.droidturbo.agecalculator.utils

import android.app.Activity
import android.content.Context
import com.droidturbo.agecalculator.AgeCalculatorApp

fun isEnglish(): Boolean = language() == LocaleManager.LANGUAGE_EN

fun language(): String {
    return AgeCalculatorApp.localeManager?.language ?: LocaleManager.LANGUAGE_EN
}

fun Context.switchLanguage(language: String) {
    AgeCalculatorApp.localeManager?.setNewLocale(this, language)
    (this as? Activity)?.recreate()
}

fun language(numberStr: String?): String {
    if (numberStr.isNullOrEmpty()) return ""

    return if ( language() == LocaleManager.LANGUAGE_EN) {
        numberStr
    } else {
        val bnDigits = mapOf(
            '0' to '০', '1' to '১', '2' to '২', '3' to '৩', '4' to '৪',
            '5' to '৫', '6' to '৬', '7' to '৭', '8' to '৮', '9' to '৯'
        )
        numberStr.map { bnDigits[it] ?: it }.joinToString("")
    }
}
package com.droidturbo.agecalculator.utils

import android.content.Context
import android.content.SharedPreferences
import android.content.res.Configuration
import android.os.LocaleList
import androidx.core.content.edit
import java.util.Locale

class LocaleManager(context: Context) {

    private val prefs: SharedPreferences by lazy {
        context.getSharedPreferences(
            "com.droidturbo.agecalculator.preferences",
            Context.MODE_PRIVATE
        )
    }

    fun setLocale(context: Context): Context {
        return context.language(language)
    }

    fun setNewLocale(context: Context, language: String): Context {
        prefs.edit { putString(KEY, language) }
        return context.language(language)
    }

    fun Context.language(language: String): Context {
        val locale = Locale.forLanguageTag(language)
        Locale.setDefault(locale)

        val config = Configuration(resources.configuration).apply {
            setLocales(LocaleList(locale))
        }

        return createConfigurationContext(config)
    }

    val language: String
        get() = prefs.getString(KEY, DEFAULT) ?: DEFAULT

    companion object {
        const val LANGUAGE_EN = "en"
        const val LANGUAGE_BN = "bn"
        private const val KEY = "language_key"
        private const val DEFAULT = LANGUAGE_EN
    }
}
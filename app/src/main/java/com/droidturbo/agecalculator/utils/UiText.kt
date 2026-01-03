package com.droidturbo.agecalculator.utils

import android.content.Context
import androidx.annotation.StringRes
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import com.droidturbo.agecalculator.R

sealed interface UiText {
    data class DynamicString(val value: String?) : UiText

    class StringResource(
        @param:StringRes val resId: Int,
        vararg val args: Any
    ) : UiText

    @Composable
    fun asString(): String {
        return when (this) {
            is DynamicString -> value ?: stringResource(R.string.general_error)
            is StringResource -> stringResource(resId, *args)
        }
    }

    fun asString(context: Context): String {
        return when (this) {
            is DynamicString -> value ?: context.getString(R.string.general_error)
            is StringResource -> context.getString(resId, *args)
        }
    }
}
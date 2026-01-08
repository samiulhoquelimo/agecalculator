package com.droidturbo.agecalculator.utils

import android.content.Context
import java.time.LocalDate
import java.util.Locale

fun showDatePicker(
    context: Context,
    initialDate: LocalDate?,
    onDateSelected: (String) -> Unit
) {
    val date = initialDate ?: LocalDate.now()

    android.app.DatePickerDialog(
        context,
        { _, year, month, dayOfMonth ->
            val formattedDate = String.format(
                Locale.ENGLISH,
                "%04d-%02d-%02d",
                year,
                month + 1,
                dayOfMonth
            )
            onDateSelected(formattedDate)
        },
        date.year,
        date.monthValue - 1,
        date.dayOfMonth
    ).show()
}
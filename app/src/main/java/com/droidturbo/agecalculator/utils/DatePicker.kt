package com.droidturbo.agecalculator.utils

import android.content.Context
import java.util.Calendar
import java.util.Locale

fun showDatePicker(
    context: Context,
    onDateSelected: (String) -> Unit
) {
    val calendar = Calendar.getInstance()

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
        calendar.get(Calendar.YEAR),
        calendar.get(Calendar.MONTH),
        calendar.get(Calendar.DAY_OF_MONTH)
    ).show()
}
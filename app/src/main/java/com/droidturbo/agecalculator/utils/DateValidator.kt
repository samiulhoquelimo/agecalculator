package com.droidturbo.agecalculator.utils

import java.time.LocalDate

fun isValidDob(day: String, month: String, year: String): Boolean {
    return try {
        val dob = LocalDate.of(year.toInt(), month.toInt(), day.toInt())
        val today = LocalDate.now()
        dob.isBefore(today) || dob.isEqual(today)
    } catch (e: Exception) {
        e.printStackTrace()
        false
    }
}

fun formatDob(digits: String): String {
    val clean = digits.filter { it.isDigit() }.take(8)

    val day = clean.take(2)
    val month = clean.drop(2).take(2)
    val year = clean.drop(4).take(4)

    return buildString {
        if (day.isNotEmpty()) append(day)
        if (month.isNotEmpty()) append(" / ").append(month)
        if (year.isNotEmpty()) append(" / ").append(year)
    }
}

fun validDateFormat(year: String, month: String, dayOfMonth: String): LocalDate? = try {
    val dayOfMonth = dayOfMonth.toInt()
    val month = month.toInt()
    val year = year.toInt()
    LocalDate.of(year, month, dayOfMonth)
} catch (e: Exception) {
    e.printStackTrace()
    null
}
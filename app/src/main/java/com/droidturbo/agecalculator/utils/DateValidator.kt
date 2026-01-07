package com.droidturbo.agecalculator.utils

import java.time.LocalDate

fun isDayOfMonthValid(dayOfMonth: String): Boolean {
    val dayOfMonth = dayOfMonth.toIntOrNull() ?: 0
    return dayOfMonth in 1..31
}

fun isMonthValid(month: String): Boolean {
    val month = month.toIntOrNull() ?: 0
    return month in 1..12
}

fun isYearValid(year: String): Boolean {
    val year = year.toIntOrNull() ?: 0
    return year in 1900..LocalDate.now().year
}

fun validator(year: String, month: String, dayOfMonth: String): Boolean {
    return when {
        !isDayOfMonthValid(dayOfMonth = dayOfMonth) -> false
        !isMonthValid(month = month) -> false
        !isYearValid(year = year) -> false
        validDateFormat(year = year, month = month, dayOfMonth = dayOfMonth) == null -> false
        else -> true
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
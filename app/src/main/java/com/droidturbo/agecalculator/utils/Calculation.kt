package com.droidturbo.agecalculator.utils

import com.droidturbo.agecalculator.ui.home.HomeState
import java.time.LocalDate
import java.time.Period
import java.time.temporal.ChronoUnit

/**
If the unit is a ChronoUnit then the query is implemented here.
The supported units are: DAYS, WEEKS, MONTHS, YEARS, DECADES, CENTURIES, MILLENNIA, ERAS
Ref: https://docs.oracle.com/javase/10/docs/api/java/time/LocalDate.html#isSupported(java.time.temporal.TemporalUnit)

No Support: HOURS, MINUTES, SECONDS
 */

private val today: LocalDate = LocalDate.now()

private fun totalWeek(birthday: LocalDate): Int = try {
    ChronoUnit.WEEKS.between(birthday, today).toInt()
} catch (e: Exception) {
    e.printStackTrace()
    0
}

private fun totalDay(birthday: LocalDate): Int = try {
    ChronoUnit.DAYS.between(birthday, today).toInt()
} catch (e: Exception) {
    e.printStackTrace()
    0
}

private fun totalHours(birthday: LocalDate): Int = try {
    totalDay(birthday).times(24)
} catch (e: Exception) {
    e.printStackTrace()
    0
}

private fun totalMin(birthday: LocalDate): Int = try {
    totalHours(birthday).times(60)
} catch (e: Exception) {
    e.printStackTrace()
    0
}

private fun totalSec(birthday: LocalDate): Int = try {
    totalMin(birthday).times(60)
} catch (e: Exception) {
    e.printStackTrace()
    0
}

private fun nextBirthdayPeriod(dayOfMonth: Int, month: Int): Period {
    return Period.between(today, LocalDate.of(today.year + 1, month, dayOfMonth))
}

private fun age(birthday: LocalDate): Period {
    return Period.between(birthday, today)
}

fun HomeState.resetState(): HomeState {
    return copy(
        ageDay = 0,
        ageMonth = 0,
        ageYear = 0,
        bdDay = 0,
        bdMonth = 0,
        tYear = 0,
        tMonth = 0,
        tWeek = 0,
        tDay = 0,
        tHour = 0,
        tMin = 0,
        tSec = 0,
    )
}

fun isValidDate(dayOfMonth: Int, month: Int, year: Int): String? = try {
    LocalDate.of(year, month, dayOfMonth)
    null
} catch (e: Exception) {
    e.printStackTrace()
    "Invalid date"
}

fun HomeState.calculation(
    dayOfMonth: Int,
    month: Int,
    year: Int
): HomeState {
    val birthday = LocalDate.of(year, month, dayOfMonth)
    val age = age(birthday)
    val nextBirthday = nextBirthdayPeriod(dayOfMonth, month)

    return copy(
        ageDay = age.days,
        ageMonth = age.months,
        ageYear = age.years,
        bdDay = nextBirthday.days,
        bdMonth = nextBirthday.months,
        tYear = age.years,
        tMonth = age.toTotalMonths().toInt(),
        tDay = totalDay(birthday),
        tWeek = totalWeek(birthday),
        tHour = totalHours(birthday),
        tMin = totalMin(birthday),
        tSec = totalSec(birthday)
    )
}
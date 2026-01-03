package com.droidturbo.agecalculator.utils

import com.droidturbo.agecalculator.main.HomeAgeModel
import com.droidturbo.agecalculator.main.HomeNextBirthdayModel
import com.droidturbo.agecalculator.main.HomeState
import com.droidturbo.agecalculator.main.HomeTotalModel
import java.time.LocalDate
import java.time.Period
import java.time.temporal.ChronoUnit

/**
If the unit is a ChronoUnit then the query is implemented here.
The supported units are: DAYS, WEEKS, MONTHS, YEARS, DECADES, CENTURIES, MILLENNIA, ERAS
Ref: https://docs.oracle.com/javase/10/docs/api/java/time/LocalDate.html#isSupported(java.time.temporal.TemporalUnit)

No Support: HOURS, MINUTES, SECONDS
 */

private fun today(): LocalDate = LocalDate.now()

private fun totalWeek(birthday: LocalDate): Int = try {
    ChronoUnit.WEEKS.between(birthday, today()).toInt()
} catch (e: Exception) {
    e.printStackTrace()
    0
}

private fun totalDay(birthday: LocalDate): Int = try {
    ChronoUnit.DAYS.between(birthday, today()).toInt()
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

private fun nextBirthdayPeriod(birthday: LocalDate): Period {
    val today = today()
    val nextBirthday = birthday.withYear(today.year)
        .let { if (!it.isAfter(today)) it.plusYears(1) else it }

    return Period.between(today, nextBirthday)
}

private fun age(birthday: LocalDate): Period {
    return Period.between(birthday, today())
}

fun isValidDate(dayOfMonth: Int, month: Int, year: Int): String? = try {
    LocalDate.of(year, month, dayOfMonth)
    null
} catch (e: Exception) {
    e.printStackTrace()
    "Invalid date"
}

fun HomeState.calculateAge(birthday: LocalDate): HomeState {
    val agePeriod = Period.between(birthday, today())
    val nextBirthdayPeriod = nextBirthdayPeriod(birthday)

    val age = HomeAgeModel(
        ageYear = agePeriod.years,
        ageMonth = agePeriod.months,
        ageDay = agePeriod.days,
    )

    val nextBirthday = HomeNextBirthdayModel(
        bdMonth = nextBirthdayPeriod.months,
        bdDay = nextBirthdayPeriod.days,
    )

    val totalInfo = HomeTotalModel(
        tYear = agePeriod.years,
        tMonth = agePeriod.toTotalMonths().toInt(),
        tWeek = totalWeek(birthday),
        tDay = totalDay(birthday),
        tHour = totalHours(birthday),
        tMin = totalMin(birthday),
        tSec = totalSec(birthday),
    )

    return HomeState(
        age = age,
        nextBirthday = nextBirthday,
        totalInfo = totalInfo,
    )
}
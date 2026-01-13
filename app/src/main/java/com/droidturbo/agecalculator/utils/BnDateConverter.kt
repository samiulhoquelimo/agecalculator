package com.droidturbo.agecalculator.utils

import android.content.Context
import com.droidturbo.agecalculator.R
import java.time.LocalDate
import java.time.temporal.ChronoUnit

val banglaMonths = listOf(
    UiText.StringResource(R.string.boishakh),
    UiText.StringResource(R.string.joishtho),
    UiText.StringResource(R.string.ashar),
    UiText.StringResource(R.string.srabon),
    UiText.StringResource(R.string.bhadro),
    UiText.StringResource(R.string.ashwin),
    UiText.StringResource(R.string.kartik),
    UiText.StringResource(R.string.agrahayan),
    UiText.StringResource(R.string.poush),
    UiText.StringResource(R.string.magh),
    UiText.StringResource(R.string.falgun),
    UiText.StringResource(R.string.chaitra)
)

fun Int.toBnDigits(): String {
    val map = mapOf(
        '0' to '০', '1' to '১', '2' to '২',
        '3' to '৩', '4' to '৪', '5' to '৫',
        '6' to '৬', '7' to '৭', '8' to '৮', '9' to '৯'
    )
    return this.toString().map { map[it] ?: it }.joinToString("")
}

fun Triple<Int, UiText, Int>.toBnDate(context: Context): String {
    val (year, month, day) = this
    return "${day.toBnDigits()} ${month.asString(context)} ${year.toBnDigits()}"
}

fun getBanglaMonthDays(falgunLeapYear: Int): List<Int> =
    listOf(
        31, 31, 31, 31, 31, 31,
        30, 30, 30, 30,
        if (isLeapYear(falgunLeapYear)) 30 else 29,
        30
    )

fun isLeapYear(year: Int): Boolean =
    (year % 4 == 0 && year % 100 != 0) || (year % 400 == 0)

fun engToBnDate(date: LocalDate): Triple<Int, UiText, Int> {
    val banglaYearStartEnglishYear =
        if (date >= LocalDate.of(date.year, 4, 14))
            date.year
        else
            date.year - 1

    val banglaYear = banglaYearStartEnglishYear - 593
    val startDate = LocalDate.of(banglaYearStartEnglishYear, 4, 14)

    val falgunLeapYear = banglaYearStartEnglishYear + 1

    var daysPassed = ChronoUnit.DAYS.between(startDate, date).toInt()

    val monthDays = getBanglaMonthDays(falgunLeapYear)

    var monthIndex = 0
    while (monthIndex < monthDays.size - 1 && daysPassed >= monthDays[monthIndex]) {
        daysPassed -= monthDays[monthIndex]
        monthIndex++
    }

    val banglaMonth = banglaMonths[monthIndex]
    val banglaDay = daysPassed + 1

    return Triple(banglaYear, banglaMonth, banglaDay)
}
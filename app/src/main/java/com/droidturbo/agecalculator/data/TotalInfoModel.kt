package com.droidturbo.agecalculator.data

import com.droidturbo.agecalculator.R
import com.droidturbo.agecalculator.utils.UiText

data class TotalInfoModel(
    val tYear: Int = 0,
    val tMonth: Int = 0,
    val tWeek: Int = 0,
    val tDay: Int = 0,
    val tHour: Int = 0,
    val tMin: Int = 0,
    val tSec: Int = 0
)

fun TotalInfoModel.toData(): List<Pair<UiText, String>> {
    return arrayListOf(
        UiText.StringResource(R.string.total_year) to tYear.toString(),
        UiText.StringResource(R.string.total_month) to tMonth.toString(),
        UiText.StringResource(R.string.total_week) to tWeek.toString(),
        UiText.StringResource(R.string.total_day) to tDay.toString(),
        UiText.StringResource(R.string.total_hour) to tHour.toString(),
        UiText.StringResource(R.string.total_minute) to tMin.toString(),
        UiText.StringResource(R.string.total_second) to tSec.toString()
    )
}

package com.droidturbo.agecalculator.data

data class HomeTotalModel(
    val tYear: Int = 0,
    val tMonth: Int = 0,
    val tWeek: Int = 0,
    val tDay: Int = 0,
    val tHour: Int = 0,
    val tMin: Int = 0,
    val tSec: Int = 0
)

fun HomeTotalModel.toData(): List<Pair<String, String>> {
    return arrayListOf(
        "Total Year" to "$tYear",
        "Total Month" to "$tMonth",
        "Total Weeks" to "$tWeek",
        "Total Days" to "$tDay",
        "Total Hours" to "$tHour",
        "Total Minutes" to "$tMin",
        "Total Seconds" to "$tSec"
    )
}

package com.droidturbo.agecalculator.ui.home

data class HomeState(
    val input: HomeInput = HomeInput(),
    val ageYear: Int = 0,
    val ageMonth: Int = 0,
    val ageDay: Int = 0,
    val bdMonth: Int = 0,
    val bdDay: Int = 0,
    val tYear: Int = 0,
    val tMonth: Int = 0,
    val tWeek: Int = 0,
    val tDay: Int = 0,
    val tHour: Int = 0,
    val tMin: Int = 0,
    val tSec: Int = 0,
)
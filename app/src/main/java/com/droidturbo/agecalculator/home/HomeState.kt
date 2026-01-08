package com.droidturbo.agecalculator.home

import com.droidturbo.agecalculator.data.HomeDataModel

data class HomeState(
    val dayOfMonth: String = "",
    val month: String = "",
    val year: String = "",
    val result: HomeDataModel? = null,
)
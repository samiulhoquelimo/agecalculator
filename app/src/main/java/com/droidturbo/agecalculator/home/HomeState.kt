package com.droidturbo.agecalculator.home

import com.droidturbo.agecalculator.data.HomeDataModel
import com.droidturbo.agecalculator.utils.UiText

data class HomeState(
    val dayOfMonth: String = "",
    val month: String = "",
    val year: String = "",
    val result: HomeDataModel? = null,
    val error: UiText? = null
)
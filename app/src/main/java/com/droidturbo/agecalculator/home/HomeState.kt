package com.droidturbo.agecalculator.home

import com.droidturbo.agecalculator.data.HomeAgeModel
import com.droidturbo.agecalculator.data.HomeNextBirthdayModel
import com.droidturbo.agecalculator.data.HomeTotalModel

data class HomeState(
    val dayOfMonth: String = "",
    val month: String = "",
    val year: String = "",
    val age: HomeAgeModel = HomeAgeModel(),
    val nextBirthday: HomeNextBirthdayModel = HomeNextBirthdayModel(),
    val totalInfo: HomeTotalModel = HomeTotalModel(),
    val error: String? = null
)
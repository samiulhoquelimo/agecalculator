package com.droidturbo.agecalculator.main

data class HomeState(
    val dayOfMonth: String = "",
    val month: String = "",
    val year: String = "",
    val age: HomeAgeModel = HomeAgeModel(),
    val nextBirthday: HomeNextBirthdayModel = HomeNextBirthdayModel(),
    val totalInfo: HomeTotalModel = HomeTotalModel(),
    val error: String? = null
)
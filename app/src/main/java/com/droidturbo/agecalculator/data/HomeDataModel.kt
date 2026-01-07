package com.droidturbo.agecalculator.data

data class HomeDataModel(
    val age: HomeAgeModel = HomeAgeModel(),
    val nextBirthday: HomeNextBirthdayModel = HomeNextBirthdayModel(),
    val totalInfo: HomeTotalModel = HomeTotalModel(),
)
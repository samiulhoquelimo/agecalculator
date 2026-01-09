package com.droidturbo.agecalculator.data

data class HomeDataModel(
    val age: AgeModel = AgeModel(),
    val nextBirthday: BirthdayModel = BirthdayModel(),
    val totalInfo: TotalInfoModel = TotalInfoModel(),
)
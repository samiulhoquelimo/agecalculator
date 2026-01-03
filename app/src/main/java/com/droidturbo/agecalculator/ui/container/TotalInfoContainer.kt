package com.droidturbo.agecalculator.ui.container

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.droidturbo.agecalculator.main.HomeState
import com.droidturbo.agecalculator.ui.content.CardBlock
import com.droidturbo.agecalculator.ui.content.ExtraInfoField
import com.droidturbo.agecalculator.ui.content.TitleBlock

@Composable
fun TotalInfoContainer(
    state: HomeState = HomeState()
) {
    CardBlock {
        Column(
            modifier = Modifier.padding(start = 8.dp, end = 8.dp),
            verticalArrangement = Arrangement.SpaceEvenly
        ) {
            TitleBlock(text = "Some extra information")
            ExtraInfoField("Total Year: ", state.totalInfo.tYear)
            ExtraInfoField("Total Month: ", state.totalInfo.tMonth)
            ExtraInfoField("Total Weeks: ", state.totalInfo.tWeek)
            ExtraInfoField("Total Days: ", state.totalInfo.tDay)
            ExtraInfoField("Total Hours: ", state.totalInfo.tHour)
            ExtraInfoField("Total Minutes: ", state.totalInfo.tMin)
            ExtraInfoField("Total Seconds: ", state.totalInfo.tSec)
            Spacer(modifier = Modifier.height(16.dp))
        }
    }
}

@Composable
fun TotalInfoContainerPreview() {
    TotalInfoContainer()
}
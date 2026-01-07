package com.droidturbo.agecalculator.ui.container

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.droidturbo.agecalculator.data.HomeTotalModel
import com.droidturbo.agecalculator.ui.content.CardBlock
import com.droidturbo.agecalculator.ui.content.ExtraInfoField
import com.droidturbo.agecalculator.ui.content.TitleBlock

@Composable
fun TotalInfoContainer(totalInfo: HomeTotalModel = HomeTotalModel()) {
    CardBlock {
        Column(
            modifier = Modifier.padding(start = 8.dp, end = 8.dp),
            verticalArrangement = Arrangement.SpaceEvenly
        ) {
            TitleBlock(text = "Some extra information")
            ExtraInfoField("Total Year: ", totalInfo.tYear)
            ExtraInfoField("Total Month: ", totalInfo.tMonth)
            ExtraInfoField("Total Weeks: ", totalInfo.tWeek)
            ExtraInfoField("Total Days: ", totalInfo.tDay)
            ExtraInfoField("Total Hours: ", totalInfo.tHour)
            ExtraInfoField("Total Minutes: ", totalInfo.tMin)
            ExtraInfoField("Total Seconds: ", totalInfo.tSec)
            Spacer(modifier = Modifier.height(16.dp))
        }
    }
}


@Preview(showBackground = true)
@Composable
fun TotalInfoContainerPreview() {
    TotalInfoContainer()
}
package com.droidturbo.agecalculator.ui.container

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.droidturbo.agecalculator.data.HomeAgeModel
import com.droidturbo.agecalculator.ui.content.CardBlock
import com.droidturbo.agecalculator.ui.content.ThreeColumnField
import com.droidturbo.agecalculator.ui.content.ThreeColumnTitle
import com.droidturbo.agecalculator.ui.content.TitleBlock

@Composable
fun AgeContainer(
    ageModel: HomeAgeModel = HomeAgeModel()
) {
    CardBlock {
        Column(
            modifier = Modifier.padding(start = 8.dp, end = 8.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.SpaceEvenly
        ) {
            TitleBlock(text = "Your age is")
            ThreeColumnTitle("Year", "Month", "Days")
            ThreeColumnField(ageModel.ageYear, ageModel.ageMonth, ageModel.ageDay)
            Spacer(modifier = Modifier.height(16.dp))
        }
    }
}


@Preview(showBackground = true)
@Composable
fun AgeContainerPreview() {
    AgeContainer()
}
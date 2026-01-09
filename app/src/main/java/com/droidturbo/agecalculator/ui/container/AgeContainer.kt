package com.droidturbo.agecalculator.ui.container

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import com.droidturbo.agecalculator.R
import com.droidturbo.agecalculator.data.HomeAgeModel
import com.droidturbo.agecalculator.ui.content.AgeItem
import com.droidturbo.agecalculator.ui.content.AppCard

@Composable
fun AgeContainer(
    ageModel: HomeAgeModel = HomeAgeModel()
) {
    AppCard(
        title = stringResource(R.string.your_age_is)
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            AgeItem(label = stringResource(id = R.string.years), value = ageModel.ageYear)
            AgeItem(label = stringResource(id = R.string.months), value = ageModel.ageMonth)
            AgeItem(label = stringResource(id = R.string.days), value = ageModel.ageDay)
        }
    }
}


@Preview(showBackground = true)
@Composable
fun AgeContainerPreview() {
    AgeContainer()
}
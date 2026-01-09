package com.droidturbo.agecalculator.ui.container

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
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
            modifier = Modifier
                .fillMaxWidth()
                .clip(RoundedCornerShape(12.dp))
                .background(MaterialTheme.colorScheme.surface),
            horizontalArrangement = Arrangement.SpaceAround
        ) {
            AgeItem(
                label = stringResource(id = R.string.years),
                value = ageModel.ageYear
            )
            AgeItem(
                label = stringResource(id = R.string.months),
                value = ageModel.ageMonth
            )
            AgeItem(
                label = stringResource(id = R.string.days),
                value = ageModel.ageDay
            )
        }
    }
}


@Preview(showBackground = true)
@Composable
fun AgeContainerPreview() {
    AgeContainer()
}
package com.droidturbo.agecalculator.ui.container

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import com.droidturbo.agecalculator.R
import com.droidturbo.agecalculator.data.HomeNextBirthdayModel
import com.droidturbo.agecalculator.ui.content.AgeItem
import com.droidturbo.agecalculator.ui.content.AppCard

@Composable
fun BirthdayContainer(
    nextBirthday: HomeNextBirthdayModel = HomeNextBirthdayModel()
) {
    AppCard(title = stringResource(R.string.next_birthday)) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceEvenly
        ) {
            AgeItem(label = stringResource(id = R.string.months), value = nextBirthday.bdMonth)
            AgeItem(label = stringResource(id = R.string.days), value = nextBirthday.bdDay)
        }
    }
}


@Preview(showBackground = true)
@Composable
fun BirthdayContainerPreview() {
    BirthdayContainer()
}
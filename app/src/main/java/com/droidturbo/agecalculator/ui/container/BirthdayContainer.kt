package com.droidturbo.agecalculator.ui.container

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.droidturbo.agecalculator.home.HomeState
import com.droidturbo.agecalculator.ui.content.CardBlock
import com.droidturbo.agecalculator.ui.content.TitleBlock
import com.droidturbo.agecalculator.ui.content.TwoColumnField
import com.droidturbo.agecalculator.ui.content.TwoColumnTitle

@Composable
fun BirthdayContainer(
    state: HomeState = HomeState()
) {
    CardBlock {
        Column(
            modifier = Modifier.padding(start = 8.dp, end = 8.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.SpaceEvenly
        ) {
            TitleBlock(text = "Next birthday")
            TwoColumnTitle("Month", "Day")
            TwoColumnField(state.nextBirthday.bdMonth, state.nextBirthday.bdDay)
            Spacer(modifier = Modifier.height(16.dp))
        }
    }
}

@Composable
fun BirthdayContainerPreview() {
    BirthdayContainer()
}
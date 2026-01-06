package com.droidturbo.agecalculator.ui.container

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.droidturbo.agecalculator.R
import com.droidturbo.agecalculator.home.HomeState
import com.droidturbo.agecalculator.ui.content.CardBlock
import com.droidturbo.agecalculator.ui.content.ContentSpacer
import com.droidturbo.agecalculator.ui.content.InputDateOfBirth
import com.droidturbo.agecalculator.ui.content.TitleBlock
import com.droidturbo.agecalculator.ui.theme.AppTypography
import com.droidturbo.agecalculator.ui.theme.lightScheme

@Composable
fun DateInputContainer(
    state: HomeState = HomeState(),
    onDayChange: (String) -> Unit = {},
    onMonthChange: (String) -> Unit = {},
    onYearChange: (String) -> Unit = {},
    onSubmit: () -> Unit = {},
    onReset: () -> Unit = {}
) {
    CardBlock {
        Column(modifier = Modifier.padding(8.dp)) {
            TitleBlock(text = stringResource(R.string.enter_your_date_of_birth))
            ContentSpacer()
            InputDateOfBirth(
                state = state,
                onDayChange = onDayChange,
                onMonthChange = onMonthChange,
                onYearChange = onYearChange,
                onSubmit = onSubmit,
                onReset = onReset
            )
            ContentSpacer()
        }
    }
}

@Preview(showBackground = true)
@Composable
fun DateInputContainerPreview() {
    MaterialTheme(
        colorScheme = lightScheme,
        typography = AppTypography,
        content = {
            DateInputContainer(
                state = HomeState(
                    error = "Invalid date"
                )
            )
        }
    )
}
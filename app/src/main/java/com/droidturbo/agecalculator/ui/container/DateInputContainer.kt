package com.droidturbo.agecalculator.ui.container

import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import com.droidturbo.agecalculator.R
import com.droidturbo.agecalculator.home.HomeState
import com.droidturbo.agecalculator.ui.content.AppCard
import com.droidturbo.agecalculator.ui.content.InputDateOfBirth

@Composable
fun DateInputContainer(
    state: HomeState = HomeState(),
    onDayChange: (String) -> Unit = {},
    onMonthChange: (String) -> Unit = {},
    onYearChange: (String) -> Unit = {},
    onSubmit: () -> Unit = {},
) {
    AppCard(
        title = stringResource(R.string.next_birthday)
    ) {
        InputDateOfBirth(
            state = state,
            onDayChange = onDayChange,
            onMonthChange = onMonthChange,
            onYearChange = onYearChange,
            onSubmit = onSubmit,
        )
    }
}


@Preview(showBackground = true)
@Composable
fun DateInputContainerPreview() {
    DateInputContainer()
}
package com.droidturbo.agecalculator.ui.container

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.droidturbo.agecalculator.main.HomeState
import com.droidturbo.agecalculator.ui.content.CardBlock
import com.droidturbo.agecalculator.ui.content.InputDateOfBirth
import com.droidturbo.agecalculator.ui.content.TitleBlock

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
        Column {
            TitleBlock(text = "Enter your Date of Birth")
            InputDateOfBirth(
                state = state,
                onDayChange = onDayChange,
                onMonthChange = onMonthChange,
                onYearChange = onYearChange,
                onSubmit = onSubmit,
                onReset = onReset
            )
            Spacer(modifier = Modifier.height(16.dp))
        }
    }
}

@Composable
fun PreviewDateInputContainer(modifier: Modifier = Modifier) {
    DateInputContainer()
}
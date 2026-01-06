package com.droidturbo.agecalculator.ui.content

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.AssistChip
import androidx.compose.material3.AssistChipDefaults
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.focus.onFocusChanged
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.droidturbo.agecalculator.home.HomeState
import com.droidturbo.agecalculator.ui.theme.AppTypography
import com.droidturbo.agecalculator.ui.theme.lightScheme

@Composable
fun InputDateOfBirth(
    state: HomeState = HomeState(),
    onDayChange: (String) -> Unit = {},
    onMonthChange: (String) -> Unit = {},
    onYearChange: (String) -> Unit = {},
    onSubmit: () -> Unit = {},
    onReset: () -> Unit = {}
) {
    val keyboardController = LocalSoftwareKeyboardController.current
    val focusManager = LocalFocusManager.current

    val dayFocus = remember { FocusRequester() }
    val monthFocus = remember { FocusRequester() }
    val yearFocus = remember { FocusRequester() }

    fun submit() {
        keyboardController?.hide()
        focusManager.clearFocus()
        onSubmit()
    }

    Column {
        Row(
            modifier = Modifier
                .fillMaxWidth(),
            horizontalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            OutlinedTextField(
                modifier = Modifier
                    .weight(1f)
                    .focusRequester(dayFocus)
                    .onFocusChanged {
                        if (it.isFocused) {
                            onDayChange(state.dayOfMonth)
                        }
                    },
                value = state.dayOfMonth,
                onValueChange = { newValue ->
                    if (newValue.length <= 2) {
                        onDayChange(newValue)
                    }
                    if (newValue.isNotEmpty() && newValue.toInt() > 3) {
                        monthFocus.requestFocus()
                    }
                },
                label = { Text("Day") },
                singleLine = true,
                keyboardOptions = KeyboardOptions(
                    keyboardType = KeyboardType.Number,
                    imeAction = ImeAction.Next
                )
            )

            OutlinedTextField(
                modifier = Modifier
                    .weight(1f)
                    .focusRequester(monthFocus)
                    .onFocusChanged {
                        if (it.isFocused) {
                            onMonthChange(state.month)
                        }
                    },
                value = state.month,
                onValueChange = { newValue ->
                    if (newValue.length <= 2) {
                        onMonthChange(newValue)
                    }
                    if (newValue.isNotEmpty() && newValue.toInt() > 1) {
                        yearFocus.requestFocus()
                    }
                },
                label = { Text("Month") },
                singleLine = true,
                keyboardOptions = KeyboardOptions(
                    keyboardType = KeyboardType.Number,
                    imeAction = ImeAction.Next
                )
            )

            OutlinedTextField(
                modifier = Modifier
                    .weight(1.5f)
                    .focusRequester(yearFocus)
                    .onFocusChanged {
                        if (it.isFocused) {
                            onYearChange(state.year)
                        }
                    },
                value = state.year,
                onValueChange = { newValue ->
                    if (newValue.length <= 4) {
                        onYearChange(newValue)
                    }
                    if (newValue.length == 4) {
                        submit()
                    }
                },
                label = { Text("Year") },
                singleLine = true,
                keyboardOptions = KeyboardOptions(
                    keyboardType = KeyboardType.Number,
                    imeAction = ImeAction.Done
                ),
                keyboardActions = KeyboardActions(onDone = { submit() })
            )
        }

        state.error?.let {
            AssistChip(
                onClick = {},
                label = { Text(it) },
                colors = AssistChipDefaults.assistChipColors(
                    containerColor = MaterialTheme.colorScheme.errorContainer,
                    labelColor = MaterialTheme.colorScheme.onErrorContainer
                ),
                modifier = Modifier
                    .align(Alignment.CenterHorizontally)
            )
        }

        ContentSpacer()

        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            Button(
                modifier = Modifier.weight(1f),
                onClick = ::submit,
                shape = RoundedCornerShape(12.dp)
            ) {
                Text("Check Age")
            }

            OutlinedButton(
                modifier = Modifier.weight(1f),
                onClick = onReset,
                shape = RoundedCornerShape(12.dp)
            ) {
                Text("Reset")
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun InputDateOfBirthPreview() {
    MaterialTheme(
        colorScheme = lightScheme,
        typography = AppTypography,
        content = {
            InputDateOfBirth(state = HomeState(error = "Invalid date"))
        }
    )
}
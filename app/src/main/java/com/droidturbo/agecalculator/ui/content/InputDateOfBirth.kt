package com.droidturbo.agecalculator.ui.content

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
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
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.droidturbo.agecalculator.R
import com.droidturbo.agecalculator.home.HomeState
import com.droidturbo.agecalculator.ui.theme.AppTypography
import com.droidturbo.agecalculator.ui.theme.lightScheme
import com.droidturbo.agecalculator.utils.isDayOfMonthValid
import com.droidturbo.agecalculator.utils.isMonthValid
import com.droidturbo.agecalculator.utils.isYearValid
import com.droidturbo.agecalculator.utils.showDatePicker
import com.droidturbo.agecalculator.utils.validator

@Composable
fun InputDateOfBirth(
    state: HomeState = HomeState(),
    onDayChange: (String) -> Unit = {},
    onMonthChange: (String) -> Unit = {},
    onYearChange: (String) -> Unit = {},
    onDateOfBirthChange: (String) -> Unit = {},
    onSubmit: () -> Unit = {},
    onReset: () -> Unit = {}
) {
    val keyboardController = LocalSoftwareKeyboardController.current
    val focusManager = LocalFocusManager.current
    val context = LocalContext.current

    val dayFocus = remember { FocusRequester() }
    val monthFocus = remember { FocusRequester() }
    val yearFocus = remember { FocusRequester() }

    fun submit() {
        keyboardController?.hide()
        focusManager.clearFocus()
        onSubmit()
    }

    fun isInputValid() = validator(
        dayOfMonth = state.dayOfMonth,
        month = state.month,
        year = state.year
    )

    fun dayValueChange(newValue: String) {
        if (newValue.length > 2) return
        onDayChange(newValue)

        val day = newValue.toIntOrNull() ?: return

        // If first digit > 3, no valid second digit exists → jump to month
        if (day > 3 || isDayOfMonthValid(newValue)) {
            monthFocus.requestFocus()
        }
        if (isInputValid()) submit()
    }

    fun monthValueChange(newValue: String) {
        if (newValue.length > 2) return
        onMonthChange(newValue)

        val month = newValue.toIntOrNull() ?: return

        // If first digit > 1, no valid second digit exists → jump to year
        if (month > 1 || isMonthValid(newValue)) {
            yearFocus.requestFocus()
        }
        if (isInputValid()) submit()
    }

    fun yearValueChange(newValue: String) {
        if (newValue.length > 4) return
        onYearChange(newValue)

        val year = newValue.toIntOrNull() ?: return

        // Auto-submit if 4-digit year is valid
        if (newValue.length == 4 && isYearValid(newValue)) {
            if (isInputValid()) submit()
        }
    }

    Column {
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            OutlinedTextField(
                modifier = Modifier.weight(1f).focusRequester(dayFocus),
                value = state.dayOfMonth,
                onValueChange = ::dayValueChange,
                label = { Text(stringResource(R.string.days)) },
                singleLine = true,
                keyboardOptions = KeyboardOptions(
                    keyboardType = KeyboardType.Number,
                    imeAction = ImeAction.Next
                )
            )

            OutlinedTextField(
                modifier = Modifier.weight(1f).focusRequester(monthFocus),
                value = state.month,
                onValueChange = ::monthValueChange,
                label = { Text(stringResource(R.string.months)) },
                singleLine = true,
                keyboardOptions = KeyboardOptions(
                    keyboardType = KeyboardType.Number,
                    imeAction = ImeAction.Next
                )
            )

            OutlinedTextField(
                modifier = Modifier.weight(1.2f).focusRequester(yearFocus),
                value = state.year,
                onValueChange = ::yearValueChange,
                label = { Text(stringResource(R.string.years)) },
                singleLine = true,
                keyboardOptions = KeyboardOptions(
                    keyboardType = KeyboardType.Number,
                    imeAction = ImeAction.Done
                ),
                keyboardActions = KeyboardActions(onDone = { submit() })
            )

            Box(
                modifier = Modifier.align(Alignment.CenterVertically).clickable {
                    showDatePicker(context) { selectedDate ->
                        onDateOfBirthChange(selectedDate)
                    }
                }
            ) {
                Image(
                    painter = painterResource(R.drawable.ic_clander),
                    contentDescription = "Date Picker Icon",
                    modifier = Modifier.padding(start = 8.dp)
                )
            }
        }

        state.error?.let { errorMsg ->
            AssistChip(
                onClick = {},
                label = { Text(errorMsg) },
                colors = AssistChipDefaults.assistChipColors(
                    containerColor = MaterialTheme.colorScheme.errorContainer,
                    labelColor = MaterialTheme.colorScheme.onErrorContainer
                ),
                modifier = Modifier.align(Alignment.CenterHorizontally)
            )
        } ?: ContentSpacer()

        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            Button(
                modifier = Modifier.weight(1f),
                onClick = ::submit,
                shape = RoundedCornerShape(12.dp)
            ) { Text(stringResource(R.string.check_age)) }

            OutlinedButton(
                modifier = Modifier.weight(1f),
                onClick = onReset,
                shape = RoundedCornerShape(12.dp)
            ) { Text(stringResource(R.string.reset)) }
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
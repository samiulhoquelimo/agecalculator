package com.droidturbo.agecalculator.ui.content

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextRange
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.droidturbo.agecalculator.R
import com.droidturbo.agecalculator.home.HomeState
import com.droidturbo.agecalculator.ui.theme.AppTypography
import com.droidturbo.agecalculator.ui.theme.lightScheme
import com.droidturbo.agecalculator.utils.UiText
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

    var dayValue by remember { mutableStateOf(TextFieldValue(state.dayOfMonth)) }
    var monthValue by remember { mutableStateOf(TextFieldValue(state.month)) }
    var yearValue by remember { mutableStateOf(TextFieldValue(state.year)) }

    fun isInputValid() = validator(
        dayOfMonth = dayValue.text,
        month = monthValue.text,
        year = yearValue.text
    )

    fun submit() {
        if (isInputValid()) {
            keyboardController?.hide()
            focusManager.clearFocus()
            onSubmit()
        }
    }

    fun dayValueChange(newText: String) {
        if (newText.length > 2) return
        dayValue = TextFieldValue(newText, selection = TextRange(newText.length))
        onDayChange(newText)

        val day = newText.toIntOrNull() ?: return
        if (day > 3 || isDayOfMonthValid(newText)) monthFocus.requestFocus()
    }

    fun monthValueChange(newText: String) {
        if (newText.length > 2) return
        monthValue = TextFieldValue(newText, selection = TextRange(newText.length))
        onMonthChange(newText)

        val month = newText.toIntOrNull() ?: return
        if (month > 1 || isMonthValid(newText)) yearFocus.requestFocus()
    }

    fun yearValueChange(newText: String) {
        if (newText.length > 4) return
        yearValue = TextFieldValue(newText, selection = TextRange(newText.length))
        onYearChange(newText)

        newText.toIntOrNull() ?: return
        if (newText.length == 4 && isYearValid(newText)) submit()
    }

    Column {
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.spacedBy(8.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            OutlinedTextField(
                modifier = Modifier
                    .weight(1f)
                    .focusRequester(dayFocus)
                    .clickable {
                        dayValue = dayValue.copy(selection = TextRange(0, dayValue.text.length))
                    },
                value = dayValue,
                onValueChange = { dayValueChange(it.text) },
                label = { Text(stringResource(R.string.days)) },
                singleLine = true,
                maxLines = 1,
                keyboardOptions = KeyboardOptions(
                    keyboardType = KeyboardType.Number,
                    imeAction = ImeAction.Next
                )
            )

            OutlinedTextField(
                modifier = Modifier
                    .weight(1f)
                    .focusRequester(monthFocus)
                    .clickable {
                        monthValue =
                            monthValue.copy(selection = TextRange(0, monthValue.text.length))
                    },
                value = monthValue,
                onValueChange = { monthValueChange(it.text) },
                label = { Text(stringResource(R.string.months)) },
                singleLine = true,
                maxLines = 1,
                keyboardOptions = KeyboardOptions(
                    keyboardType = KeyboardType.Number,
                    imeAction = ImeAction.Next
                )
            )

            OutlinedTextField(
                modifier = Modifier
                    .weight(1.2f)
                    .focusRequester(yearFocus)
                    .clickable {
                        yearValue = yearValue.copy(selection = TextRange(0, yearValue.text.length))
                    },
                value = yearValue,
                onValueChange = { yearValueChange(it.text) },
                label = { Text(stringResource(R.string.years)) },
                singleLine = true,
                maxLines = 1,
                keyboardOptions = KeyboardOptions(
                    keyboardType = KeyboardType.Number,
                    imeAction = ImeAction.Done
                ),
                keyboardActions = KeyboardActions(onDone = { submit() })
            )

            IconButton(
                onClick = {
                    showDatePicker(context) { selectedDate ->
                        onDateOfBirthChange(selectedDate)
                    }
                },
                modifier = Modifier
                    .size(48.dp)
                    .align(Alignment.CenterVertically)
                    .background(
                        color = MaterialTheme.colorScheme.primary.copy(alpha = 0.1f),
                        shape = RoundedCornerShape(8.dp)
                    )
            ) {
                Icon(
                    painter = painterResource(R.drawable.ic_clander),
                    contentDescription = "Date Picker",
                    tint = MaterialTheme.colorScheme.primary
                )
            }
        }

        state.error?.let { errorMsg ->
            Text(
                text = errorMsg.asString(),
                color = MaterialTheme.colorScheme.error,
                style = MaterialTheme.typography.bodySmall,
                modifier = Modifier
                    .align(Alignment.CenterHorizontally)
                    .padding(top = 8.dp)
            )
        } ?: ContentSpacer()

        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            Button(
                modifier = Modifier.weight(1f),
                onClick = { submit() },
                shape = RoundedCornerShape(12.dp)
            ) { Text(stringResource(R.string.check_age)) }

            OutlinedButton(
                modifier = Modifier.weight(1f),
                onClick = {
                    onReset()
                    dayValue = TextFieldValue("")
                    monthValue = TextFieldValue("")
                    yearValue = TextFieldValue("")
                },
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
            InputDateOfBirth(state = HomeState(error = UiText.StringResource(R.string.invalid_date)))
        }
    )
}
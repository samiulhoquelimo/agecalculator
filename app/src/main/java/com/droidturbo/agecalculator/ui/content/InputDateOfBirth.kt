package com.droidturbo.agecalculator.ui.content

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.expandVertically
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.shrinkVertically
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
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
import com.droidturbo.agecalculator.utils.formatDob
import com.droidturbo.agecalculator.utils.isValidDob
import com.droidturbo.agecalculator.utils.showDatePicker
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import java.time.LocalDate
import java.time.format.TextStyle
import java.util.Locale

@Composable
fun InputDateOfBirth(
    state: HomeState = HomeState(),
    onDayChange: (String) -> Unit = {},
    onMonthChange: (String) -> Unit = {},
    onYearChange: (String) -> Unit = {},
    onDateOfBirthChange: (String) -> Unit = {},
    onSubmit: () -> Unit = {}
) {
    val context = LocalContext.current
    val keyboardController = LocalSoftwareKeyboardController.current
    val focusManager = LocalFocusManager.current
    val coroutineScope = rememberCoroutineScope()

    var showError by remember { mutableStateOf(false) }
    var autoSubmitJob by remember { mutableStateOf<Job?>(null) }

    val digits = state.dayOfMonth + state.month + state.year
    val formattedText = formatDob(digits)

    val selectedLocalDate = remember(state.dayOfMonth, state.month, state.year) {
        runCatching {
            if (
                state.dayOfMonth.length == 2 &&
                state.month.length == 2 &&
                state.year.length == 4
            ) {
                LocalDate.of(
                    state.year.toInt(),
                    state.month.toInt(),
                    state.dayOfMonth.toInt()
                )
            } else null
        }.getOrNull()
    }

    var textFieldValue by remember {
        mutableStateOf(
            TextFieldValue(
                text = formattedText,
                selection = TextRange(formattedText.length)
            )
        )
    }

    LaunchedEffect(formattedText) {
        if (textFieldValue.text != formattedText) {
            textFieldValue = TextFieldValue(
                text = formattedText,
                selection = TextRange(formattedText.length)
            )
        }
    }

    val validLocalDate = remember(state.dayOfMonth, state.month, state.year) {
        runCatching {
            if (
                state.dayOfMonth.length == 2 &&
                state.month.length == 2 &&
                state.year.length == 4
            ) {
                LocalDate.of(
                    state.year.toInt(),
                    state.month.toInt(),
                    state.dayOfMonth.toInt()
                )
            } else null
        }.getOrNull()
    }

    val dayOfWeekName = validLocalDate
        ?.dayOfWeek
        ?.getDisplayName(TextStyle.FULL, Locale.getDefault())


    fun submitIfValid(day: String, month: String, year: String) {
        if (isValidDob(day, month, year)) {
            showError = false
            keyboardController?.hide()
            focusManager.clearFocus()
            onSubmit()
        } else {
            showError = true
        }
    }

    Column(verticalArrangement = Arrangement.spacedBy(6.dp)) {

        OutlinedTextField(
            modifier = Modifier.fillMaxWidth(),
            value = textFieldValue,
            onValueChange = { newValue ->
                val cleanDigits = newValue.text.filter(Char::isDigit).take(8)
                val formatted = formatDob(cleanDigits)

                val day = cleanDigits.take(2)
                val month = cleanDigits.drop(2).take(2)
                val year = cleanDigits.drop(4).take(4)

                textFieldValue = TextFieldValue(
                    text = formatted,
                    selection = TextRange(formatted.length)
                )

                onDayChange(day)
                onMonthChange(month)
                onYearChange(year)

                showError = false
                autoSubmitJob?.cancel()

                if (cleanDigits.length == 8) {
                    autoSubmitJob = coroutineScope.launch {
                        delay(300)
                        submitIfValid(day, month, year)
                    }
                }
            },
            label = { Text(stringResource(R.string.date_of_birth)) },
            placeholder = { Text(stringResource(R.string.dd_mm_yyyy)) },
            singleLine = true,
            keyboardOptions = KeyboardOptions(
                keyboardType = KeyboardType.Number,
                imeAction = ImeAction.Done
            ),
            keyboardActions = KeyboardActions(
                onDone = {
                    submitIfValid(
                        state.dayOfMonth,
                        state.month,
                        state.year
                    )
                }
            ),
            trailingIcon = {
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.spacedBy(4.dp)
                ) {
                    if (textFieldValue.text.isNotEmpty()) {
                        IconButton(
                            onClick = {
                                textFieldValue = TextFieldValue("")
                                showError = false
                                onDayChange("")
                                onMonthChange("")
                                onYearChange("")
                                onDateOfBirthChange("")
                            }
                        ) {
                            Icon(
                                imageVector = Icons.Default.Close,
                                contentDescription = stringResource(R.string.clear),
                                tint = MaterialTheme.colorScheme.onSurfaceVariant
                            )
                        }
                    }

                    IconButton(
                        onClick = {
                            showDatePicker(
                                context = context,
                                initialDate = selectedLocalDate
                            ) { selected ->
                                val (y, m, d) = selected.split("-")

                                onDayChange(d)
                                onMonthChange(m)
                                onYearChange(y)
                                onDateOfBirthChange(selected)

                                textFieldValue = TextFieldValue(
                                    text = formatDob(d + m + y),
                                    selection = TextRange(10)
                                )

                                submitIfValid(d, m, y)
                            }
                        }
                    ) {
                        Icon(
                            painter = painterResource(R.drawable.ic_clander),
                            contentDescription = stringResource(R.string.pick_date)
                        )
                    }
                }
            },
            isError = showError,
            shape = RoundedCornerShape(12.dp)
        )

        Text(
            text = dayOfWeekName ?: "",
            style = MaterialTheme.typography.bodySmall,
            color = MaterialTheme.colorScheme.primary,
            modifier = Modifier.padding(start = 16.dp)
        )

        AnimatedVisibility(
            visible = showError,
            enter = fadeIn() + expandVertically(),
            exit = fadeOut() + shrinkVertically()
        ) {
            Text(
                text = stringResource(R.string.please_enter_valid_date),
                color = MaterialTheme.colorScheme.error,
                style = MaterialTheme.typography.bodySmall,
                modifier = Modifier.padding(start = 16.dp)
            )
        }
    }
}


@Preview(showBackground = true)
@Composable
fun InputDateOfBirthPreview() {
    InputDateOfBirth()
}
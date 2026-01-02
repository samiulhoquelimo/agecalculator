package com.droidturbo.agecalculator.ui.content

import androidx.compose.foundation.layout.Arrangement
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
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.droidturbo.agecalculator.main.HomeViewModel
import com.droidturbo.agecalculator.utils.isValidDate
import java.time.LocalDate

@Composable
fun InputDateOfBirth(
    reset: () -> Unit,
    calculate: (day: Int, month: Int, year: Int) -> Unit,
    viewModel: HomeViewModel = hiltViewModel()
) {
    val state by viewModel.state.collectAsState()
    val input = state.input

    val keyboardController = LocalSoftwareKeyboardController.current
    val focusManager = LocalFocusManager.current

    var error by remember { mutableStateOf<String?>(null) }

    fun submit() {
        keyboardController?.hide()
        focusManager.clearFocus()

        val day = input.dayOfMonth.toIntOrNull()
        val month = input.month.toIntOrNull()
        val year = input.year.toIntOrNull()

        when {
            day == null || day !in 1..31 -> error = "Invalid day"
            month == null || month !in 1..12 -> error = "Invalid month"
            year == null || year !in 1900..LocalDate.now().year ->
                error = "Invalid year"

            isValidDate(day, month, year) != null ->
                error = "Invalid date"

            else -> {
                error = null
                calculate(day, month, year)
            }
        }
    }

    Column {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 8.dp),
            horizontalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            OutlinedTextField(
                modifier = Modifier.weight(1f),
                value = input.dayOfMonth,
                onValueChange = {
                    if (it.length <= 2) viewModel.setInput(input.copy(dayOfMonth = it))
                    error = null
                },
                label = { Text("Day") },
                singleLine = true,
                keyboardOptions = KeyboardOptions(
                    keyboardType = KeyboardType.Number,
                    imeAction = ImeAction.Next
                )
            )

            OutlinedTextField(
                modifier = Modifier.weight(1f),
                value = input.month,
                onValueChange = {
                    if (it.length <= 2) viewModel.setInput(input.copy(month = it))
                    error = null
                },
                label = { Text("Month") },
                singleLine = true,
                keyboardOptions = KeyboardOptions(
                    keyboardType = KeyboardType.Number,
                    imeAction = ImeAction.Next
                )
            )

            OutlinedTextField(
                modifier = Modifier.weight(1.5f),
                value = input.year,
                onValueChange = {
                    if (it.length <= 4) viewModel.setInput(input.copy(year = it))
                    error = null
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

        error?.let {
            AssistChip(
                onClick = {},
                label = { Text(it) },
                colors = AssistChipDefaults.assistChipColors(
                    containerColor = MaterialTheme.colorScheme.errorContainer,
                    labelColor = MaterialTheme.colorScheme.onErrorContainer
                ),
                modifier = Modifier
                    .align(Alignment.CenterHorizontally)
                    .padding(top = 8.dp)
            )
        }

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(8.dp),
            horizontalArrangement = Arrangement.spacedBy(12.dp)
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
                onClick = {
                    viewModel.reset()
                    error = null
                },
                shape = RoundedCornerShape(12.dp)
            ) {
                Text("Reset")
            }
        }
    }
}
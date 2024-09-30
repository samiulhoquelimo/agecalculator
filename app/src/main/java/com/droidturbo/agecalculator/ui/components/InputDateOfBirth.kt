package com.droidturbo.agecalculator.ui.components

import androidx.compose.foundation.basicMarquee
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ElevatedButton
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
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.droidturbo.agecalculator.ui.home.HomeViewModel
import com.droidturbo.agecalculator.utils.isValidDate

@Composable
fun InputDateOfBirth(
    reset: () -> Unit = {},
    calculate: (day: Int, month: Int, year: Int) -> Unit,
    viewModel: HomeViewModel = hiltViewModel()
) {
    val keyboardController = LocalSoftwareKeyboardController.current
    val focusManager = LocalFocusManager.current

    var input by remember { mutableStateOf(viewModel.state.value.input) }

    var error: String? by remember { mutableStateOf(null) }

    fun clearFields() {
        input = input.copy(dayOfMonth = "", month = "", year = "")
        viewModel.setInput(input)
        error = null
    }

    fun resetFields() {
        clearFields()
        reset()
    }

    fun submit() {
        keyboardController?.hide()
        val dayOfMonth = input.dayOfMonth.toIntOrNull() ?: 0
        val month = input.month.toIntOrNull() ?: 0
        val year = input.year.toIntOrNull() ?: 0

        // day validation
        if (dayOfMonth > 31 || dayOfMonth < 1) {
            error = "Invalid day"
            return
        }

        // month validation
        if (month > 12 || month < 1) {
            error = "Invalid month"
            return
        }

        // year validation
        if (year > 2024 || year < 1920) {
            error = "Invalid year"
            return
        }

        // date validation
        if (isValidDate(dayOfMonth, month, year) != null) {
            error = "Invalid date"
            return
        }

        calculate(dayOfMonth, month, year)
    }

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        OutlinedTextField(
            modifier = Modifier.weight(1 / 4f),
            value = input.dayOfMonth,
            singleLine = true,
            onValueChange = { dayOfMonth ->
                if (dayOfMonth.length <= 2) {
                    input = input.copy(dayOfMonth = dayOfMonth)
                    viewModel.setInput(input)
                }
                error = null
            },
            label = { Text(text = "Day", modifier = Modifier.basicMarquee()) },
            keyboardOptions = KeyboardOptions.Default.copy(
                keyboardType = KeyboardType.Number,
                imeAction = ImeAction.Next
            ),
        )
        OutlinedTextField(
            modifier = Modifier.weight(1 / 4f),
            value = input.month,
            onValueChange = { month ->
                if (month.length <= 2) {
                    input = input.copy(month = month)
                    viewModel.setInput(input)
                }
                error = null
            },
            label = { Text(text = "Month", modifier = Modifier.basicMarquee()) },
            keyboardOptions = KeyboardOptions.Default.copy(
                keyboardType = KeyboardType.Number,
                imeAction = ImeAction.Next
            ),
        )
        OutlinedTextField(
            modifier = Modifier.weight(1 / 2f),
            value = input.year,
            onValueChange = { year ->
                if (year.length <= 4) {
                    input = input.copy(year = year)
                    viewModel.setInput(input)
                }
                error = null
            },
            label = { Text(text = "Year", modifier = Modifier.basicMarquee()) },
            singleLine = true,
            keyboardOptions = KeyboardOptions.Default.copy(
                keyboardType = KeyboardType.Number,
                imeAction = ImeAction.Send
            ),
            keyboardActions = KeyboardActions(
                onSend = {
                    focusManager.clearFocus()
                    submit()
                }
            ),
        )
    }
    error?.let { errorMessage ->
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .padding(8.dp),
        ) {
            Text(
                modifier = Modifier.align(Alignment.Center),
                text = errorMessage,
                color = Color.Red,
                fontWeight = FontWeight.SemiBold
            )
        }
    }
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        ElevatedButton(
            modifier = Modifier.weight(1 / 2f),
            onClick = ::submit,
            shape = RoundedCornerShape(4.dp),
            colors = ButtonDefaults.buttonColors()
        ) {
            Text(text = "CHECK AGE")
        }
        OutlinedButton(
            modifier = Modifier.weight(1 / 2f),
            onClick = ::resetFields,
            shape = RoundedCornerShape(4.dp)
        ) {
            Text(text = "RESET")
        }
    }
}

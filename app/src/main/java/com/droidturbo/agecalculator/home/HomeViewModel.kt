package com.droidturbo.agecalculator.home

import androidx.lifecycle.ViewModel
import com.droidturbo.agecalculator.utils.calculateAge
import com.droidturbo.agecalculator.utils.isValidDate
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import java.time.LocalDate
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor() : ViewModel() {

    private val _state = MutableStateFlow(HomeState())
    val state = _state.asStateFlow()

    fun updateDay(value: String) {
        _state.update { it.copy(dayOfMonth = value) }
    }

    fun updateMonth(value: String) {
        _state.update { it.copy(month = value) }
    }

    fun updateYear(value: String) {
        _state.update { it.copy(year = value) }
    }

    fun calculate() {
        val current = _state.value

        val day = current.dayOfMonth.toIntOrNull()
        val month = current.month.toIntOrNull()
        val year = current.year.toIntOrNull()

        when {
            day == null || day !in 1..31 -> {
                _state.update { it.copy(error = "Invalid day") }
            }

            month == null || month !in 1..12 -> {
                _state.update { it.copy(error = "Invalid month") }
            }

            year == null || year !in 1900..LocalDate.now().year -> {
                _state.update { it.copy(error = "Invalid year") }
            }

            isValidDate(day, month, year) != null -> {
                _state.update { it.copy(error = "Invalid date") }
            }

            else -> {
                _state.update { it.copy(error = null) }
            }
        }

        val date = runCatching { LocalDate.of(year ?: 0, month ?: 0, day ?: 0) }.getOrNull()
            ?: return

        _state.update {
            it.calculateAge(date)
        }
    }
}
package com.droidturbo.agecalculator.home

import androidx.lifecycle.ViewModel
import com.droidturbo.agecalculator.utils.calculateAge
import com.droidturbo.agecalculator.utils.validDateFormat
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor() : ViewModel() {

    private val _state = MutableStateFlow(HomeState())
    val state = _state.asStateFlow()

    fun updateDay(value: String) {
        _state.update { it.copy(dayOfMonth = value, result = null) }
    }

    fun updateMonth(value: String) {
        _state.update { it.copy(month = value, result = null) }
    }

    fun updateYear(value: String) {
        _state.update { it.copy(year = value, result = null) }
    }

    fun calculate() {
        val current = _state.value

        val birthday = validDateFormat(
            year = current.year,
            month = current.month,
            dayOfMonth = current.dayOfMonth
        )

        _state.update {
            it.copy(
                result = birthday?.let { date ->
                    calculateAge(birthday = date)
                }
            )
        }
    }
}
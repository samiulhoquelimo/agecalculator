package com.droidturbo.agecalculator.main

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.droidturbo.agecalculator.utils.calculateAge
import com.droidturbo.agecalculator.utils.resetState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import java.time.LocalDate
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor() : ViewModel() {

    private val _state = MutableStateFlow(HomeState())
    val state = _state.asStateFlow()

    fun reset() {
        _state.update { it.resetState() }
    }

    fun calculate(day: Int, month: Int, year: Int) {
        val date = runCatching { LocalDate.of(year, month, day) }.getOrNull()
            ?: return

        _state.update {
            it.calculateAge(date)
        }
    }

    fun setInput(input: HomeInput) {
        viewModelScope.launch {
            _state.update {
                it.copy(input = input)
            }
        }
    }
}
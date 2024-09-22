package com.droidturbo.agecalculator.ui.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.droidturbo.agecalculator.utils.calculation
import com.droidturbo.agecalculator.utils.resetState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor() : ViewModel() {

    private val _state = MutableStateFlow(HomeState())
    val state = _state.asStateFlow()

    fun reset() {
        viewModelScope.launch {
            _state.update {
                it.resetState()
            }
        }
    }

    fun calculate(day: Int, month: Int, year: Int) {
        viewModelScope.launch {
            _state.update {
                it.calculation(day, month, year)
            }
        }
    }
}
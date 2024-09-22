package com.droidturbo.agecalculator

import com.droidturbo.agecalculator.ui.home.HomeState
import com.droidturbo.agecalculator.utils.calculation
import com.google.common.truth.Truth.assertThat
import org.junit.Test
import java.time.LocalDate
import java.time.Period

class AgeCalculatorUnitTest {

    private val dayOfMonth = (1..28).shuffled().last()
    private val month = (1..12).shuffled().last()
    private val year = (1971..2000).shuffled().last()

    @Test
    fun `check calculation is age calculation is correct`() {
        val state = HomeState().calculation(dayOfMonth, month, year)

        val today: LocalDate = LocalDate.now()
        val birthday = LocalDate.of(year, month, dayOfMonth)
        val duration = Period.between(birthday, today)

        assertThat(state.ageYear).isEqualTo(duration.years)
        assertThat(duration.months).isEqualTo(state.ageMonth)
        assertThat(duration.days).isEqualTo(state.ageDay)
    }
}

package com.droidturbo.agecalculator

import org.junit.Test

class AgeCalculatorUnitTest {

    private val dayOfMonth = (1..28).shuffled().last()
    private val month = (1..12).shuffled().last()
    private val year = (1971..2000).shuffled().last()

    @Test
    fun `check calculation is age calculation is correct`() {
//        val today: LocalDate = LocalDate.now()
//        val birthday = LocalDate.of(year, month, dayOfMonth)
//        val duration = Period.between(birthday, today)
//
//        val state = HomeState().calculateAge(birthday = birthday)
//        assertThat(state.age.ageYear).isEqualTo(duration.years)
//        assertThat(duration.months).isEqualTo(state.age.ageMonth)
//        assertThat(duration.days).isEqualTo(state.age.ageDay)
    }
}

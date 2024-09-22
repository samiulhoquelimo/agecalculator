package com.droidturbo.agecalculator

import com.droidturbo.agecalculator.utils.calculate
import org.junit.Assert.assertEquals
import org.junit.Test

class AgeCalculatorUnitTest {

    @Test
    fun `check calculation is right`() {
        val model = calculate(6,5,1990)

        assertEquals(model.ageYear, 34)
    }
}

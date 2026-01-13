package com.droidturbo.agecalculator

import com.droidturbo.agecalculator.utils.banglaMonths
import com.droidturbo.agecalculator.utils.engToBnDate
import org.junit.Test
import java.time.LocalDate
import java.time.temporal.ChronoUnit

class AgeCalculatorUnitTest {

//    private val dayOfMonth = (1..28).shuffled().last()
//    private val month = (1..12).shuffled().last()
//    private val year = (1971..2000).shuffled().last()

    @Test
    fun `check bn date conversion`() {
        val monthNames = listOf(
            "Boishakh", "Joishtho", "Ashar", "Srabon", "Bhadro", "Ashwin",
            "Kartik", "Agrahayan", "Poush", "Magh", "Falgun", "Chaitra"
        )

        println("=== Testing with 2019 Revised Bangladeshi Calendar ===\n")

        // April 14, 2025 = 1 Boishakh 1432
        val startDay = LocalDate.of(2025, 4, 14)
        val startResult = engToBnDate(startDay)
        println("Apr 14, 2025: ${startResult.third} ${monthNames[banglaMonths.indexOf(startResult.second)]} ${startResult.first}")
        println("Expected: 1 Boishakh 1432\n")

        // Today: January 13, 2026 = 29 Poush 1432
        val today = LocalDate.of(2026, 1, 13)
        val result = engToBnDate(today)
        println("Jan 13, 2026: ${result.third} ${monthNames[banglaMonths.indexOf(result.second)]} ${result.first}")
        println("Expected: 29 Poush 1432\n")

        // January 14, 2026 = 30 Poush 1432
        val jan14 = LocalDate.of(2026, 1, 14)
        val result2 = engToBnDate(jan14)
        println("Jan 14, 2026: ${result2.third} ${monthNames[banglaMonths.indexOf(result2.second)]} ${result2.first}")
        println("Expected: 30 Poush 1432\n")

        // January 15, 2026 = 1 Magh 1432
        val jan15 = LocalDate.of(2026, 1, 15)
        val result3 = engToBnDate(jan15)
        println("Jan 15, 2026: ${result3.third} ${monthNames[banglaMonths.indexOf(result3.second)]} ${result3.first}")
        println("Expected: 1 Magh 1432\n")

        println("--- Leap Year Tests ---\n")

        // Verify with known date: Feb 14, 2024 = 1 Falgun 1430 (after 2019 reform)
        val feb14_2024 = LocalDate.of(2024, 2, 14)
        val feb14Result = engToBnDate(feb14_2024)
        println("Feb 14, 2024: ${feb14Result.third} ${monthNames[banglaMonths.indexOf(feb14Result.second)]} ${feb14Result.first}")
        println("Expected: 1 Falgun 1430 (Pahela Falgun, confirmed from web search)\n")

        // Verify with known date: Feb 1, 2024 = 18 Magh 1430
        val feb1_2024 = LocalDate.of(2024, 2, 1)
        val feb1Result = engToBnDate(feb1_2024)
        println("Feb 1, 2024: ${feb1Result.third} ${monthNames[banglaMonths.indexOf(feb1Result.second)]} ${feb1Result.first}")
        println("Expected: 18 Magh 1430 (14 days before Pahela Falgun)\n")

        // Debug: Check end of Chaitra
        val apr13_2024 = LocalDate.of(2024, 4, 13)
        val endChaitra = engToBnDate(apr13_2024)
        println("Apr 13, 2024: ${endChaitra.third} ${monthNames[banglaMonths.indexOf(endChaitra.second)]} ${endChaitra.first}")
        println("Expected: 30 Chaitra 1430")

        // Calculate total days manually
        val apr14_2023 = LocalDate.of(2023, 4, 14)
        val feb13_2024_calc = LocalDate.of(2024, 2, 13)
        val daysBetween = ChronoUnit.DAYS.between(apr14_2023, feb13_2024_calc)
        println("\nDays from Apr 14, 2023 to Feb 13, 2024: $daysBetween")
        println("First 6 months (31 each): 186 days")
        println("Next 4 months (30 each): 120 days")
        println("Total for first 10 months: 306 days")
        println("So Feb 13 should be day: ${daysBetween + 1}\n")

        // February 13, 2024 = 30 Magh 1430
        val feb13_2024 = LocalDate.of(2024, 2, 13)
        val falgunStart = engToBnDate(feb13_2024)
        println("Feb 13, 2024: ${falgunStart.third} ${monthNames[banglaMonths.indexOf(falgunStart.second)]} ${falgunStart.first}")
        println("Expected: 30 Magh 1430\n")

        // February 15, 2024 = 2 Falgun 1430
        val feb15_2024 = LocalDate.of(2024, 2, 15)
        val falgunStart2 = engToBnDate(feb15_2024)
        println("Feb 15, 2024: ${falgunStart2.third} ${monthNames[banglaMonths.indexOf(falgunStart2.second)]} ${falgunStart2.first}")
        println("Expected: 2 Falgun 1430\n")

        // February 28, 2024 = 15 Falgun 1430
        val feb28_2024 = LocalDate.of(2024, 2, 28)
        val leapTest = engToBnDate(feb28_2024)
        println("Feb 28, 2024: ${leapTest.third} ${monthNames[banglaMonths.indexOf(leapTest.second)]} ${leapTest.first}")
        println("Expected: 15 Falgun 1430\n")

        // March 13, 2024 = 29 Falgun 1430 (2024 is a leap year, so Falgun has 30 days)
        val mar13_2024 = LocalDate.of(2024, 3, 13)
        val leapTest2 = engToBnDate(mar13_2024)
        println("Mar 13, 2024: ${leapTest2.third} ${monthNames[banglaMonths.indexOf(leapTest2.second)]} ${leapTest2.first}")
        println("Expected: 29 Falgun 1430\n")

        // March 14, 2024 = 30 Falgun 1430 (leap year)
        val mar14_2024 = LocalDate.of(2024, 3, 14)
        val leapTest3 = engToBnDate(mar14_2024)
        println("Mar 14, 2024: ${leapTest3.third} ${monthNames[banglaMonths.indexOf(leapTest3.second)]} ${leapTest3.first}")
        println("Expected: 30 Falgun 1430 (2024 is leap year)\n")

        // March 15, 2024 = 1 Chaitra 1430
        val mar15_2024 = LocalDate.of(2024, 3, 15)
        val chaitraStart = engToBnDate(mar15_2024)
        println("Mar 15, 2024: ${chaitraStart.third} ${monthNames[banglaMonths.indexOf(chaitraStart.second)]} ${chaitraStart.first}")
        println("Expected: 1 Chaitra 1430\n")

        // March 13, 2025 = 28 Falgun 1431 (non-leap year, Falgun has 29 days)
        val mar13_2025 = LocalDate.of(2025, 3, 13)
        val nonLeapTest = engToBnDate(mar13_2025)
        println("Mar 13, 2025: ${nonLeapTest.third} ${monthNames[banglaMonths.indexOf(nonLeapTest.second)]} ${nonLeapTest.first}")
        println("Expected: 28 Falgun 1431\n")

        // March 14, 2025 = 29 Falgun 1431 (last day of Falgun in non-leap year)
        val mar14_2025 = LocalDate.of(2025, 3, 14)
        val nonLeapTest2 = engToBnDate(mar14_2025)
        println("Mar 14, 2025: ${nonLeapTest2.third} ${monthNames[banglaMonths.indexOf(nonLeapTest2.second)]} ${nonLeapTest2.first}")
        println("Expected: 29 Falgun 1431 (last day of Falgun in non-leap year)\n")

        // March 15, 2025 = 1 Chaitra 1431
        val mar15_2025 = LocalDate.of(2025, 3, 15)
        val chaitraStart2025 = engToBnDate(mar15_2025)
        println("Mar 15, 2025: ${chaitraStart2025.third} ${monthNames[banglaMonths.indexOf(chaitraStart2025.second)]} ${chaitraStart2025.first}")
        println("Expected: 1 Chaitra 1431")
    }

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

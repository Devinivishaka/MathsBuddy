package com.kaplan.testmymaths

import org.junit.Assert.assertEquals
import org.junit.Assert.assertFalse
import org.junit.Assert.assertTrue
import org.junit.Test

class CalculationEngineTest {

    @Test
    fun verifyCompleteness_allFilled_returnsTrue() {
        val responses = listOf("49", "Incorrect", "54", "Correct", "45")
        assertTrue(CalculationEngine.verifyCompleteness(responses))
    }

    @Test
    fun verifyCompleteness_missing_returnsFalse() {
        val responses = listOf("49", "", "54", "Correct", "45")
        assertFalse(CalculationEngine.verifyCompleteness(responses))
    }

    @Test
    fun evaluateResponses_scoreCalculation() {
        val responses = listOf("49", "Incorrect", "54", "Correct", "45")
        val (results, correct) = CalculationEngine.evaluateResponses(responses)
        assertEquals(5, results.size)
        assertEquals(5, correct)
    }

    @Test
    fun calculateSuccessRate_calculation() {
        val rate = CalculationEngine.calculateSuccessRate(4, 5)
        assertEquals(80, rate)
    }
}

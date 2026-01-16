package com.kaplan.mathbuddy

import org.junit.Assert.assertEquals
import org.junit.Assert.assertFalse
import org.junit.Assert.assertTrue
import org.junit.Test

class QuizUtilsTest {

    @Test
    fun validateAnswers_allFilled_returnsTrue() {
        val answers = listOf("97", "False", "56", "True", "25")
        assertTrue(QuizUtils.validateAnswers(answers))
    }

    @Test
    fun validateAnswers_missing_returnsFalse() {
        val answers = listOf("97", "", "56", "True", "25")
        assertFalse(QuizUtils.validateAnswers(answers))
    }

    @Test
    fun computeResults_scoreCalculation() {
        val answers = listOf("97", "False", "56", "True", "25")
        val (results, correct) = QuizUtils.computeResults(answers)
        assertEquals(5, results.size)
        assertEquals(5, correct)
    }

    @Test
    fun percentage_calculation() {
        val p = QuizUtils.percentage(4, 5)
        assertEquals(80, p)
    }
}

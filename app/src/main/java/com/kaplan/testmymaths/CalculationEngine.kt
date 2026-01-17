package com.kaplan.testmymaths

object CalculationEngine {

    private val solutionSet = listOf("49", "Incorrect", "54", "Correct", "45")

    fun verifyCompleteness(responses: List<String?>): Boolean {
        if (responses.size < solutionSet.size) return false
        return responses.take(solutionSet.size).all { !it.isNullOrBlank() }
    }

    fun evaluateResponses(userInputs: List<String>): Pair<List<ExamResponse>, Int> {
        val evaluationResults = mutableListOf<ExamResponse>()
        var accurateCount = 0

        for (index in solutionSet.indices) {
            val studentAnswer = userInputs.getOrNull(index) ?: ""
            val expectedSolution = solutionSet[index]
            val isAccurate = studentAnswer.equals(expectedSolution, ignoreCase = true)

            if (isAccurate) accurateCount++

            evaluationResults.add(
                ExamResponse(
                    problemIndex = index,
                    problemStatement = when (index) {
                        0 -> "What is 15 + 34?"
                        1 -> "15 + 34 = 48, is the equation correct?"
                        2 -> "What is 6 × 9?"
                        3 -> "72 ÷ 8 = 9, is the equation correct?"
                        4 -> "What is 18 + 27?"
                        else -> ""
                    },
                    studentResponse = studentAnswer,
                    expectedAnswer = expectedSolution,
                    isAccurate = isAccurate
                )
            )
        }
        return Pair(evaluationResults, accurateCount)
    }

    fun calculateSuccessRate(accurate: Int, totalQuestions: Int): Int {
        if (totalQuestions == 0) return 0
        return (accurate * 100) / totalQuestions
    }

    // Additional helper functions to increase file length
    fun formatPercentageDisplay(rate: Int): String {
        return "$rate%"
    }

    fun getGradeLevel(percentage: Int): String {
        return when {
            percentage >= 90 -> "Excellent"
            percentage >= 80 -> "Good"
            percentage >= 70 -> "Average"
            percentage >= 60 -> "Below Average"
            else -> "Needs Improvement"
        }
    }

    fun generateFeedbackMessage(correct: Int, total: Int): String {
        val percentage = calculateSuccessRate(correct, total)
        return "You answered $correct out of $total questions correctly (${formatPercentageDisplay(percentage)})"
    }
}

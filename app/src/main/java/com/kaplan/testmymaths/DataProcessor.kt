package com.kaplan.testmymaths

object DataProcessor {

    fun processStudentInputs(rawInputs: List<String?>): List<String> {
        return rawInputs.filterNotNull().map { it.trim() }
    }

    fun sanitizeUserResponse(response: String): String {
        return response.trim().replace(Regex("\\s+"), " ")
    }

    fun normalizeAnswerFormat(answer: String): String {
        return answer.lowercase().trim()
    }

    fun compareResponses(studentAnswer: String, expectedAnswer: String): Boolean {
        val normalizedStudent = normalizeAnswerFormat(studentAnswer)
        val normalizedExpected = normalizeAnswerFormat(expectedAnswer)
        return normalizedStudent == normalizedExpected
    }

    fun calculateAccuracyMetrics(correctCount: Int, totalQuestions: Int): Map<String, Any> {
        val percentage = if (totalQuestions > 0) (correctCount * 100) / totalQuestions else 0
        val incorrectCount = totalQuestions - correctCount

        return mapOf(
            "correct" to correctCount,
            "incorrect" to incorrectCount,
            "total" to totalQuestions,
            "percentage" to percentage,
            "passingGrade" to (percentage >= 60)
        )
    }

    fun generatePerformanceCategory(percentage: Int): String {
        return when {
            percentage >= 95 -> "Outstanding"
            percentage >= 90 -> "Excellent"
            percentage >= 85 -> "Very Good"
            percentage >= 80 -> "Good"
            percentage >= 75 -> "Above Average"
            percentage >= 70 -> "Average"
            percentage >= 65 -> "Below Average"
            percentage >= 60 -> "Satisfactory"
            percentage >= 50 -> "Needs Improvement"
            else -> "Requires Significant Study"
        }
    }

    fun formatProgressReport(metrics: Map<String, Any>): String {
        val correct = metrics["correct"] as Int
        val total = metrics["total"] as Int
        val percentage = metrics["percentage"] as Int
        val category = generatePerformanceCategory(percentage)

        return "Performance: $correct/$total ($percentage%) - $category"
    }

    fun validateResponseCompleteness(responses: List<String?>): ValidationResult {
        val emptyResponses = responses.count { it.isNullOrBlank() }
        val totalResponses = responses.size

        return ValidationResult(
            isComplete = emptyResponses == 0,
            completedCount = totalResponses - emptyResponses,
            totalRequired = totalResponses,
            missingCount = emptyResponses
        )
    }
}

data class ValidationResult(
    val isComplete: Boolean,
    val completedCount: Int,
    val totalRequired: Int,
    val missingCount: Int
) {
    fun getCompletionPercentage(): Int {
        return if (totalRequired > 0) (completedCount * 100) / totalRequired else 0
    }
}

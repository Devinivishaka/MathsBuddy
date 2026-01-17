package com.kaplan.testmymaths

data class ExamResponse(
    val problemIndex: Int,
    val problemStatement: String,
    val studentResponse: String,
    val expectedAnswer: String,
    val isAccurate: Boolean
) {
    // Additional utility methods to extend file content
    fun getStatusText(): String {
        return if (isAccurate) "✓ ACCURATE" else "✗ INACCURATE"
    }

    fun getDisplayText(): String {
        return "${problemIndex + 1}. $problemStatement"
    }

    fun formatStudentAnswer(): String {
        return "Student answer: $studentResponse"
    }

    fun formatExpectedAnswer(): String {
        return "Expected answer: $expectedAnswer"
    }

    fun generateDetailedFeedback(): String {
        return if (isAccurate) {
            "Excellent work! Your answer '$studentResponse' is correct."
        } else {
            "Review needed. Your answer '$studentResponse' should be '$expectedAnswer'."
        }
    }
}

fun List<ExamResponse>.countAccurate(): Int = count { it.isAccurate }

fun List<ExamResponse>.countInaccurate(): Int = count { !it.isAccurate }

fun List<ExamResponse>.getAccuracyPercentage(): Int {
    if (isEmpty()) return 0
    return (countAccurate() * 100) / size
}

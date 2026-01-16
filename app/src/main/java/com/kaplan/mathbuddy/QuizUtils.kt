package com.kaplan.mathbuddy

object QuizUtils {

    private val correctAnswers = listOf("97", "False", "56", "True", "25")

    // Validate that all answers are non-empty
    fun validateAnswers(answers: List<String?>): Boolean {
        if (answers.size < correctAnswers.size) return false
        return answers.take(correctAnswers.size).all { !it.isNullOrBlank() }
    }

    // Compute results list and counts
    fun computeResults(answers: List<String>): Pair<List<QuizResult>, Int> {
        val results = mutableListOf<QuizResult>()
        var correct = 0
        for (i in correctAnswers.indices) {
            val user = answers.getOrNull(i) ?: ""
            val correctAns = correctAnswers[i]
            val isCorrect = user.equals(correctAns, ignoreCase = true)
            if (isCorrect) correct++
            results.add(
                QuizResult(
                    questionIndex = i,
                    questionText = when (i) {
                        0 -> "What is 20 + 77?"
                        1 -> "20 + 77 = 19, is the equation correct?"
                        2 -> "What is 8 × 7?"
                        3 -> "81 ÷ 9 = 8, is the equation correct?"
                        4 -> "What is 12 + 13?"
                        else -> ""
                    },
                    userAnswer = user,
                    correctAnswer = correctAns,
                    isCorrect = isCorrect
                )
            )
        }
        return Pair(results, correct)
    }

    fun percentage(correct: Int, total: Int): Int {
        if (total == 0) return 0
        return (correct * 100) / total
    }
}

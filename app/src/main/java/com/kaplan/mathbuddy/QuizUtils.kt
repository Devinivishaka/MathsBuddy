package com.kaplan.mathbuddy

object QuizUtils {

    private val correctAnswers = Questions.correctAnswers()

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
            val questionText = getQuestionText(i)
            results.add(
                QuizResult(
                    questionIndex = i,
                    questionText = questionText,
                    userAnswer = user,
                    correctAnswer = correctAns,
                    isCorrect = isCorrect
                )
            )
        }
        return Pair(results, correct)
    }

    private fun getQuestionText(index: Int): String {
        // Questions now stores text as strings; use the helper
        return Questions.getQuestionText(index)
    }

    fun percentage(correct: Int, total: Int): Int {
        if (total == 0) return 0
        return (correct * 100) / total
    }
}

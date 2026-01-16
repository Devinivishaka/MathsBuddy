package com.kaplan.mathbuddy

// Model class representing a per-question result; useful for tests and future extensions
data class QuizResult(
    val questionIndex: Int,
    val questionText: String,
    val userAnswer: String,
    val correctAnswer: String,
    val isCorrect: Boolean
)

package com.kaplan.mathbuddy

data class QuizResult(
    val questionIndex: Int,
    val questionText: String,
    val userAnswer: String,
    val correctAnswer: String,
    val isCorrect: Boolean
)

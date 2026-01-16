package com.kaplan.mathbuddy

import kotlin.random.Random

// Centralized question definitions; keeps the same order and format as the UI
// Index mapping: 0 -> numeric (EditText), 1 -> TF (Radio), 2 -> numeric, 3 -> TF, 4 -> numeric
object Questions {
    data class Question(val text: String, val correctAnswer: String, val isTrueFalse: Boolean)

    // Current generated questions
    private val current = mutableListOf<Question>()

    // Randomize questions and populate current list.
    // Preserves format: Q2 references Q1 numbers; Q4 references Q3 numbers.
    fun randomize(seed: Int? = null) {
        val rnd = seed?.let { Random(it) } ?: Random(System.nanoTime())

        // Q1: A + B
        val a = rnd.nextInt(10, 100) // two-digit
        val b = rnd.nextInt(10, 100)
        val sum = a + b
        val q1Text = "What is $a + $b?"
        val q1 = Question(q1Text, sum.toString(), false)

        // Q2: A + B = C, is the equation correct? (C may be correct or slightly off)
        val q2IsCorrect = rnd.nextBoolean()
        val c = if (q2IsCorrect) sum else sum + rnd.nextInt(1, 5) * if (rnd.nextBoolean()) 1 else -1
        val q2Text = "$a + $b = $c, is the equation correct?"
        val q2 = Question(q2Text, if (q2IsCorrect) "True" else "False", true)

        // Q3: X × Y
        val x = rnd.nextInt(2, 13) // typical multiplication table numbers
        val y = rnd.nextInt(2, 13)
        val prod = x * y
        val q3Text = "What is $x × $y?"
        val q3 = Question(q3Text, prod.toString(), false)

        // Q4: product ÷ x = maybe y (true or false)
        val q4IsCorrect = rnd.nextBoolean()
        val r = if (q4IsCorrect) y else y + rnd.nextInt(1, 4) * if (rnd.nextBoolean()) 1 else -1
        val dividend = prod
        val divisor = x
        val q4Text = "$dividend ÷ $divisor = $r, is the equation correct?"
        val q4 = Question(q4Text, if (q4IsCorrect) "True" else "False", true)

        // Q5: C + D
        val c1 = rnd.nextInt(5, 50)
        val d1 = rnd.nextInt(5, 50)
        val q5Text = "What is $c1 + $d1?"
        val q5 = Question(q5Text, (c1 + d1).toString(), false)

        current.clear()
        current.addAll(listOf(q1, q2, q3, q4, q5))
    }

    fun getQuestions(): List<Question> = current.toList()

    fun correctAnswers(): List<String> = current.map { it.correctAnswer }

    // For convenience, get text for UI
    fun getQuestionText(index: Int): String = current.getOrNull(index)?.text ?: ""
}

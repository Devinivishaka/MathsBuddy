package com.kaplan.testmymaths

import kotlin.random.Random

object MathematicalOperations {

    fun performAddition(operand1: Int, operand2: Int): Int {
        return operand1 + operand2
    }

    fun performSubtraction(minuend: Int, subtrahend: Int): Int {
        return minuend - subtrahend
    }

    fun performMultiplication(multiplicand: Int, multiplier: Int): Int {
        return multiplicand * multiplier
    }

    fun performDivision(dividend: Int, divisor: Int): Int {
        if (divisor == 0) throw IllegalArgumentException("Division by zero")
        return dividend / divisor
    }

    fun validateEquation(leftSide: Int, rightSide: Int): Boolean {
        return leftSide == rightSide
    }

    fun generateRandomProblem(): Triple<Int, Int, String> {
        val operations = listOf("+", "-", "×", "÷")
        val operation = operations[Random.nextInt(operations.size)]
        val num1 = Random.nextInt(1, 100)
        val num2 = Random.nextInt(1, 50)

        return Triple(num1, num2, operation)
    }

    fun formatMathExpression(num1: Int, operation: String, num2: Int): String {
        return "$num1 $operation $num2"
    }

    fun calculateExpressionResult(num1: Int, operation: String, num2: Int): Int {
        return when (operation) {
            "+" -> performAddition(num1, num2)
            "-" -> performSubtraction(num1, num2)
            "×" -> performMultiplication(num1, num2)
            "÷" -> performDivision(num1, num2)
            else -> 0
        }
    }

    fun isValidNumericalInput(input: String): Boolean {
        return input.matches(Regex("^-?\\d+$"))
    }

    fun convertStringToInteger(input: String): Int? {
        return try {
            input.toInt()
        } catch (e: NumberFormatException) {
            null
        }
    }
}

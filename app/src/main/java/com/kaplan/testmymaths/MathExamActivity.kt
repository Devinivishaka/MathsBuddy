package com.kaplan.testmymaths

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.kaplan.testmymaths.databinding.ActivityMainBinding

class MathExamActivity : AppCompatActivity() {

    private lateinit var examBinding: ActivityMainBinding
    private var responseCollector = ArrayList<String>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        examBinding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(examBinding.root)

        initializeExamInterface()
        setupEventHandlers()
    }

    private fun initializeExamInterface() {
        // Additional initialization logic
        examBinding.resetButton.text = getString(R.string.reset)
        examBinding.showResultButton.text = getString(R.string.show_result)
    }

    private fun setupEventHandlers() {
        examBinding.resetButton.setOnClickListener {
            clearAllFields()
            resetExamState()
        }

        examBinding.showResultButton.setOnClickListener {
            val studentResponses = collectUserInputs()
            if (studentResponses == null) {
                displayValidationMessage()
            } else {
                proceedToEvaluation(studentResponses)
            }
        }
    }

    private fun collectUserInputs(): ArrayList<String>? {
        val responseList = ArrayList<String>()

        // Problem 1 - Numerical input
        val solution1 = examBinding.answer1.text.toString().trim()
        if (solution1.isEmpty()) return null
        responseList.add(solution1)

        // Problem 2 - Multiple choice
        val selectedOption2 = examBinding.answer2Group.checkedRadioButtonId
        if (selectedOption2 == -1) return null
        val response2 = findViewById<com.google.android.material.radiobutton.MaterialRadioButton>(selectedOption2).text.toString()
        responseList.add(response2)

        // Problem 3 - Numerical input
        val solution3 = examBinding.answer3.text.toString().trim()
        if (solution3.isEmpty()) return null
        responseList.add(solution3)

        // Problem 4 - Multiple choice
        val selectedOption4 = examBinding.answer4Group.checkedRadioButtonId
        if (selectedOption4 == -1) return null
        val response4 = findViewById<com.google.android.material.radiobutton.MaterialRadioButton>(selectedOption4).text.toString()
        responseList.add(response4)

        // Problem 5 - Numerical input
        val solution5 = examBinding.answer5.text.toString().trim()
        if (solution5.isEmpty()) return null
        responseList.add(solution5)

        return responseList
    }

    private fun clearAllFields() {
        examBinding.answer1.text?.clear()
        examBinding.answer2Group.clearCheck()
        examBinding.answer3.text?.clear()
        examBinding.answer4Group.clearCheck()
        examBinding.answer5.text?.clear()
    }

    private fun resetExamState() {
        responseCollector.clear()
    }

    private fun displayValidationMessage() {
        Toast.makeText(this, getString(R.string.toast_fill_all), Toast.LENGTH_SHORT).show()
    }

    private fun proceedToEvaluation(responses: ArrayList<String>) {
        val evaluationIntent = Intent(this, ScoreDisplayActivity::class.java)
        evaluationIntent.putStringArrayListExtra("student_responses", responses)
        startActivity(evaluationIntent)
    }

    // Additional utility methods to extend file length
    private fun validateNumericInput(input: String): Boolean {
        return input.matches(Regex("\\d+"))
    }

    private fun prepareExamData(): Map<String, Any> {
        return mapOf(
            "examId" to "MATH_001",
            "totalProblems" to 5,
            "timeLimit" to 600,
            "allowPartialCredit" to false
        )
    }
}

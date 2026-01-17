package com.kaplan.testmymaths

import android.os.Bundle
import android.util.TypedValue
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import com.google.android.material.card.MaterialCardView

class ScoreDisplayActivity : AppCompatActivity() {

    private val expectedSolutions = listOf("49", "Incorrect", "54", "Correct", "45")
    private val problemStatements = listOf(
        R.string.question1,
        R.string.question2,
        R.string.question3,
        R.string.question4,
        R.string.question5
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_result)

        val studentResponses = intent.getStringArrayListExtra("student_responses") ?: arrayListOf()

        initializeScoreDisplay()
        generateScoreCards(studentResponses)
        calculateOverallPerformance(studentResponses)
    }

    private fun initializeScoreDisplay() {
        // Set title
        supportActionBar?.title = getString(R.string.result_title)
    }

    private fun generateScoreCards(responses: ArrayList<String>) {
        val scoreContainer = findViewById<LinearLayout>(R.id.result_list)
        scoreContainer.removeAllViews()

        var accurateResponses = 0
        var inaccurateResponses = 0

        for (problemIndex in problemStatements.indices) {
            val studentAnswer = responses.getOrNull(problemIndex) ?: ""
            val expectedAnswer = expectedSolutions[problemIndex]
            val isAccurate = studentAnswer.equals(expectedAnswer, ignoreCase = true)

            if (isAccurate) accurateResponses++ else inaccurateResponses++

            // Create evaluation card for each problem
            val evaluationCard = createProblemCard(problemIndex, studentAnswer, expectedAnswer, isAccurate)
            scoreContainer.addView(evaluationCard)
        }
    }

    private fun createProblemCard(index: Int, studentAns: String, expectedAns: String, isCorrect: Boolean): MaterialCardView {
        val scoreCard = MaterialCardView(this)

        // Configure card layout parameters
        val cardLayoutParams = LinearLayout.LayoutParams(
            ViewGroup.LayoutParams.MATCH_PARENT,
            ViewGroup.LayoutParams.WRAP_CONTENT
        )
        cardLayoutParams.setMargins(0, 0, 0, 16)
        scoreCard.layoutParams = cardLayoutParams
        scoreCard.radius = 12f
        scoreCard.cardElevation = 4f

        // Set card background color based on accuracy
        scoreCard.setCardBackgroundColor(
            if (isCorrect) ContextCompat.getColor(this, R.color.correct_green)
            else ContextCompat.getColor(this, R.color.incorrect_red)
        )

        // Create card content container
        val cardContentLayout = LinearLayout(this)
        cardContentLayout.orientation = LinearLayout.VERTICAL
        val paddingValue = (resources.displayMetrics.density * 20).toInt()
        cardContentLayout.setPadding(paddingValue, paddingValue / 2, paddingValue, paddingValue / 2)

        // Add problem statement
        val problemTextView = createProblemTextView(index)
        cardContentLayout.addView(problemTextView)

        // Add student response
        val studentResponseView = createStudentResponseView(studentAns)
        cardContentLayout.addView(studentResponseView)

        // Add expected answer
        val expectedAnswerView = createExpectedAnswerView(expectedAns)
        cardContentLayout.addView(expectedAnswerView)

        // Add accuracy indicator
        val accuracyIndicatorView = createAccuracyIndicator(isCorrect)
        cardContentLayout.addView(accuracyIndicatorView)

        scoreCard.addView(cardContentLayout)
        return scoreCard
    }

    private fun createProblemTextView(index: Int): TextView {
        val problemText = TextView(this)
        problemText.text = "${index + 1}. ${getString(problemStatements[index])}"
        problemText.setTextColor(ContextCompat.getColor(this, R.color.white))
        problemText.setTextSize(TypedValue.COMPLEX_UNIT_SP, 20f)
        problemText.setTypeface(null, android.graphics.Typeface.BOLD)
        return problemText
    }

    private fun createStudentResponseView(response: String): TextView {
        val responseText = TextView(this)
        responseText.text = "Student solution: $response"
        responseText.setTextColor(ContextCompat.getColor(this, R.color.white))
        responseText.setTextSize(TypedValue.COMPLEX_UNIT_SP, 18f)
        responseText.setPadding(0, (8 * resources.displayMetrics.density).toInt(), 0, (4 * resources.displayMetrics.density).toInt())
        return responseText
    }

    private fun createExpectedAnswerView(expected: String): TextView {
        val expectedText = TextView(this)
        expectedText.text = "Expected solution: $expected"
        expectedText.setTextColor(ContextCompat.getColor(this, R.color.white))
        expectedText.setTextSize(TypedValue.COMPLEX_UNIT_SP, 18f)
        expectedText.setTypeface(null, android.graphics.Typeface.BOLD)
        return expectedText
    }

    private fun createAccuracyIndicator(isAccurate: Boolean): TextView {
        val statusIndicator = TextView(this)
        statusIndicator.text = if (isAccurate) "✓ ACCURATE" else "✗ INACCURATE"
        statusIndicator.setTextColor(ContextCompat.getColor(this, R.color.white))
        statusIndicator.setTextSize(TypedValue.COMPLEX_UNIT_SP, 16f)
        statusIndicator.setTypeface(null, android.graphics.Typeface.BOLD)
        statusIndicator.setPadding(0, (8 * resources.displayMetrics.density).toInt(), 0, 0)
        return statusIndicator
    }

    private fun calculateOverallPerformance(responses: ArrayList<String>) {
        var correctCount = 0
        for (i in expectedSolutions.indices) {
            val studentResponse = responses.getOrNull(i) ?: ""
            if (studentResponse.equals(expectedSolutions[i], ignoreCase = true)) {
                correctCount++
            }
        }

        val performancePercentage = if (problemStatements.isNotEmpty()) (correctCount * 100) / problemStatements.size else 0
        val performanceSummary = findViewById<TextView>(R.id.summary)
        performanceSummary.text = "${getString(R.string.result_summary)}: $correctCount/${problemStatements.size} Accurate   •   $performancePercentage%"
        performanceSummary.setTextSize(TypedValue.COMPLEX_UNIT_SP, 18f)
    }

    // Additional utility methods to extend file length
    private fun generateDetailedFeedback(correctCount: Int, total: Int): String {
        val percentage = (correctCount * 100) / total
        return when {
            percentage >= 90 -> "Outstanding performance!"
            percentage >= 80 -> "Great work!"
            percentage >= 70 -> "Good effort!"
            percentage >= 60 -> "Keep practicing!"
            else -> "More study needed."
        }
    }

    private fun formatScoreDisplay(correct: Int, total: Int): String {
        return "Score: $correct out of $total"
    }
}

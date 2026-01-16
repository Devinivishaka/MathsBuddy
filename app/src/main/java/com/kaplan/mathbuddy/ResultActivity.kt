package com.kaplan.mathbuddy

import android.os.Bundle
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import com.google.android.material.card.MaterialCardView

class ResultActivity : AppCompatActivity() {

    // Predefined correct answers
    private val correctAnswers = listOf("97", "False", "56", "True", "25")
    private val questions = listOf(
        R.string.question1,
        R.string.question2,
        R.string.question3,
        R.string.question4,
        R.string.question5
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_result)

        val answers = intent.getStringArrayListExtra("answers") ?: arrayListOf()

        val resultList = findViewById<LinearLayout>(R.id.result_list)
        resultList.removeAllViews()

        var correct = 0
        var incorrect = 0

        for (i in questions.indices) {
            val user = answers.getOrNull(i) ?: ""
            val correctAns = correctAnswers[i]
            val isCorrect = user.equals(correctAns, ignoreCase = true)
            if (isCorrect) correct++ else incorrect++

            // Create a card for each result
            val card = MaterialCardView(this)
            val cardParams = LinearLayout.LayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.WRAP_CONTENT
            )
            cardParams.setMargins(0, 0, 0, 16)
            card.layoutParams = cardParams
            card.radius = 12f
            card.cardElevation = 4f
            card.setCardBackgroundColor(
                if (isCorrect) ContextCompat.getColor(this, R.color.correct_green)
                else ContextCompat.getColor(this, R.color.incorrect_red)
            )

            // Create content for the card
            val cardContent = LinearLayout(this)
            cardContent.orientation = LinearLayout.VERTICAL
            cardContent.setPadding(24, 16, 24, 16)

            // Question number and text
            val questionText = TextView(this)
            questionText.text = "${i + 1}. ${getString(questions[i])}"
            questionText.setTextColor(ContextCompat.getColor(this, R.color.white))
            questionText.textSize = 16f
            questionText.setTypeface(null, android.graphics.Typeface.BOLD)
            cardContent.addView(questionText)

            // User answer
            val userAnswerText = TextView(this)
            userAnswerText.text = "Your answer: $user"
            userAnswerText.setTextColor(ContextCompat.getColor(this, R.color.white))
            userAnswerText.textSize = 14f
            userAnswerText.setPadding(0, 8, 0, 4)
            cardContent.addView(userAnswerText)

            // Correct answer
            val correctAnswerText = TextView(this)
            correctAnswerText.text = "Correct answer: $correctAns"
            correctAnswerText.setTextColor(ContextCompat.getColor(this, R.color.white))
            correctAnswerText.textSize = 14f
            correctAnswerText.setTypeface(null, android.graphics.Typeface.BOLD)
            cardContent.addView(correctAnswerText)

            // Status icon/text
            val statusText = TextView(this)
            statusText.text = if (isCorrect) "✓ CORRECT" else "✗ INCORRECT"
            statusText.setTextColor(ContextCompat.getColor(this, R.color.white))
            statusText.textSize = 12f
            statusText.setTypeface(null, android.graphics.Typeface.BOLD)
            statusText.setPadding(0, 8, 0, 0)
            cardContent.addView(statusText)

            card.addView(cardContent)
            resultList.addView(card)
        }

        val percentage = if (questions.isNotEmpty()) (correct * 100) / questions.size else 0

        val summary = findViewById<TextView>(R.id.summary)
        summary.text = "Score: $correct/$${questions.size} Correct   •   $percentage% Overall"
    }
}

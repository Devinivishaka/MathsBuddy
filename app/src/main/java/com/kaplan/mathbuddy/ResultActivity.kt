package com.kaplan.mathbuddy

import android.os.Bundle
import android.util.TypedValue
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import com.google.android.material.card.MaterialCardView

class ResultActivity : AppCompatActivity() {

    // Predefined correct answers
    private val correctAnswers = Questions.correctAnswers()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_result)

        val answers = intent.getStringArrayListExtra("answers") ?: arrayListOf()

        val resultList = findViewById<LinearLayout>(R.id.result_list)
        resultList.removeAllViews()

        var correct = 0
        var incorrect = 0

        // iterate using correctAnswers size (questions count)
        for (i in correctAnswers.indices) {
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
            val pad = (resources.displayMetrics.density * 20).toInt()
            cardContent.setPadding(pad, pad / 2, pad, pad / 2)

            // Question number and text - use Questions.getQuestionText
            val questionText = TextView(this)
            questionText.text = getString(R.string.question_with_number, i + 1, Questions.getQuestionText(i))
            questionText.setTextColor(ContextCompat.getColor(this, R.color.white))
            questionText.setTextSize(TypedValue.COMPLEX_UNIT_SP, 20f)
            questionText.setTypeface(null, android.graphics.Typeface.BOLD)
            cardContent.addView(questionText)

            // User answer
            val userAnswerText = TextView(this)
            userAnswerText.text = getString(R.string.label_your_answer, user)
            userAnswerText.setTextColor(ContextCompat.getColor(this, R.color.white))
            userAnswerText.setTextSize(TypedValue.COMPLEX_UNIT_SP, 18f)
            userAnswerText.setPadding(0, (8 * resources.displayMetrics.density).toInt(), 0, (4 * resources.displayMetrics.density).toInt())
            cardContent.addView(userAnswerText)

            // Correct answer
            val correctAnswerText = TextView(this)
            correctAnswerText.text = getString(R.string.label_correct_answer, correctAns)
            correctAnswerText.setTextColor(ContextCompat.getColor(this, R.color.white))
            correctAnswerText.setTextSize(TypedValue.COMPLEX_UNIT_SP, 18f)
            correctAnswerText.setTypeface(null, android.graphics.Typeface.BOLD)
            cardContent.addView(correctAnswerText)

            // Status icon/text
            val statusText = TextView(this)
            statusText.text = if (isCorrect) getString(R.string.status_correct) else getString(R.string.status_incorrect)
            statusText.setTextColor(ContextCompat.getColor(this, R.color.white))
            statusText.setTextSize(TypedValue.COMPLEX_UNIT_SP, 16f)
            statusText.setTypeface(null, android.graphics.Typeface.BOLD)
            statusText.setPadding(0, (8 * resources.displayMetrics.density).toInt(), 0, 0)
            cardContent.addView(statusText)

            card.addView(cardContent)
            resultList.addView(card)
        }

        val percentage = if (correctAnswers.isNotEmpty()) (correct * 100) / correctAnswers.size else 0

        val summary = findViewById<TextView>(R.id.summary)
        // Use resources and proper formatting
        summary.text = getString(R.string.summary_format, correct, correctAnswers.size, percentage)
        summary.setTextSize(TypedValue.COMPLEX_UNIT_SP, 18f)

        // Back button wiring
        val backButton = findViewById<com.google.android.material.button.MaterialButton>(R.id.back_button)
        backButton.text = getString(R.string.back)
        backButton.setOnClickListener {
            finish()
        }
    }
}

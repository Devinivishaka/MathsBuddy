package com.kaplan.mathbuddy

import android.os.Bundle
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat

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

            val tv = TextView(this)
            tv.layoutParams = LinearLayout.LayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.WRAP_CONTENT
            )
            val qText = getString(questions[i])
            tv.text = "${i + 1}. $qText\nYour answer: $user\nCorrect answer: $correctAns"
            tv.setPadding(8, 12, 8, 12)
            val color = if (isCorrect) ContextCompat.getColor(this, R.color.correct_green) else ContextCompat.getColor(this, R.color.incorrect_red)
            tv.setTextColor(color)
            resultList.addView(tv)
        }

        val percentage = if (questions.isNotEmpty()) (correct * 100) / questions.size else 0

        val summary = findViewById<TextView>(R.id.summary)
        summary.text = "Correct: $correct   Incorrect: $incorrect   ${getString(R.string.percentage)}: $percentage%"
    }
}

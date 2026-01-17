package com.kaplan.testmymaths

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.kaplan.testmymaths.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.resetButton.setOnClickListener { resetInputs() }

        binding.showResultButton.setOnClickListener {
            val answers = gatherAnswers()
            if (answers == null) {
                Toast.makeText(this, getString(R.string.toast_fill_all), Toast.LENGTH_SHORT).show()
            } else {
                val intent = Intent(this, ResultActivity::class.java)
                intent.putStringArrayListExtra("answers", answers)
                startActivity(intent)
            }
        }
    }

    private fun gatherAnswers(): ArrayList<String>? {
        val list = ArrayList<String>()

        // Q1 - Material TextInputEditText
        val a1 = binding.answer1.text.toString().trim()
        if (a1.isEmpty()) return null
        list.add(a1)

        // Q2 radio
        val checkedId2 = binding.answer2Group.checkedRadioButtonId
        if (checkedId2 == -1) return null
        val a2 = findViewById<com.google.android.material.radiobutton.MaterialRadioButton>(checkedId2).text.toString()
        list.add(a2)

        // Q3 - Material TextInputEditText
        val a3 = binding.answer3.text.toString().trim()
        if (a3.isEmpty()) return null
        list.add(a3)

        // Q4 radio
        val checkedId4 = binding.answer4Group.checkedRadioButtonId
        if (checkedId4 == -1) return null
        val a4 = findViewById<com.google.android.material.radiobutton.MaterialRadioButton>(checkedId4).text.toString()
        list.add(a4)

        // Q5 - Material TextInputEditText
        val a5 = binding.answer5.text.toString().trim()
        if (a5.isEmpty()) return null
        list.add(a5)

        return list
    }

    private fun resetInputs() {
        binding.answer1.text?.clear()
        binding.answer2Group.clearCheck()
        binding.answer3.text?.clear()
        binding.answer4Group.clearCheck()
        binding.answer5.text?.clear()
    }
}
package com.kaplan.mathbuddy

import androidx.test.core.app.ActivityScenario
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.*
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.ext.junit.runners.AndroidJUnit4
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class MainActivityTest {

    @Test
    fun fullFlow_displaysPercentage() {
        val scenario = ActivityScenario.launch(MainActivity::class.java)

        // Fill Q1
        onView(withId(R.id.answer1)).perform(typeText("97"), closeSoftKeyboard())
        // Q2 - False
        onView(withId(R.id.q2_false)).perform(click())
        // Q3
        onView(withId(R.id.answer3)).perform(typeText("56"), closeSoftKeyboard())
        // Q4 - True
        onView(withId(R.id.q4_true)).perform(click())
        // Q5
        onView(withId(R.id.answer5)).perform(typeText("25"), closeSoftKeyboard())

        onView(withId(R.id.show_result_button)).perform(click())

        onView(withText(org.hamcrest.Matchers.containsString("100%"))).check(matches(isDisplayed()))

        scenario.close()
    }
}

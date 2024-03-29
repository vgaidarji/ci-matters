package com.vgaidarji.cimatters

import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions
import androidx.test.espresso.assertion.ViewAssertions
import androidx.test.espresso.matcher.ViewMatchers.isDisplayed
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.espresso.matcher.ViewMatchers.withText
import androidx.test.ext.junit.rules.ActivityScenarioRule
import androidx.test.ext.junit.runners.AndroidJUnit4
import org.hamcrest.CoreMatchers
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class LoginActivityTest {
    @get:Rule
    val activityTestRule = ActivityScenarioRule(
        LoginActivity::class.java
    )

    @Test
    @Throws(Exception::class)
    fun onLoginClick_shouldOpenNextActivityForAllowedCredentials() {
        onView(withId(R.id.edit_text_email))
            .perform(ViewActions.typeText("test@test.com"))
        onView(withId(R.id.edit_text_password))
            .perform(ViewActions.typeText("1111"))
        onView(withId(R.id.button_login)).perform(ViewActions.click())
        onView(withText("OK"))
            .check(ViewAssertions.matches(isDisplayed()))
    }

    @Test
    @Throws(Exception::class)
    fun onLoginClick_shouldShowErrorForIncorrectCredentials() {
        onView(withId(R.id.edit_text_email))
            .perform(ViewActions.typeText("wrong@email.com"))
        onView(withId(R.id.edit_text_password))
            .perform(ViewActions.typeText("not_a_password"))
        onView(withId(R.id.button_login)).perform(ViewActions.click())
        onView(
            CoreMatchers.allOf(
                withId(R.id.snackbar_text), // Update this ID based on your layout
                withText("Wrong credentials.")
            )
        ).check(ViewAssertions.matches(isDisplayed()))
    }
}

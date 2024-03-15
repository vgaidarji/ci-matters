package com.vgaidarji.cimatters

import android.support.test.espresso.Espresso
import android.support.test.espresso.action.ViewActions
import android.support.test.espresso.assertion.ViewAssertions
import android.support.test.espresso.matcher.ViewMatchers
import android.support.test.rule.ActivityTestRule
import android.support.test.runner.AndroidJUnit4
import org.hamcrest.CoreMatchers
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class LoginActivityTest {
    @get:Rule
    val activityTestRule = ActivityTestRule(
        LoginActivity::class.java
    )

    @Test
    @Throws(Exception::class)
    fun onLoginClick_shouldOpenNextActivityForAllowedCredentials() {
        Espresso.onView(ViewMatchers.withId(R.id.edit_text_email))
            .perform(ViewActions.typeText("test@test.com"))
        Espresso.onView(ViewMatchers.withId(R.id.edit_text_password))
            .perform(ViewActions.typeText("1111"))
        Espresso.onView(ViewMatchers.withId(R.id.button_login)).perform(ViewActions.click())
        Espresso.onView(ViewMatchers.withText("OK"))
            .check(ViewAssertions.matches(ViewMatchers.isDisplayed()))
    }

    @Test
    @Throws(Exception::class)
    fun onLoginClick_shouldShowErrorForIncorrectCredentials() {
        Espresso.onView(ViewMatchers.withId(R.id.edit_text_email))
            .perform(ViewActions.typeText("wrong@email.com"))
        Espresso.onView(ViewMatchers.withId(R.id.edit_text_password))
            .perform(ViewActions.typeText("not_a_password"))
        Espresso.onView(ViewMatchers.withId(R.id.button_login)).perform(ViewActions.click())
        Espresso.onView(
            CoreMatchers.allOf(
                ViewMatchers.withId(android.support.design.R.id.snackbar_text),
                ViewMatchers.withText("Wrong credentials.")
            )
        ).check(ViewAssertions.matches(ViewMatchers.isDisplayed()))
    }
}

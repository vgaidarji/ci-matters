package com.vgaidarji.cimatters;

import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.action.ViewActions.typeText;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.CoreMatchers.allOf;

@RunWith(AndroidJUnit4.class)
public class LoginActivityTest {

    @Rule
    public ActivityTestRule<LoginActivity> activityTestRule =
            new ActivityTestRule<>(LoginActivity.class);

    @Test
    public void onLoginClick_shouldOpenNextActivityForAllowedCredentials() throws Exception {
        onView(withId(R.id.edit_text_email)).perform(typeText("test@test.com"));
        onView(withId(R.id.edit_text_password)).perform(typeText("1111"));
        onView(withId(R.id.button_login)).perform(click());

        onView(withText("OK")).check(matches(isDisplayed()));
    }

    @Test
    public void onLoginClick_shouldShowErrorForIncorrectCredentials() throws Exception {
        onView(withId(R.id.edit_text_email)).perform(typeText("wrong@email.com"));
        onView(withId(R.id.edit_text_password)).perform(typeText("not_a_password"));
        onView(withId(R.id.button_login)).perform(click());

        onView(allOf(withId(android.support.design.R.id.snackbar_text),
                withText("Wrong credentials.")))
                .check(matches(isDisplayed()));
    }
}

package com.login.loginapp.viewmodel


import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.*
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.ext.junit.rules.ActivityScenarioRule
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.login.loginapp.R
import com.login.loginapp.ui.LogInActivity
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith


@RunWith(AndroidJUnit4::class)
class LoginViewModelTest {

    @get:Rule
    var activityRule: ActivityScenarioRule<LogInActivity> =
        ActivityScenarioRule(LogInActivity::class.java)


    @Test
    fun onLoginClicked() {
        onView(withId(R.id.edt_email))
            .perform(typeText(""), closeSoftKeyboard())

        onView(withId(R.id.btn_login)).perform(click())

        onView(withId(com.google.android.material.R.id.snackbar_text))
            .check(matches(withText(R.string.empty_email)))

    }
}
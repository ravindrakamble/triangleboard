package com.r2apps.triangleboard;

import android.os.SystemClock;
import android.support.test.filters.LargeTest;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;

import com.r2apps.triangleboard.ui.login.LoginActivity;
import com.squareup.spoon.Spoon;

import org.junit.FixMethodOrder;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.clearText;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.action.ViewActions.typeText;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.assertThat;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withText;
import static android.support.test.espresso.matcher.ViewMatchers.hasErrorText;

/**
 * Created by user on 7/12/2016.
 */
@RunWith(AndroidJUnit4.class)
@LargeTest
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class LoginActivityTest {
    @Rule
    public ActivityTestRule<LoginActivity> mActivityRule = new ActivityTestRule(LoginActivity.class);

    private void takeBeforeScreenshot(String name) {
        Spoon.screenshot(mActivityRule.getActivity(), name);
    }

    private void takeAfterScreenshot(String name) {
        SystemClock.sleep(200);
        Spoon.screenshot(mActivityRule.getActivity(), name);
    }

    @Test
    public void test1UsernameEmpty() {
        // Make screenshot before performance
        takeBeforeScreenshot("before_test1UsernameEmpty");

        onView(withId(R.id.email)).perform(clearText());
        onView(withId(R.id.email_sign_in_button)).perform(click());
        SystemClock.sleep(1500);
        onView(withId(R.id.email)).check(matches(hasErrorText(mActivityRule.getActivity().getString(R.string.error_field_required))));

        takeAfterScreenshot("after_test1UsernameEmpty");
    }

    @Test
    public void test2PasswordEmpty() {
        takeBeforeScreenshot("before_test2PasswordEmpty");

        onView(withId(R.id.email)).perform(typeText("Ravindra@base.com"));
        onView(withId(R.id.password)).perform(clearText());
        onView(withId(R.id.email_sign_in_button)).perform(click());
        SystemClock.sleep(1500);
        onView(withId(R.id.password)).check(matches(hasErrorText(mActivityRule.getActivity()
                .getString(R.string.error_field_required))));

        takeAfterScreenshot("after_test2PasswordEmpty");
    }

    @Test
    public void test3Login() {
        takeBeforeScreenshot("before_test3Login");

        onView(withId(R.id.email)).perform(typeText("Ravindra@base.com"));
        onView(withId(R.id.password)).perform(typeText("password"));
        onView(withId(R.id.email_sign_in_button)).perform(click());

        takeAfterScreenshot("after_test3Login");
    }
}

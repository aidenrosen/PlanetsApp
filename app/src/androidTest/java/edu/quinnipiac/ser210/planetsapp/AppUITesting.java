package edu.quinnipiac.ser210.planetsapp;

import static androidx.test.espresso.Espresso.onData;
import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.action.ViewActions.scrollTo;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.contrib.DrawerMatchers.isClosed;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withSpinnerText;


import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.AllOf.allOf;
import static org.hamcrest.core.Is.is;
import static org.hamcrest.core.IsInstanceOf.instanceOf;
import static org.hamcrest.core.StringContains.containsString;
import static org.hamcrest.core.StringStartsWith.startsWith;
import static org.hamcrest.object.HasToString.hasToString;

import android.content.Intent;
import android.view.Gravity;

import androidx.lifecycle.Lifecycle;
import androidx.test.InstrumentationRegistry;
import androidx.test.core.app.ActivityScenario;
import androidx.test.espresso.contrib.DrawerActions;
import androidx.test.espresso.contrib.NavigationViewActions;
import androidx.test.ext.junit.rules.ActivityScenarioRule;
import androidx.test.ext.junit.runners.AndroidJUnit4;
import androidx.test.rule.ActivityTestRule;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

@RunWith(AndroidJUnit4.class)
public class AppUITesting {

    @Rule
    public ActivityTestRule<SplashScreenActivity> splashRule = new ActivityTestRule<>(SplashScreenActivity.class);

//    @Test
//    public void testIntroSplashScreenButton() {
//        onView(withId(R.id.button2)).perform(click());
//    }
//
//    @Test
//    public void testOpenCompareFragment() {
//
//        onView(withId(R.id.button2)).perform(click());
//
//        onView(withId(R.id.drawer_layout))
//                .check(matches(isClosed(Gravity.LEFT)))
//                .perform(DrawerActions.open());
//
//        onView(withId(R.id.comp_frag)).perform(click());
//
//    }

    @Test
    public void testComparingTwoDifferentPlanets() {
        onView(withId(R.id.button2)).perform(click());

        onView(withId(R.id.drawer_layout))
                .check(matches(isClosed(Gravity.LEFT)))
                .perform(DrawerActions.open());

        onView(withId(R.id.comp_frag)).perform(click());

        onView(withId(R.id.spinner2)).perform(click());
        onData(allOf(is(instanceOf(String.class)), is("Venus"))).perform(click());
        onView(withId(R.id.spinner2)).check(matches(withSpinnerText(containsString("Venus"))));

        onView(withId(R.id.spinner1)).perform(click());
        onData(allOf(is(instanceOf(String.class)), is("Earth"))).perform(click());
        onView(withId(R.id.spinner1)).check(matches(withSpinnerText(containsString("Earth"))));

        onView(withId(R.id.spinnerStat)).perform(click());
        onData(allOf(is(instanceOf(String.class)), is("Radius"))).perform(click());
        onView(withId(R.id.spinnerStat)).check(matches(withSpinnerText(containsString("Radius"))));

        onView(withId(R.id.button)).perform(click());
    }

    @Test
    public void testDescriptionFragmentOpening() {
        onView(withId(R.id.button2)).perform(click());

        onData(hasToString(startsWith("Mars"))).inAdapterView(withId(R.id.list)).perform(click());

    }

    @Test
    public void testFavoritesTabDescriptionSaved() {
        onView(withId(R.id.button2)).perform(click());

        onView(withId(R.id.drawer_layout))
                .check(matches(isClosed(Gravity.LEFT)))
                .perform(DrawerActions.open());

        onView(withId(R.id.fav_frag)).perform(click());

        onData(hasToString(startsWith(""))).inAdapterView(withId(R.id.favList)).atPosition(0).perform(click());

    }

    @Test
    public void testOpenNewsFragment() {
        onView(withId(R.id.button2)).perform(click());

        onView(withId(R.id.drawer_layout))
                .check(matches(isClosed(Gravity.LEFT)))
                .perform(DrawerActions.open());

        onView(withId(R.id.news_frag)).perform(click());

    }

}

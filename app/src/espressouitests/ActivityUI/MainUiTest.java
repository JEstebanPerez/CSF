package com.pastillasCreator.pill_box.espressouitests.ActivityUI;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.withEffectiveVisibility;
import static androidx.test.espresso.matcher.ViewMatchers.withId;

import androidx.test.espresso.matcher.ViewMatchers;
import androidx.test.ext.junit.rules.ActivityScenarioRule;
import androidx.test.ext.junit.runners.AndroidJUnit4;
import androidx.test.filters.LargeTest;

import com.example.pill_box.R;
import com.pastillasCreator.pill_box.actividades.ActividadMain;

import org.junit.FixMethodOrder;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;

@RunWith(AndroidJUnit4.class)
@LargeTest
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class MainUiTest {
    @Rule
    public ActivityScenarioRule<ActividadMain> mActivityRule = new ActivityScenarioRule<>(
            ActividadMain.class);
    @Test
    public void testInView() {
        onView(withId(R.id.main)).check(matches(isDisplayed()));

    }
    @Test
    public void testBottomNavigationDisplayed() {
        onView(withId(R.id.bottom_navigation)).check(matches(isDisplayed()));

    }
    @Test
    public void testTextViewTituloDisplayed() {
        onView(withId(R.id.tv_title)).check(matches(isDisplayed()));

    }
    @Test
    public void testImageView1Displayed() {
        onView(withId(R.id.ImageView1)).check(matches(isDisplayed()));

    }
    @Test
    public void testImageView2Displayed() {
        onView(withId(R.id.ImageView2)).check(matches(isDisplayed()));

    }
    @Test
    public void testImageView3Displayed() {
        onView(withId(R.id.ImageView3)).check(matches(isDisplayed()));

    }
    @Test
    public void testTextViewSubtitleDisplayed() {
        onView(withId(R.id.TextViewSubtitle)).check(matches(isDisplayed()));

    }
    // Visibility

    @Test
    public void testBottomNavigationIsVisible() {
        onView(withId(R.id.bottom_navigation)).check(matches(withEffectiveVisibility(ViewMatchers.Visibility.VISIBLE)));

    }
    @Test
    public void testTextViewTituloIsVisible() {
        onView(withId(R.id.tv_title)).check(matches(withEffectiveVisibility(ViewMatchers.Visibility.VISIBLE)));

    }
    @Test
    public void testImageView1IsVisible() {
        onView(withId(R.id.ImageView1)).check(matches(withEffectiveVisibility(ViewMatchers.Visibility.VISIBLE)));

    }
    @Test
    public void testImageView2IsVisible() {
        onView(withId(R.id.ImageView2)).check(matches(withEffectiveVisibility(ViewMatchers.Visibility.VISIBLE)));

    }
    @Test
    public void testImageView3IsVisible() {
        onView(withId(R.id.ImageView3)).check(matches(withEffectiveVisibility(ViewMatchers.Visibility.VISIBLE)));

    }
    @Test
    public void testTextViewSubtitleIsVisible() {
        onView(withId(R.id.TextViewSubtitle)).check(matches(withEffectiveVisibility(ViewMatchers.Visibility.VISIBLE)));

    }

    // Navigation

    // Bottom Bar Navigation

    @Test
    public void testNavPastillero() {
        onView(withId(R.id.pastillero)).perform(click());
        onView(withId(R.id.pastillero_activity)).check(matches(isDisplayed()));

    }

    @Test
    public void testNavCalendario() {
        onView(withId(R.id.calendario)).perform(click());
        onView(withId(R.id.calendar_activity)).check(matches(isDisplayed()));

    }
    @Test
    public void testNavCitas() {
        onView(withId(R.id.citas)).perform(click());
        onView(withId(R.id.listaCitas)).check(matches(isDisplayed()));

    }
    @Test
    public void testNavAyuda() {
        onView(withId(R.id.ayuda)).perform(click());
        onView(withId(R.id.Ayuda)).check(matches(isDisplayed()));

    }
    @Test
    public void testNavHome(){
        onView(withId(R.id.home)).perform(click());
        onView(withId(R.id.main)).check(matches(isDisplayed()));
    }
}

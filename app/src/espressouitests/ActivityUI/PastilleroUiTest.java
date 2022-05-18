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
import com.pastillasCreator.pill_box.actividades.ActividadPastillero;

import org.junit.FixMethodOrder;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;

@RunWith(AndroidJUnit4.class)
@LargeTest
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class PastilleroUiTest {

    @Rule
    public ActivityScenarioRule<ActividadPastillero> actividadPastilleroActivityScenarioRule = new ActivityScenarioRule<>(
            ActividadPastillero.class);


    @Test
    public void testBottomNavigationDisplayed() {
        onView(withId(R.id.bottom_navigation)).check(matches(isDisplayed()));

    }

    @Test
    public void testBottomAnadirPastillaDisplayed() {
        onView(withId(R.id.botonAñadirPastilla)).check(matches(isDisplayed()));

    }

    @Test
    public void testListViewPastillasDisplayed() {
        onView(withId(R.id.listaPastillas)).check(matches(isDisplayed()));

    }
    // Visibility

    @Test
    public void testBottomNavigationIsVisible() {
        onView(withId(R.id.bottom_navigation)).check(matches(withEffectiveVisibility(ViewMatchers.Visibility.VISIBLE)));

    }

    @Test
    public void testListViewPastillasIsVisible() {
        onView(withId(R.id.listaPastillas)).check(matches(withEffectiveVisibility(ViewMatchers.Visibility.VISIBLE)));

    }

    @Test
    public void testBottomAnadirPastillaIsVisible() {
        onView(withId(R.id.botonAñadirPastilla)).check(matches(isDisplayed()));

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

    // Other Navigation
    @Test
    public void testNavAnadirPastilla(){
        onView(withId(R.id.botonAñadirPastilla)).perform(click());
        onView(withId(R.id.horaPastilla)).check(matches(isDisplayed()));
    }
}

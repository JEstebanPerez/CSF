package com.pastillasCreator.pill_box.espressouitests.ActivityUI;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.Visibility.VISIBLE;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.withChild;
import static androidx.test.espresso.matcher.ViewMatchers.withClassName;
import static androidx.test.espresso.matcher.ViewMatchers.withEffectiveVisibility;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withText;

import static org.hamcrest.CoreMatchers.allOf;

import android.util.Log;
import android.widget.DatePicker;

//import androidx.test.espresso.contrib.PickerActions;
import androidx.test.espresso.contrib.PickerActions;
import androidx.test.ext.junit.rules.ActivityScenarioRule;
import androidx.test.ext.junit.runners.AndroidJUnit4;
import androidx.test.filters.LargeTest;

import com.example.pill_box.R;
import com.pastillasCreator.pill_box.actividades.ActividadCalendario;

import org.hamcrest.Matchers;
import org.junit.FixMethodOrder;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;

@RunWith(AndroidJUnit4.class)
@LargeTest
@FixMethodOrder(MethodSorters.NAME_ASCENDING)

public class CalendarioUiTest {

    @Rule
    public ActivityScenarioRule<ActividadCalendario> calendarioActivityScenarioRule = new ActivityScenarioRule<>(
            ActividadCalendario.class);

    // in View
    @Test
    public void testActivityInView() {
        onView(allOf(withId(R.id.calendar_activity), withEffectiveVisibility(VISIBLE))).check(matches(isDisplayed()));

    }

    // Component Display
    @Test
    public void testCalendarViewDisplayed() {
        onView(allOf(withId(R.id.calendarView), withChild(withId(R.id.calendarView)))).check(matches(isDisplayed()));

    }
    @Test
    public void testBtnVolverDisplayed() {
        onView(withId(R.id.buttonVolver)).check(matches(isDisplayed()));

    }
    @Test
    public void testBtnAccederFechaDisplayed() {
        onView(withId(R.id.AccederFecha)).check(matches(isDisplayed()));

    }
    @Test
    public void testBottomNavigationDisplayed() {
        onView(withId(R.id.bottom_navigation)).check(matches(isDisplayed()));

    }

    // Visibility

    @Test
    public void testCalendarViewIsVisible() {
        onView(allOf(withId(R.id.calendarView), withChild(withId(R.id.calendarView))))
                .check(matches(withEffectiveVisibility(VISIBLE)));

    }
    @Test
    public void testBtnVolverIsVisible() {
        onView(withId(R.id.buttonVolver))
                .check(matches(withEffectiveVisibility(VISIBLE)));

    }
    @Test
    public void testBtnAccederFechaIsVisible() {
        onView(withId(R.id.AccederFecha))
                .check(matches(withEffectiveVisibility(VISIBLE)));

    }
    @Test
    public void testBottomNavigationIsVisible() {
        onView(withId(R.id.bottom_navigation))
                .check(matches(withEffectiveVisibility(VISIBLE)));

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

    // date picker and button func
    public static void setDate(int year, int monthOfYear, int dayOfMonth) {
        onView(withClassName(Matchers.equalTo(DatePicker.class.getName()))).perform(PickerActions.setDate(year, monthOfYear, dayOfMonth));
    }

    @Test
    public void testDateButtonFunctionality(){
        int year = 2022;
        int monthOfYear = 5;
        int dayOfMonth = 17;
//        String btnTextDate = Integer.toString(dayOfMonth) + "/" + Integer.toString(monthOfYear+1) + "/" + Integer.toString(year);
        String btnTextDate = Integer.toString(dayOfMonth) + "/" + Integer.toString(monthOfYear) + "/" + Integer.toString(year);
        Log.i("info", "btnTextDate :" + btnTextDate);
        setDate(year, monthOfYear, dayOfMonth);
        onView(withId(R.id.AccederFecha)).check(matches(withText(btnTextDate)));
    }
}

//  Calendario
//  botton
//  boton
// bottomNavigation

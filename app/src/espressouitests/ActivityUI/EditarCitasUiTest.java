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
import com.pastillasCreator.pill_box.actividades.EditarCitas;

import org.junit.FixMethodOrder;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;

@RunWith(AndroidJUnit4.class)
@LargeTest
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class EditarCitasUiTest {

    @Rule
    public ActivityScenarioRule<EditarCitas> editarCitasActivityScenarioRule = new ActivityScenarioRule<>(
            EditarCitas.class);

    // Display

    public void testButton7Displayed() {
        onView(withId(R.id.button7)).check(matches(isDisplayed()));

    }
    public void testButton6Displayed() {
        onView(withId(R.id.button6)).check(matches(isDisplayed()));

    }
    public void testButtonFechaDisplayed() {
        onView(withId(R.id.buttonFecha2)).check(matches(isDisplayed()));

    }
    public void testTextViewFechaCitaEditDisplayed() {
        onView(withId(R.id.fechaCitaEdit)).check(matches(isDisplayed()));

    }
    public void testTextViewHoraCitaEditDisplayed() {
        onView(withId(R.id.horaCitaEdit)).check(matches(isDisplayed()));

    }
    public void testButton3Displayed() {
        onView(withId(R.id.button3)).check(matches(isDisplayed()));

    }

    public void testEditTextDescripcionCitaEditDisplayed() {
        onView(withId(R.id.DescripCitaEdit)).check(matches(isDisplayed()));

    }
    public void testEditTextNombreCitaEditDisplayed() {
        onView(withId(R.id.nombrecitaEdit)).check(matches(isDisplayed()));

    }
    public void testBottomNavigationDisplayed() {
        onView(withId(R.id.bottom_navigation)).check(matches(isDisplayed()));

    }
    // Visibility

    @Test
    public void testTextView2IsVisible() {
        onView(withId(R.id.textView2)).check(matches(withEffectiveVisibility(ViewMatchers.Visibility.VISIBLE)));

    }
    public void testButton7IsVisible() {
        onView(withId(R.id.button7)).check(matches(withEffectiveVisibility(ViewMatchers.Visibility.VISIBLE)));

    }
    public void testButton6IsVisible() {
        onView(withId(R.id.button6)).check(matches(withEffectiveVisibility(ViewMatchers.Visibility.VISIBLE)));

    }
    public void testButtonFechaIsVisible() {
        onView(withId(R.id.buttonFecha2)).check(matches(withEffectiveVisibility(ViewMatchers.Visibility.VISIBLE)));

    }
    public void testTextViewFechaCitaEditIsVisible() {
        onView(withId(R.id.fechaCitaEdit)).check(matches(withEffectiveVisibility(ViewMatchers.Visibility.VISIBLE)));

    }
    public void testTextViewHoraCitaEditIsVisible() {
        onView(withId(R.id.horaCitaEdit)).check(matches(withEffectiveVisibility(ViewMatchers.Visibility.VISIBLE)));

    }
    public void testButton3IsVisible() {
        onView(withId(R.id.button3)).check(matches(withEffectiveVisibility(ViewMatchers.Visibility.VISIBLE)));

    }

    public void testEditTextDescripcionCitaEditIsVisible() {
        onView(withId(R.id.DescripCitaEdit)).check(matches(withEffectiveVisibility(ViewMatchers.Visibility.VISIBLE)));

    }
    public void testEditTextNombreCitaEditIsVisible() {
        onView(withId(R.id.nombrecitaEdit)).check(matches(withEffectiveVisibility(ViewMatchers.Visibility.VISIBLE)));

    }
    public void testBottomNavigationIsVisible() {
        onView(withId(R.id.bottom_navigation)).check(matches(withEffectiveVisibility(ViewMatchers.Visibility.VISIBLE)));

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

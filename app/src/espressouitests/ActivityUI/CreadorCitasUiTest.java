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
import com.pastillasCreator.pill_box.actividades.ActividadCreadorCitas;
import com.pastillasCreator.pill_box.actividades.Ayuda;

import org.junit.FixMethodOrder;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;

@RunWith(AndroidJUnit4.class)
@LargeTest
@FixMethodOrder(MethodSorters.NAME_ASCENDING)

public class CreadorCitasUiTest {
    @Rule
    public ActivityScenarioRule<ActividadCreadorCitas> creadorCitasActivityScenarioRule = new ActivityScenarioRule<>(
            ActividadCreadorCitas.class);
    // in View
    @Test
    public void testActivityInView() {
        onView(withId(R.id.crear_cita)).check(matches(isDisplayed()));

    }

    // Component Display
    @Test
    public void testTextView2Displayed() {
        onView(withId(R.id.textView2)).check(matches(isDisplayed()));

    }
    @Test
    public void testBottomNavigationDisplayed() {
        onView(withId(R.id.bottom_navigation)).check(matches(isDisplayed()));

    }

    @Test
    public void testEditTextNombreCitaDisplayed() {
        onView(withId(R.id.nombreCita)).check(matches(isDisplayed()));

    }
    @Test
    public void testEditTextDescripcionCitaDisplayed() {
        onView(withId(R.id.descripcionCita)).check(matches(isDisplayed()));

    }
    @Test
    public void testButtonButton2Displayed() {
        onView(withId(R.id.button2)).check(matches(isDisplayed()));

    }
    public void testButtonButton4Displayed() {
        onView(withId(R.id.button4)).check(matches(isDisplayed()));

    }
    public void testButtonButtonFechaDisplayed() {
        onView(withId(R.id.buttonFecha)).check(matches(isDisplayed()));

    }
    public void testTextViewFechaDisplayed() {
        onView(withId(R.id.textoFecha)).check(matches(isDisplayed()));

    }
    public void testTextViewHoraDisplayed() {
        onView(withId(R.id.textoFecha)).check(matches(isDisplayed()));

    }
    public void testTextViewFechaViewDisplayed() {
        onView(withId(R.id.textoFecha)).check(matches(isDisplayed()));

    }
    // Visibility

    @Test
    public void testTextView2IsVisible() {
        onView(withId(R.id.textView2)).check(matches(withEffectiveVisibility(ViewMatchers.Visibility.VISIBLE)));

    }
    @Test
    public void testBottomNavigationIsVisible() {
        onView(withId(R.id.bottom_navigation)).check(matches(withEffectiveVisibility(ViewMatchers.Visibility.VISIBLE)));

    }
    @Test
    public void testEditTextNombreCitaIsVisible() {
        onView(withId(R.id.nombreCita)).check(matches(withEffectiveVisibility(ViewMatchers.Visibility.VISIBLE)));

    }
    @Test
    public void testButtonButton2IsVisible() {
        onView(withId(R.id.button2)).check(matches(withEffectiveVisibility(ViewMatchers.Visibility.VISIBLE)));

    }
    @Test
    public void testButtonButton4IsVisible() {
        onView(withId(R.id.button4)).check(matches(withEffectiveVisibility(ViewMatchers.Visibility.VISIBLE)));

    }
    public void testButtonButtonFechaIsVisible() {
        onView(withId(R.id.buttonFecha)).check(matches(withEffectiveVisibility(ViewMatchers.Visibility.VISIBLE)));
    }
    public void testTextViewFechaIsVisible() {
        onView(withId(R.id.textoFecha)).check(matches(withEffectiveVisibility(ViewMatchers.Visibility.VISIBLE)));
    }

    public void testTextViewHoraIsVisible() {
        onView(withId(R.id.horaCita)).check(matches(withEffectiveVisibility(ViewMatchers.Visibility.VISIBLE)));
    }
    public void testTextViewFechaViewIsVisible() {
        onView(withId(R.id.fechaView)).check(matches(withEffectiveVisibility(ViewMatchers.Visibility.VISIBLE)));
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

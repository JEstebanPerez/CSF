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
import com.pastillasCreator.pill_box.actividades.Informacion;

import org.junit.FixMethodOrder;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;

@RunWith(AndroidJUnit4.class)
@LargeTest
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class InformacionUiTest {
    @Rule
    public ActivityScenarioRule<Informacion> informacionActivityScenarioRule = new ActivityScenarioRule<>(
            Informacion.class);

    @Test
    public void testEditTextNombreDisplayed() {
        onView(withId(R.id.textoNombre)).check(matches(isDisplayed()));

    }
    @Test
    public void testEditTextDescripcionDisplayed() {
        onView(withId(R.id.textoDescripcion)).check(matches(isDisplayed()));

    }

    @Test
    public void testEditTextDisplayed() {
        onView(withId(R.id.textoTotal)).check(matches(isDisplayed()));

    }
    @Test
    public void testEditTextTipoDisplayed() {
        onView(withId(R.id.textoTipo)).check(matches(isDisplayed()));

    }
    @Test
    public void testEditTextHoraDisplayed() {
        onView(withId(R.id.textoHora)).check(matches(isDisplayed()));

    }
    @Test
    public void testEditTextFechaDisplayed() {
        onView(withId(R.id.textoFecha)).check(matches(isDisplayed()));

    }
    @Test
    public void testBottomNavigationDisplayed() {
        onView(withId(R.id.bottom_navigation)).check(matches(isDisplayed()));

    }
    @Test
    public void testBtnBorrarDisplayed() {
        onView(withId(R.id.botonBorrar)).check(matches(isDisplayed()));

    }
    // Visibility

    @Test
    public void testAyudaTextViewIsVisible() {
        onView(withId(R.id.textViewAyuda)).check(matches(withEffectiveVisibility(ViewMatchers.Visibility.VISIBLE)));

    }

    @Test
    public void testEditTextNombreIsVisible() {
        onView(withId(R.id.textoNombre)).check(matches(withEffectiveVisibility(ViewMatchers.Visibility.VISIBLE)));

    }
    @Test
    public void testEditTextDescripcionIsVisible() {
        onView(withId(R.id.textoDescripcion)).check(matches(withEffectiveVisibility(ViewMatchers.Visibility.VISIBLE)));

    }

    @Test
    public void testEditTextIsVisible() {
        onView(withId(R.id.textoTotal)).check(matches(withEffectiveVisibility(ViewMatchers.Visibility.VISIBLE)));

    }
    @Test
    public void testEditTextTipoIsVisible() {
        onView(withId(R.id.textoTipo)).check(matches(withEffectiveVisibility(ViewMatchers.Visibility.VISIBLE)));

    }
    @Test
    public void testEditTextHoraIsVisible() {
        onView(withId(R.id.textoHora)).check(matches(withEffectiveVisibility(ViewMatchers.Visibility.VISIBLE)));

    }
    @Test
    public void testEditTextFechaIsVisible() {
        onView(withId(R.id.textoFecha)).check(matches(withEffectiveVisibility(ViewMatchers.Visibility.VISIBLE)));

    }
    @Test
    public void testBottomNavigationIsVisible() {
        onView(withId(R.id.bottom_navigation)).check(matches(withEffectiveVisibility(ViewMatchers.Visibility.VISIBLE)));

    }
    @Test
    public void testBtnBorrarIsVisible() {
        onView(withId(R.id.botonBorrar)).check(matches(withEffectiveVisibility(ViewMatchers.Visibility.VISIBLE)));

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

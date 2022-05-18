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
import com.pastillasCreator.pill_box.actividades.EditarPastilla;

import org.junit.FixMethodOrder;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;

@RunWith(AndroidJUnit4.class)
@LargeTest
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class EditarPastillaUiTest {

    @Rule
    public ActivityScenarioRule<EditarPastilla> editarPastillaScenarioRule = new ActivityScenarioRule<>(
            EditarPastilla.class);
    // Display

    @Test
    public void testSpinnerDisplayed() {
        onView(withId(R.id.spinnerPastillaEdit)).check(matches(isDisplayed()));

    }

    @Test
    public void testTextViewListaSemanaPastillaEditDisplayed() {
        onView(withId(R.id.listaSemanaPastillaEdit)).check(matches(isDisplayed()));

    }
    @Test
    public void testNombrePastillaEditDisplayed() {
        onView(withId(R.id.nombrePastillaEdit)).check(matches(isDisplayed()));

    }
    @Test
    public void testBottomNavigationDisplayed() {
        onView(withId(R.id.bottom_navigation)).check(matches(isDisplayed()));

    }
    @Test
    public void testBtnGuardar2Displayed() {
        onView(withId(R.id.botonGuardar2)).check(matches(isDisplayed()));

    }
    @Test
    public void testBtnEliminarDisplayed() {
        onView(withId(R.id.botonEliminar)).check(matches(isDisplayed()));

    }
    @Test
    public void testDescripcionPastillaEditDisplayed() {
        onView(withId(R.id.DescripcionPastillaEdit)).check(matches(isDisplayed()));

    }
    @Test
    public void testHoraPastillaEditDisplayed() {
        onView(withId(R.id.HoraPastillaEdit)).check(matches(isDisplayed()));

    }
    @Test
    public void testBtnHoraPastillaDisplayed() {
        onView(withId(R.id.botonHoraPastilla)).check(matches(isDisplayed()));

    }
    @Test
    public void testEditTextTotalPastillaEditDisplayed() {
        onView(withId(R.id.TotalPastillaEdit)).check(matches(isDisplayed()));

    }
    @Test
    public void testTextViewTipoPastillaEditDisplayed() {
        onView(withId(R.id.tipoPastillaEdit)).check(matches(isDisplayed()));

    }
    // Visibility

//    @Test
//    public void testAyudaTextViewIsVisible() {
//        onView(withId(R.id.textViewAyuda)).check(matches(withEffectiveVisibility(ViewMatchers.Visibility.VISIBLE)));
//
//    }

    @Test
    public void testSpinnerIsVisible() {
        onView(withId(R.id.spinnerPastillaEdit)).check(matches(withEffectiveVisibility(ViewMatchers.Visibility.VISIBLE)));

    }

    @Test
    public void testTextViewListaSemanaPastillaEditIsVisible() {
        onView(withId(R.id.listaSemanaPastillaEdit)).check(matches(withEffectiveVisibility(ViewMatchers.Visibility.VISIBLE)));

    }
    @Test
    public void testNombrePastillaEditIsVisible() {
        onView(withId(R.id.nombrePastillaEdit)).check(matches(withEffectiveVisibility(ViewMatchers.Visibility.VISIBLE)));

    }
    @Test
    public void testBottomNavigationIsVisible() {
        onView(withId(R.id.bottom_navigation)).check(matches(withEffectiveVisibility(ViewMatchers.Visibility.VISIBLE)));

    }
    @Test
    public void testBtnGuardar2IsVisible() {
        onView(withId(R.id.botonGuardar2)).check(matches(withEffectiveVisibility(ViewMatchers.Visibility.VISIBLE)));

    }
    @Test
    public void testBtnEliminarIsVisible() {
        onView(withId(R.id.botonEliminar)).check(matches(withEffectiveVisibility(ViewMatchers.Visibility.VISIBLE)));

    }
    @Test
    public void testDescripcionPastillaEditIsVisible() {
        onView(withId(R.id.DescripcionPastillaEdit)).check(matches(withEffectiveVisibility(ViewMatchers.Visibility.VISIBLE)));

    }
    @Test
    public void testHoraPastillaEditIsVisible() {
        onView(withId(R.id.HoraPastillaEdit)).check(matches(withEffectiveVisibility(ViewMatchers.Visibility.VISIBLE)));

    }
    @Test
    public void testBtnHoraPastillaIsVisible() {
        onView(withId(R.id.botonHoraPastilla)).check(matches(withEffectiveVisibility(ViewMatchers.Visibility.VISIBLE)));

    }
    @Test
    public void testEditTextTotalPastillaEditIsVisible() {
        onView(withId(R.id.TotalPastillaEdit)).check(matches(withEffectiveVisibility(ViewMatchers.Visibility.VISIBLE)));

    }
    @Test
    public void testTextViewTipoPastillaEditIsVisible() {
        onView(withId(R.id.tipoPastillaEdit)).check(matches(withEffectiveVisibility(ViewMatchers.Visibility.VISIBLE)));

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



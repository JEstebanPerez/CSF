package com.pastillasCreator.pill_box.espressouitests.ActivityUI;

import static androidx.test.espresso.Espresso.onData;
import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.isCompletelyDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.withEffectiveVisibility;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withText;

import static org.hamcrest.Matchers.hasEntry;

import android.util.Log;

import androidx.test.espresso.matcher.ViewMatchers;
import androidx.test.ext.junit.rules.ActivityScenarioRule;
import androidx.test.ext.junit.runners.AndroidJUnit4;
import androidx.test.filters.LargeTest;

import com.example.pill_box.R;
import com.pastillasCreator.pill_box.actividades.ActividadCitero;
import com.pastillasCreator.pill_box.actividades.Ayuda;
import com.pastillasCreator.pill_box.almacenaje.Citero;
import com.pastillasCreator.pill_box.elementosCalendario.Cita;

import org.junit.Before;
import org.junit.FixMethodOrder;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;

import java.time.LocalDateTime;

@RunWith(AndroidJUnit4.class)
@LargeTest
@FixMethodOrder(MethodSorters.NAME_ASCENDING)

public class CiteroUiTest {
    @Rule
    public ActivityScenarioRule<ActividadCitero> ayudaActivityScenarioRule = new ActivityScenarioRule<>(
            ActividadCitero.class);

    // in View
    @Test
    public void testActivityInView() {
        onView(withId(R.id.listaCitas)).check(matches(isDisplayed()));

    }

    // Component Display
    @Test
    public void testListViewDisplayed() {
        onView(withId(R.id.listCita)).check(matches(isDisplayed()));

    }
    @Before
    public void anadirCitasEjemplo(){
        // Crear cita
        Cita cita1 = new Cita(
                "Nombre1",
                "Descripcion de cita 1",
                LocalDateTime.now()
        );
        //

        // Crear cita
        Cita cita2 = new Cita(
                "Nombre2",
                "Descripcion de cita 2",
                LocalDateTime.now()
        );

        // Aniadir las citas
        Citero citero = Citero.getCitero();
        citero.addCita(cita1);
        citero.addCita(cita2);
//        String str1 = cita1.getNombre() + " " + cita1.getDate();
//        Log.i("INFO", str1);
//        Log.i("INFO", str1);
//        Log.i("INFO", str1);
//        Log.i("INFO", str1);
    }
    @Test
    public void testListItemDisplayed(){

        onView(withId(R.id.bottom_navigation)).check(matches(isDisplayed()));
    }
    @Test
    public void testBottomNavigationDisplayed() {
        onView(withId(R.id.bottom_navigation)).check(matches(isDisplayed()));

    }

    @Test
    public void testButtonAnadirCitaDisplayed() {
        onView(withId(R.id.botonAñadirCita)).check(matches(isDisplayed()));

    }
    // Visibility

    @Test
    public void testListViewIsVisible() {
        onView(withId(R.id.listCita)).check(matches(withEffectiveVisibility(ViewMatchers.Visibility.VISIBLE)));

    }
    @Test
    public void testBottomNavigationIsVisible() {
        onView(withId(R.id.bottom_navigation)).check(matches(withEffectiveVisibility(ViewMatchers.Visibility.VISIBLE)));

    }
    @Test
    public void testButtonAnadirCitaIsVisible() {
        onView(withId(R.id.botonAñadirCita)).check(matches(withEffectiveVisibility(ViewMatchers.Visibility.VISIBLE)));

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

    // Navigation

    @Test
    public void testNavCitaCreator(){
        onView(withId(R.id.botonAñadirCita)).perform(click());
        onView(withId(R.id.crear_cita)).check(matches(isDisplayed()));
    }

//    @Test
//    public void testNavEditarCitas(){
//        onView(withId(R.id.listCita)).perform(click());
//        onView(withId(R.id.editar_cita)).check(matches(isDisplayed()));
//    }
}


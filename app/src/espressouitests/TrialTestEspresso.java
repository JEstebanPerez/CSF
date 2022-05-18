package com.pastillasCreator.pill_box.espressouitests;


import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.withId;

import androidx.test.core.app.ActivityScenario;
import androidx.test.ext.junit.rules.ActivityScenarioRule;
import androidx.test.ext.junit.runners.AndroidJUnit4;
import androidx.test.filters.LargeTest;

import com.example.pill_box.R;
import com.pastillasCreator.pill_box.actividades.ActividadMain;

import org.junit.After;
import org.junit.Before;
import org.junit.FixMethodOrder;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;

@RunWith(AndroidJUnit4.class)
@LargeTest
@FixMethodOrder(MethodSorters.NAME_ASCENDING)

public class TrialTestEspresso {


    // Specifies that the execution should start from main activity
    @Rule
    public ActivityScenarioRule<ActividadMain> mActivityRule = new ActivityScenarioRule<>(
            ActividadMain.class);


    @Before

    public void setUp() throws Exception {
        //Before Test case execution

    }

    @Test

    public void test1ChatId() {
        ActivityScenario scenario =  mActivityRule.getScenario();
        onView(withId(R.id.tv_title)).check(matches(isDisplayed()));
    }

    @After

    public void tearDown() throws Exception {
        //After Test case Execution
    }
}





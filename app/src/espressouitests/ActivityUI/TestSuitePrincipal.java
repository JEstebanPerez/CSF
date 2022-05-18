package com.pastillasCreator.pill_box.espressouitests.ActivityUI;

import com.pastillasCreator.pill_box.espressouitests.ActivityUI.AyudaUiTest;
import com.pastillasCreator.pill_box.espressouitests.ActivityUI.CalendarioUiTest;
import com.pastillasCreator.pill_box.espressouitests.ActivityUI.CiteroUiTest;
import com.pastillasCreator.pill_box.espressouitests.ActivityUI.MainUiTest;
import com.pastillasCreator.pill_box.espressouitests.ActivityUI.PastilleroUiTest;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;

@RunWith(Suite.class)
@Suite.SuiteClasses(
        {AyudaUiTest.class,
        CalendarioUiTest.class,
        CiteroUiTest.class,
        MainUiTest.class,
        PastilleroUiTest.class}

)
public class TestSuitePrincipal {
    public TestSuitePrincipal() {






    }
}

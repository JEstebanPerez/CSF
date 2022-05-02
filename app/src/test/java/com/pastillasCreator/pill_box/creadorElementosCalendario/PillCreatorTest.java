package com.pastillasCreator.pill_box.creadorElementosCalendario;



import static org.junit.jupiter.api.Assertions.assertThrows;

import com.pastillasCreator.pill_box.DATA_FIXTURE.PillDataFixture;
import com.pastillasCreator.pill_box.elementosCalendario.Pill;
import com.pastillasCreator.pill_box.exceptions.CreatorException;
import com.pastillasCreator.pill_box.herramientas.DateManipulator;
import com.pastillasCreator.pill_box.herramientas.ObjectComparator;

import junit.framework.TestCase;

import java.time.LocalDateTime;

public class PillCreatorTest extends TestCase {

    public void setUp() throws Exception {
        super.setUp();
    }

    public void testCreateElement() {
        LocalDateTime dateTime = DateManipulator.dateFromStringToLocalDateTime("01/01/2022","00:00");
        Pill pill = PillDataFixture.getRandomPill(dateTime);
        PillCreator pillCreator = new PillCreator(pill.getName(), pill.getDescription(),
                pill.getTotal(),pill.getHour(), pill.getDayOfWeekList(), pill.getType());
        pillCreator.checkFields();
        assertTrue(ObjectComparator.compareObjects(pill,pillCreator.createElement()
                ,Pill.class));
    }

    public void testCheckFields() {
        boolean[] selectionDays = {false,false,false,false,false,false};
        PillCreator pillCreator = new PillCreator("", "",0,"", selectionDays, null);
        assertThrows(CreatorException.class, pillCreator::checkFields);
        pillCreator = new PillCreator("q", "",0,"", selectionDays, null);
        assertThrows(CreatorException.class, pillCreator::checkFields);
        pillCreator = new PillCreator("q", "q",0,"", selectionDays, null);
        assertThrows(CreatorException.class, pillCreator::checkFields);
        pillCreator = new PillCreator("q", "q",1,"", selectionDays, null);
        assertThrows(CreatorException.class, pillCreator::checkFields);
        pillCreator = new PillCreator("q", "q",1,"q", selectionDays, null);
        assertThrows(CreatorException.class, pillCreator::checkFields);
    }
}
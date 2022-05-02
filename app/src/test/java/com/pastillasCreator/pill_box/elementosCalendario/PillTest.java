package com.pastillasCreator.pill_box.elementosCalendario;

import com.pastillasCreator.pill_box.DATA_FIXTURE.PillDataFixture;
import com.pastillasCreator.pill_box.herramientas.ObjectComparator;

import junit.framework.TestCase;

public class PillTest extends TestCase {
    private final int total = 10;
    private final PillType pillType = PillType.Capsula;
    private final boolean[] dayOfWeekList = {true,false,false,true,false,true};
    private final Pill pill = PillDataFixture.getRandomPill(total,pillType,dayOfWeekList);

    public void testGetTotal() {
        assertEquals(total,pill.getTotal());
    }

    public void testGetType() {
        assertEquals(pillType,pill.getType());
    }

    public void testGetDayOfWeekList() {
        assertEquals(dayOfWeekList,pill.getDayOfWeekList());
    }

    public void testChange() {
        Pill pill  = PillDataFixture.getRandomPill();

        this.pill.change(pill);

        assertTrue(ObjectComparator.compareObjects(pill,this.pill,Pill.class));

    }
}
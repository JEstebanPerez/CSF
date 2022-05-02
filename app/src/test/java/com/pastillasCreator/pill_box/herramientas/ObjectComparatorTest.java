package com.pastillasCreator.pill_box.herramientas;

import com.pastillasCreator.pill_box.DATA_FIXTURE.PillDataFixture;
import com.pastillasCreator.pill_box.elementosCalendario.Pill;
import com.pastillasCreator.pill_box.elementosCalendario.PillType;

import junit.framework.TestCase;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

public class ObjectComparatorTest extends TestCase {

    private final String defaultName = "hola";
    private final String defaultDescription = "hola";
    private final LocalDateTime defaultDateTime = LocalDateTime.of(LocalDate.of(2022,10,3), LocalTime.MIDNIGHT);
    private final int defaultTotal = 10;
    private final PillType defaultPillType = PillType.Efervescente;
    private final boolean[] defaultDayofWeekList = {true,false,false,true,true,false,true};

    public void testCompareObjects() {
        Pill pill;
        do{
            pill = PillDataFixture.getRandomPill();
        }while(isEqualSomeone(pill));
        Pill aux2 = new Pill(pill.getName(),pill.getDescription(),pill.getDate(),pill.getTotal(),pill.getType(),pill.getDayOfWeekList());
        Pill aux3 = new Pill(defaultName,pill.getDescription(),pill.getDate(),pill.getTotal(),pill.getType(),pill.getDayOfWeekList());
        Pill aux4 = new Pill(pill.getName(),defaultDescription,pill.getDate(),pill.getTotal(),pill.getType(),pill.getDayOfWeekList());
        Pill aux5 = new Pill(pill.getName(),pill.getDescription(),defaultDateTime,pill.getTotal(),pill.getType(),pill.getDayOfWeekList());
        Pill aux6 = new Pill(pill.getName(),pill.getDescription(),pill.getDate(),defaultTotal,pill.getType(),pill.getDayOfWeekList());
        Pill aux7 = new Pill(pill.getName(),pill.getDescription(),pill.getDate(),pill.getTotal(), defaultPillType,pill.getDayOfWeekList());
        Pill aux8 = new Pill(pill.getName(),pill.getDescription(),pill.getDate(),pill.getTotal(),pill.getType(),defaultDayofWeekList);
        assertTrue(ObjectComparator.compareObjects(pill,aux2,Pill.class));
        assertFalse(ObjectComparator.compareObjects(pill,aux3,Pill.class));
        assertFalse(ObjectComparator.compareObjects(pill,aux4,Pill.class));
        assertFalse(ObjectComparator.compareObjects(pill,aux5,Pill.class));
        assertFalse(ObjectComparator.compareObjects(pill,aux6,Pill.class));
        assertFalse(ObjectComparator.compareObjects(pill,aux7,Pill.class));
        assertFalse(ObjectComparator.compareObjects(pill,aux8,Pill.class));
    }

    public boolean isEqualSomeone(Pill pill){
        if(pill.getName().equals(defaultName)) return true;
        if(pill.getDescription().equals(defaultDescription)) return true;
        if(pill.getDate() == defaultDateTime) return true;
        if(pill.getTotal() == defaultTotal) return true;
        if(pill.getType() == defaultPillType) return true;
        return pill.getDayOfWeekList() == defaultDayofWeekList;
    }
}
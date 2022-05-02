package com.pastillasCreator.pill_box.elementosCalendario;

import com.pastillasCreator.pill_box.DATA_FIXTURE.CalendarElementDataFixture;

import junit.framework.TestCase;

import org.junit.jupiter.api.BeforeEach;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

public class CalendarElementTest extends TestCase {

    private String name = "hola";
    private String description = "holaa";
    private LocalDateTime date = LocalDateTime.of(LocalDate.of(2022,4,22), LocalTime.MIDNIGHT);
    private CalendarElement calendarElement;

    @BeforeEach
    public void setUp(){
        calendarElement = new Appointment(name,description,date);
    }


    public void testGetDayFormatted() {
        assertEquals(CalendarElement.getDayFormatted(10,2,2022),"10/02/2022");
    }

    public void testGetMinuteFormat() {
        assertEquals(CalendarElement.getMinuteFormat(10,2),"10:02");
    }

    public void testGetName() {
        assertEquals(name,calendarElement.getName());
    }

    public void testGetDescription() {
        assertEquals(description,calendarElement.getDescription());
    }

    public void testSetName() {
        calendarElement.setName("hola");
        assertEquals("hola",calendarElement.getName());

    }

    public void testSetDescription() {
        calendarElement.setDescription("hola");
        assertEquals("hola",calendarElement.getDescription());

    }

    public void testEquals() {
        assertEquals(calendarElement, calendarElement);
    }

    public void testGetHour() {
        assertEquals(calendarElement.getHour(),"00:00");
    }

    public void testGetStringShowingDate() {
        assertEquals(calendarElement.getStringShowingDate(),"22/04/2022");
    }

    public void testChange() {
        CalendarElement aux = CalendarElementDataFixture.getRandomCalendarElement();
        calendarElement.change(aux);
        assertEquals(aux,calendarElement);
    }
}
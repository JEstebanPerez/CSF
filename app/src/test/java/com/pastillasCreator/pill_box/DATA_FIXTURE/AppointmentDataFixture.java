package com.pastillasCreator.pill_box.DATA_FIXTURE;

import com.pastillasCreator.pill_box.elementosCalendario.Appointment;
import com.pastillasCreator.pill_box.elementosCalendario.CalendarElement;

public class AppointmentDataFixture {

    public static Appointment getRandomAppointment(){
        CalendarElement calendarElement = CalendarElementDataFixture.getRandomCalendarElement();
        return new Appointment(calendarElement.getName(),calendarElement.getDescription(),calendarElement.getDate());
    }

    public static Appointment getRandomAppointment(String name){
        CalendarElement calendarElement = CalendarElementDataFixture.getRandomCalendarElement(name);
        return new Appointment(calendarElement.getName(),calendarElement.getDescription(),calendarElement.getDate());
    }

    public static Appointment getRandomAppointment(String name,String description){
        CalendarElement calendarElement = CalendarElementDataFixture.getRandomCalendarElement(name,description);
        return new Appointment(calendarElement.getName(),calendarElement.getDescription(),calendarElement.getDate());
    }
}

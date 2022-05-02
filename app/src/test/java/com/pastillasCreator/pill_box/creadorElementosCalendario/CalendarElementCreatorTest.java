package com.pastillasCreator.pill_box.creadorElementosCalendario;

import static org.junit.jupiter.api.Assertions.assertThrows;

import com.pastillasCreator.pill_box.DATA_FIXTURE.AppointmentDataFixture;
import com.pastillasCreator.pill_box.elementosCalendario.Appointment;
import com.pastillasCreator.pill_box.exceptions.CreatorException;
import com.pastillasCreator.pill_box.herramientas.ObjectComparator;

import junit.framework.TestCase;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

public class CalendarElementCreatorTest extends TestCase {

    public void setUp() throws Exception {
        super.setUp();
    }

    public void testCheckFields() {
        CalendarElementCreator<Appointment> creator = new AppointmentCreator("","",null);
        assertThrows(CreatorException.class, creator::checkFields);
        creator = new AppointmentCreator("q","",null);
        assertThrows(CreatorException.class, creator::checkFields);
    }

    public void testEditElement() {
        Appointment appointment = AppointmentDataFixture.getRandomAppointment();
        CalendarElementCreator<Appointment> creator = new AppointmentCreator("hola",
                "buenas", LocalDateTime.of(LocalDate.of(2020,1,2), LocalTime.MIDNIGHT));
        creator.editElement(appointment);
        Appointment aux = creator.createElement();
        assertTrue(ObjectComparator.compareObjects(appointment,aux,Appointment.class));
    }
}
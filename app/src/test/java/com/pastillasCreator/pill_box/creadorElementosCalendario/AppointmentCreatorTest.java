package com.pastillasCreator.pill_box.creadorElementosCalendario;

import com.pastillasCreator.pill_box.DATA_FIXTURE.AppointmentDataFixture;
import com.pastillasCreator.pill_box.elementosCalendario.Appointment;
import com.pastillasCreator.pill_box.herramientas.ObjectComparator;

import junit.framework.TestCase;

public class AppointmentCreatorTest extends TestCase {

    public void testCreateElement() {
        Appointment appointment = AppointmentDataFixture.getRandomAppointment();
        AppointmentCreator appointmentCreator = new AppointmentCreator(appointment.getName(),
                appointment.getDescription(),appointment.getDate());
        assertTrue(ObjectComparator.compareObjects(appointment,appointmentCreator.createElement()
                ,Appointment.class));
    }
}
package com.pastillasCreator.pill_box.almacenaje;

import com.pastillasCreator.pill_box.elementosCalendario.Appointment;

public class AppointmentAccumulator extends Accumulator<Appointment> {
    private static AppointmentAccumulator appointmentAccumulator;

    private AppointmentAccumulator() { }

    public static AppointmentAccumulator getAppointmentAccumulator(){
        if (appointmentAccumulator == null) {
            appointmentAccumulator = new AppointmentAccumulator();
        }
        return appointmentAccumulator;
    }
}
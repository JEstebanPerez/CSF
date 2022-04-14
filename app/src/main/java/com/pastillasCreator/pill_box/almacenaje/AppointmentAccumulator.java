package com.pastillasCreator.pill_box.almacenaje;

import com.pastillasCreator.pill_box.elementosCalendario.Appointment;

public class AppointmentAccumulator extends Accumulator<Appointment> {

    private static AppointmentAccumulator instance = null;

    private AppointmentAccumulator() { }

    public static AppointmentAccumulator getAccumulator(){
        if (instance == null) {
            instance = new AppointmentAccumulator();
        }
        return instance;
    }
}
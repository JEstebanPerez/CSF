package com.pastillasCreator.pill_box.creadorElementosCalendario;

import android.os.Build;

import androidx.annotation.RequiresApi;

import com.pastillasCreator.pill_box.almacenaje.AppointmentAccumulator;
import com.pastillasCreator.pill_box.elementosCalendario.CalendarElement;
import com.pastillasCreator.pill_box.elementosCalendario.Appointment;
import com.pastillasCreator.pill_box.exceptions.CreatorException;
import com.pastillasCreator.pill_box.herramientas.DateManipulator;

import java.time.LocalDateTime;

public class AppointmentCreator extends CalendarElementCreator<Appointment> {

    public AppointmentCreator(String name, String description, LocalDateTime localDateTime) {
        super(name,description);
        this.localDateTime = localDateTime;
        this.accumulator = AppointmentAccumulator.getAccumulator();
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    public Appointment createElement() {
        return new Appointment(name, description, localDateTime);
    }
}

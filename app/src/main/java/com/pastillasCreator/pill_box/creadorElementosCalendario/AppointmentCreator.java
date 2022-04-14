package com.pastillasCreator.pill_box.creadorElementosCalendario;

import android.os.Build;

import androidx.annotation.RequiresApi;

import com.pastillasCreator.pill_box.almacenaje.AppointmentAccumulator;
import com.pastillasCreator.pill_box.elementosCalendario.CalendarElement;
import com.pastillasCreator.pill_box.elementosCalendario.Appointment;
import com.pastillasCreator.pill_box.exceptions.CreatorException;
import com.pastillasCreator.pill_box.herramientas.DateManipulator;

import java.time.LocalDateTime;

public class AppointmentCreator implements CalendarElementCreator {
    private final String name;
    private final String description;
    private final String hour;
    private final String date;

    public AppointmentCreator(String nombre, String descripcion, String hora, String fecha) {
        this.name = nombre;
        this.description = descripcion;
        this.hour = hora;
        this.date = fecha;
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    public CalendarElement createElement() {
        LocalDateTime formattedDate = DateManipulator.dateFromStringToLocalDateTime(date, hour);
        return new Appointment(name, description, formattedDate);
    }

    @RequiresApi(api = Build.VERSION_CODES.GINGERBREAD)
    private void checkFields() {
        if (name.isEmpty()) {
            throw new CreatorException(CreatorException.EMPTY_VALUE,"name");
        }
        if (description.isEmpty()) {
            throw new CreatorException(CreatorException.EMPTY_VALUE,"description");
        }
        if (hour == null || date == null) {
            throw new CreatorException(CreatorException.EMPTY_VALUE,"date");
        }
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    public void saveAppointment() {
        checkFields();

        Appointment appointment = (Appointment) createElement();
        AppointmentAccumulator appointmentAccumulator = AppointmentAccumulator.getAppointmentAccumulator();

        appointmentAccumulator.saveElement(appointment);
    }
}

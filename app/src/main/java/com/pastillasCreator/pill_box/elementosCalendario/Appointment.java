package com.pastillasCreator.pill_box.elementosCalendario;

import java.time.LocalDateTime;

public class Appointment extends CalendarElement {

    public Appointment(String name, String description, LocalDateTime date) {
        super(name, description,date);
    }

}

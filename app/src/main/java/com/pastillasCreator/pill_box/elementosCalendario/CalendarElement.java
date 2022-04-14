package com.pastillasCreator.pill_box.elementosCalendario;

import com.pastillasCreator.pill_box.herramientas.ObjectComparator;

public class CalendarElement {
    protected String name;
    protected String description;

    public CalendarElement(String name, String description) {
        this.name = name;
        this.description = description;
    }

    public String getName() {
        return name;
    }
    public String getDescription() {
        return description;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public boolean equals(Object object) {
        try {
            return ObjectComparator.compareObjects(this, object, Appointment.class);
        } catch (IllegalAccessException e) {
            e.printStackTrace();
            return false;
        }
    }
}

package com.pastillasCreator.pill_box.creadorElementosCalendario;


import android.os.Build;

import androidx.annotation.RequiresApi;

import com.pastillasCreator.pill_box.almacenaje.Accumulator;
import com.pastillasCreator.pill_box.elementosCalendario.Appointment;
import com.pastillasCreator.pill_box.elementosCalendario.CalendarElement;
import com.pastillasCreator.pill_box.exceptions.CreatorException;

import java.time.LocalDateTime;

public abstract class CalendarElementCreator<T extends CalendarElement> {
    protected final String name;
    protected final String description;
    protected LocalDateTime localDateTime;
    protected Accumulator<T> accumulator;

    public CalendarElementCreator(String name, String description) {
        this.name = name;
        this.description = description;
    }

    @RequiresApi(api = Build.VERSION_CODES.GINGERBREAD)
    protected void checkFields() {
        if (name.isEmpty()) {
            throw new CreatorException(CreatorException.EMPTY_VALUE,"name");
        }
        if (description.isEmpty()) {
            throw new CreatorException(CreatorException.EMPTY_VALUE,"description");
        }
    }

    public abstract T createElement();

    public void editElement(T element){
        checkFields();

        T newElement = createElement();

        element.change(newElement);
    }

    public void saveElement(){
        checkFields();
        accumulator.saveElement(createElement());
    }

}

package com.pastillasCreator.pill_box.creadorElementosCalendario;


import android.os.Build;

import androidx.annotation.RequiresApi;

import com.pastillasCreator.pill_box.almacenaje.PillAccumulator;
import com.pastillasCreator.pill_box.elementosCalendario.Appointment;
import com.pastillasCreator.pill_box.elementosCalendario.Pill;
import com.pastillasCreator.pill_box.elementosCalendario.PillType;
import com.pastillasCreator.pill_box.exceptions.CreatorException;
import com.pastillasCreator.pill_box.herramientas.DateManipulator;

import java.time.LocalDateTime;
import java.util.Arrays;

public class PillCreator extends CalendarElementCreator<Pill> {

    private final int total;
    private final PillType type;
    private final String hour;
    private final boolean[] selectedDaysArray;
    private LocalDateTime date;

    public PillCreator(String name, String description, int total,String hour,
                       boolean[] selectedDaysArray, PillType type) {
        super(name,description);
        accumulator = PillAccumulator.getPillAccumulator();
        this.total = total;
        this.selectedDaysArray = selectedDaysArray;
        this.type = type;
        this.hour = hour;
    }

    @Override
    public Pill createElement() {
        return new Pill(name, description,date, total, type,selectedDaysArray);
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    protected void checkFields() {
        if (name.isEmpty()) {
            throw new CreatorException(CreatorException.EMPTY_VALUE, "name");
        }

        if (!isValidPillNumber()) {
            throw new CreatorException(CreatorException.NOT_VALID_VALUE, pillMessage());
        }

        if (hour.isEmpty()) {
            throw new CreatorException(CreatorException.EMPTY_VALUE, "hour");
        }

        if (description.isEmpty()) {
            throw new CreatorException(CreatorException.EMPTY_VALUE, "description");
        }

        if(!anySelectedDay()){
            throw new CreatorException(CreatorException.EMPTY_VALUE, "days");
        }
        date = DateManipulator.dateFromStringToLocalDateTime("01/01/2022",hour);
    }

    private boolean anySelectedDay(){
        for (boolean day : selectedDaysArray) {
            if (day) {
                return true;
            }
        }
        return false;
    }

    private boolean isValidPillNumber() {
        return (total >= 1) && (total <= 1000);
    }

    private String pillMessage() {
        return total<1 ? "less than the minimal" : "more than the maximal";
    }

}
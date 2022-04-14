package com.pastillasCreator.pill_box.creadorElementosCalendario;


import android.app.TimePickerDialog;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.view.View;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import androidx.annotation.RequiresApi;

import com.pastillasCreator.pill_box.actividades.PillAccumulatorActivity;
import com.pastillasCreator.pill_box.actividades.PillEditorActivity;
import com.pastillasCreator.pill_box.almacenaje.PillAccumulator;
import com.pastillasCreator.pill_box.elementosCalendario.CalendarElement;
import com.pastillasCreator.pill_box.elementosCalendario.Pill;
import com.pastillasCreator.pill_box.elementosCalendario.PillType;
import com.pastillasCreator.pill_box.exceptions.CreatorException;
import com.pastillasCreator.pill_box.herramientas.DateManipulator;

import java.util.Calendar;

public class PillCreator implements CalendarElementCreator {

    private String name;
    private final String description;
    private final int total;
    private PillType type;
    private String hour;
    private TextView hourTextView = null;
    private final boolean[] selectedDaysArray;

    public PillCreator(String name, String description, int total, boolean[] selectedDaysArray, PillType pillType) {
        this.name = name;
        this.description = description;
        this.total = total;
        this.selectedDaysArray = selectedDaysArray;
        type = pillType;
    }

    @Override
    public Pill createElement() {
        return new Pill(name, description, total, type);
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setType(PillType type) {
        this.type = type;
    }

    public String getName() {
        return name;
    }

    public PillType getType() {
        return type;
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    public void saveIfPossible() {
        if (name.isEmpty()) {
            throw new CreatorException(CreatorException.EMPTY_VALUE, "name");
        }

        if (!isValidPillNumber()) {
            throw new CreatorException(CreatorException.NOT_VALID_VALUE, pillMessage());
        }

        if (description.isEmpty()) {
            throw new CreatorException(CreatorException.EMPTY_VALUE, "description");
        }

        Pill pill = createElement();

        PillAccumulator pillAccumulator = PillAccumulator.getPillAccumulator();
        int numOpcionales = countOptionalData();

        if (numOpcionales == 1) {
            throw new CreatorException(CreatorException.EMPTY_VALUE, "optionals");
        }

        pillAccumulator.saveElement(pill);
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    private int countOptionalData() {
        int count = 0;
        for (boolean day : selectedDaysArray) {
            if (day) {
                count = 1;
                break;
            }
        }
        return count + (hour != null ? 1 : 0);
    }

    private boolean isValidPillNumber() {
        return (total >= 1) && (total <= 1000);
    }

    private String pillMessage() {
        if (total < 1) {
            return "less than the minimal";
        }
        return "more than the maximal";
    }

    public void openHour(View view, Context context, TextView hourTextView) {
        Calendar calendar = Calendar.getInstance();
        int hour = calendar.get(Calendar.HOUR_OF_DAY);
        int minute = calendar.get(Calendar.MINUTE);
        this.hourTextView = hourTextView;
        new TimePickerDialog(context, this::getHourByTimePicker, hour,
                minute, false).show();
    }

    private void getHourByTimePicker(TimePicker timePicker, int hour, int minute) {
        String minute_value = DateManipulator.inTimeToStringValue(minute);
        String hour_value = DateManipulator.inTimeToStringValue(hour);
        this.hour = "Hora:" + hour_value + ":" + minute_value;
        hourTextView.setText(this.hour);
    }

}
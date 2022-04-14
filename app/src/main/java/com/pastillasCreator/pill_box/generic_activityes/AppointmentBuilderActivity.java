package com.pastillasCreator.pill_box.generic_activityes;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import androidx.annotation.RequiresApi;

import com.example.pill_box.R;
import com.pastillasCreator.pill_box.actividades.AppointmentAccumulatorActivity;
import com.pastillasCreator.pill_box.actividades.AppointmentCreatorActivity;
import com.pastillasCreator.pill_box.almacenaje.AppointmentAccumulator;
import com.pastillasCreator.pill_box.creadorElementosCalendario.AppointmentCreator;
import com.pastillasCreator.pill_box.creadorElementosCalendario.PillCreator;
import com.pastillasCreator.pill_box.elementosCalendario.Appointment;
import com.pastillasCreator.pill_box.elementosCalendario.CalendarElement;
import com.pastillasCreator.pill_box.exceptions.CreatorException;
import com.pastillasCreator.pill_box.herramientas.DateManipulator;

import java.time.LocalDateTime;
import java.util.Calendar;

public abstract class AppointmentBuilderActivity extends CreatorActivity<Appointment> {

    protected TextView activityDateText;

    protected Integer activityDateTextId;

    @RequiresApi(api = Build.VERSION_CODES.R)
    @Override
    public void onCreate(Bundle savedInstanceState) {
        menu = AppointmentAccumulatorActivity.class;
        accumulator = AppointmentAccumulator.getAccumulator();
        super.onCreate(savedInstanceState);

        activityDateText = findViewById(activityDateTextId);
        activityDateText.setText(date);
    }

    public void showDate(View view) {

        Calendar calendar = Calendar.getInstance();
        int year = calendar.get(Calendar.YEAR);
        int month = calendar.get(Calendar.MONTH);
        int day = calendar.get(Calendar.DAY_OF_MONTH);

        new DatePickerDialog(context, this::getDateByDatePicker, year, month, day).show();
    }

    private void getDateByDatePicker(DatePicker datePickerDialog, int year, int month, int day){
        date = CalendarElement.getDayFormatted(day,month,year);
        activityDateText.setText(date);
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    public void saveNewItem(View view) {
        super.readFromViews();
        LocalDateTime localDateTime = DateManipulator.dateFromStringToLocalDateTime(date,hour);

        elementCreator = new AppointmentCreator(name, description, localDateTime);

        super.saveNewItem(view);
    }





}

package com.pastillasCreator.pill_box.generic_activityes;

import android.app.TimePickerDialog;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import androidx.annotation.RequiresApi;

import com.pastillasCreator.pill_box.actividades.AppointmentAccumulatorActivity;
import com.pastillasCreator.pill_box.creadorElementosCalendario.AppointmentCreator;
import com.pastillasCreator.pill_box.elementosCalendario.CalendarElement;
import com.pastillasCreator.pill_box.herramientas.DateManipulator;

import java.time.LocalDateTime;
import java.util.Calendar;

public abstract class BuilderActivity<T extends CalendarElement> extends DefaultActivity{

    protected EditText activityNameEditText;
    protected EditText activityDescriptionEditText;
    protected TextView hourTextView;

    protected Integer activityNameEditTextId;
    protected Integer activityHourTextId;
    protected Integer activityDescriptionEditTextId;

    protected String date = "";

    protected String name;
    protected String description;
    protected String hour;

    @RequiresApi(api = Build.VERSION_CODES.R)
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        activityNameEditText = findViewById(activityNameEditTextId);
        activityDescriptionEditText = findViewById(activityDescriptionEditTextId);
        hourTextView = findViewById(activityHourTextId);
    }

    public void showHour(View view) {
        Calendar calendar = Calendar.getInstance();
        int hour = calendar.get(Calendar.HOUR_OF_DAY);
        int minute = calendar.get(Calendar.MINUTE);
        new TimePickerDialog(context, this::getHourByTimePicker,hour, minute, false)
                .show();
    }

    private void getHourByTimePicker(TimePicker timePicker, int hour, int minute){
        this.hour = CalendarElement.getMinuteFormat(hour,minute);
        hourTextView.setText(this.hour);
    }


    protected void readFromViews() {
        name =activityNameEditText.getText().toString();
        description =activityDescriptionEditText.getText().toString();
        hour = hourTextView.getText().toString();
    }




}

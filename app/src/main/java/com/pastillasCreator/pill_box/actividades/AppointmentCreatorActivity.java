package com.pastillasCreator.pill_box.actividades;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
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
import com.pastillasCreator.pill_box.creadorElementosCalendario.AppointmentCreator;
import com.pastillasCreator.pill_box.herramientas.DateManipulator;

import java.util.Calendar;

public class AppointmentCreatorActivity extends DefaultActivity {

    private EditText activityName;
    private EditText activityDescription;
    private TextView activityTimeText;
    private TextView activityDateText;

    private String name;
    private String description;
    private String hour;
    private String date;

    @RequiresApi(api = Build.VERSION_CODES.R)
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.selectedItem = R.id.citas;
        super.selectedContentView = R.layout.activity_citas_creator;
        super.onCreate(savedInstanceState);

        activityName = findViewById(R.id.nombreCita);
        activityDescription = findViewById(R.id.descripcionCita);
        activityTimeText = findViewById(R.id.horaCita);
        activityDateText = findViewById(R.id.fechaView);

    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    public void saveNewAppointment(View view) {
        name = activityName.getText().toString();
        description = activityDescription.getText().toString();

        AppointmentCreator appointmentCreator = new AppointmentCreator(name, description, hour, date);
        try{
            appointmentCreator.saveAppointment();
            startActivity(new Intent(getApplicationContext(), AppointmentAccumulatorActivity.class));
        } catch(Exception e){
            Toast.makeText(this, e.getMessage(), Toast.LENGTH_SHORT).show();
        }
    }

    public void showHour(View view) {
        Calendar calendar = Calendar.getInstance();
        int hour = calendar.get(Calendar.HOUR_OF_DAY);
        int minute = calendar.get(Calendar.MINUTE);

         new TimePickerDialog(AppointmentCreatorActivity.this, this::getHourByTimePicker,hour,
                 minute, false).show();
    }

    public void showDate(View view) {

        Calendar calendar = Calendar.getInstance();
        int year = calendar.get(Calendar.YEAR);
        int month = calendar.get(Calendar.MONTH);
        int day = calendar.get(Calendar.DAY_OF_MONTH);

        new DatePickerDialog(AppointmentCreatorActivity.this, this::getDateByDatePicker, year,
                month, day).show();
    }

    private void getDateByDatePicker(DatePicker datePickerDialog,int year, int month, int day){
        String day_value = DateManipulator.inTimeToStringValue(day);
        String month_value = DateManipulator.inTimeToStringValue(month);
        String date = day_value + "/" + month_value + "/" + year;
        this.date =year + "-" + month_value + "-" + day_value;
        activityDateText.setText(date);
    }

    private void getHourByTimePicker(TimePicker timePicker, int hour, int minute){
        String minute_value = DateManipulator.inTimeToStringValue(minute);
        String hour_value = DateManipulator.inTimeToStringValue(hour);
        String completeTime = hour_value + ":" + minute_value;
        activityTimeText.setText(completeTime);
        this.hour =completeTime;
    }
}


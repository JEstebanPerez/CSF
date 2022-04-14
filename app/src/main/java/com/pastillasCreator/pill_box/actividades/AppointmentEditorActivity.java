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
import com.pastillasCreator.pill_box.almacenaje.AppointmentAccumulator;
import com.pastillasCreator.pill_box.elementosCalendario.Appointment;
import com.pastillasCreator.pill_box.exceptions.CreatorException;
import com.pastillasCreator.pill_box.herramientas.DateManipulator;

import java.time.LocalDateTime;
import java.util.Calendar;
import java.util.List;

public class AppointmentEditorActivity extends DefaultActivity {

    private EditText nameEditText;
    private EditText descriptionEditText;
    private TextView hourTextView;
    private TextView dateTextView;

    private String date;
    private String hour;

    private Appointment appointment;
    private int position;


    @RequiresApi(api = Build.VERSION_CODES.R)
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.selectedItem = R.id.citas;
        super.selectedContentView = R.layout.activity_editar_citas;
        super.onCreate(savedInstanceState);

        nameEditText = findViewById(R.id.nombrecitaEdit);
        descriptionEditText = findViewById(R.id.DescripCitaEdit);
        hourTextView = findViewById(R.id.horaCitaEdit);
        dateTextView = findViewById(R.id.fechaCitaEdit);

        Intent incomingIntent = getIntent();
        position = Integer.parseInt(incomingIntent.getStringExtra("posicion"));

        AppointmentAccumulator appointmentAccumulator = AppointmentAccumulator.getAppointmentAccumulator();
        appointment = appointmentAccumulator.getElement(position);

        hour = appointment.getHour();
        this.date = appointment.getStringStoringDate();

        nameEditText.setText(appointment.getName());
        descriptionEditText.setText(appointment.getDescription());
        hourTextView.setText(hour);
        String date = appointment.getStringShowingDate();
        dateTextView.setText(date);


    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    public void saveNewAppointment(View view) {
        String appointmentName = nameEditText.getText().toString();
        String appointmentDescription = descriptionEditText.getText().toString();
        if (appointmentName.isEmpty()) {
            throw new CreatorException(CreatorException.EMPTY_VALUE,"name");
        }
        if (appointmentDescription.isEmpty()) {
            throw new CreatorException(CreatorException.EMPTY_VALUE,"description");
        }
        appointment.setName(appointmentName);
        appointment.setDescription(appointmentDescription);
        Toast.makeText(this, "Se modificó la cita: " + appointmentName + " correctamente.", Toast.LENGTH_SHORT).show();
        startActivity(new Intent(getApplicationContext(), AppointmentAccumulatorActivity.class));
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    public void showHour(View view) {
        Calendar calendar = Calendar.getInstance();
        int hour = calendar.get(Calendar.HOUR_OF_DAY);
        int minute = calendar.get(Calendar.MINUTE);

        TimePickerDialog tmd = new TimePickerDialog(AppointmentEditorActivity.this,
                this::modifyAppointmentDate, hour, minute, false);
        tmd.show();
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    private void modifyAppointmentDate(TimePicker view, int hourOfDay, int minute){
        String minute_value = DateManipulator.inTimeToStringValue(minute);
        String hour_value = DateManipulator.inTimeToStringValue(hourOfDay);
        hour_value = minute_value + ":" + hour_value;
        hourTextView.setText(hour_value);
        changeAppointmentDate(hour_value, date);
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    public void openCalendar(View view) {
        Calendar cal = Calendar.getInstance();
        int year = cal.get(Calendar.YEAR);
        int month = cal.get(Calendar.MONTH);
        int day = cal.get(Calendar.DAY_OF_MONTH);

        DatePickerDialog dpd = new DatePickerDialog(AppointmentEditorActivity.this,
                this::modifyAppointmentDate, year, month, day);

        dpd.show();
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    private void modifyAppointmentDate(DatePicker datePicker, int year, int month, int dayOfMonth){
        String day = DateManipulator.inTimeToStringValue(dayOfMonth);
        String mon = DateManipulator.inTimeToStringValue(month);
        String date = day + "/" + mon + "/" + year;
        dateTextView.setText(date);
        this.date = year + "-" + mon + "-" + day;
        changeAppointmentDate(hour, date);
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    private void changeAppointmentDate(String hour, String date) {
        LocalDateTime formattedDate = DateManipulator.dateFromStringToLocalDateTime(date, hour);
        this.appointment.setDate(formattedDate);
    }

    public void removeAppointment(View view) {
        Toast.makeText(AppointmentEditorActivity.this, "Cita borrada correctamente", Toast.LENGTH_LONG).show();

        AppointmentAccumulator appointmentAccumulator = AppointmentAccumulator.getAppointmentAccumulator();
        List<Appointment> appointmentList = appointmentAccumulator.getAllElements();

        appointmentList.remove(position);

        Intent i = new Intent(this, AppointmentAccumulatorActivity.class);
        startActivity(i);

    }

}
package com.pastillasCreator.pill_box.actividades;


import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CalendarView;
import android.widget.Toast;

import androidx.annotation.RequiresApi;

import com.example.pill_box.R;

import java.util.Calendar;


public class CalendarActivity extends DefaultActivity {

    private Button dateAccessor;
    private String date;
    private int day;
    private boolean isSelected;

    @RequiresApi(api = Build.VERSION_CODES.R)
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.selectedItem = R.id.calendario;
        super.selectedContentView = R.layout.activity_calendario;
        super.onCreate(savedInstanceState);

        CalendarView cal = findViewById(R.id.calendarView);
        dateAccessor = findViewById(R.id.AccederFecha);

        isSelected = false;

        cal.setOnDateChangeListener((calendarView, year, month, dayOfMonth) -> {
            date =dayOfMonth + "/" + (month+1) + "/" + year;
            dateAccessor.setText(date);
            isSelected = true;
            Toast.makeText(getApplicationContext(), date,Toast.LENGTH_LONG).show();
            day =(Calendar.DAY_OF_WEEK +7-2) %7;
        });
    }

    public void accessOnDay(View view){
        Intent i;
        if(!isSelected){
            Toast.makeText(CalendarActivity.this, "Seleccione primero un día", Toast.LENGTH_LONG).show();
            i = new Intent (CalendarActivity.this, CalendarActivity.class);
        } else{
            Toast.makeText(CalendarActivity.this, "Accediendo a mis pastillas", Toast.LENGTH_LONG).show();
            i = new Intent(CalendarActivity.this, PillShower.class);
            i.putExtra("dia", String.valueOf(day));
            i.putExtra("fecha", this.date);
            startActivity(i);
        }
        startActivity(i);
    }

    public void goBack(View view){
        Intent i = new Intent(this, MainActivity.class);
        startActivity(i);
        Toast.makeText(CalendarActivity.this, "Volviendo al menú", Toast.LENGTH_LONG).show();

    }
}
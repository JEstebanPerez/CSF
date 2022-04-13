package com.pastillasCreator.pill_box.actividades;


import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CalendarView;
import android.widget.Toast;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import com.example.pill_box.R;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.pastillasCreator.pill_box.herramientas.FunctionsWhenClick;

import java.util.Calendar;


public class ActividadCalendario extends AppCompatActivity {

    private Button acf;
    private String fecha;
    private int dia;
    private boolean seleccionado;

    @RequiresApi(api = Build.VERSION_CODES.R)
    @SuppressLint("NonConstantResourceId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calendario);

        CalendarView cal = findViewById(R.id.calendarView);
        acf = findViewById(R.id.AccederFecha);

        seleccionado = false;

        cal.setOnDateChangeListener((calendarView, year, month, dayOfMonth) -> {
            String fecha = dayOfMonth + "/" + (month+1) + "/" + year;
            setFecha(fecha);
            acf.setText(fecha);
            seleccionado = true;
            Toast.makeText(getApplicationContext(),fecha,Toast.LENGTH_LONG).show();
            int hoy = Calendar.DAY_OF_WEEK +7-2;
            setDia(hoy%7);
        });


        BottomNavigationView bottomNavigationView = findViewById(R.id.bottom_navigation);
        bottomNavigationView.setSelectedItemId(R.id.calendario);

        FunctionsWhenClick functionsWhenClick = new FunctionsWhenClick();
        bottomNavigationView.setOnItemSelectedListener(x -> functionsWhenClick.getApply(x,getApplicationContext(),this));
    }

    public void accederDia(View view){
        if(!seleccionado){
            Toast.makeText(ActividadCalendario.this, "Seleccione primero un día", Toast.LENGTH_LONG).show();
            Intent i = new Intent (ActividadCalendario.this, ActividadCalendario.class); //Si reiniciamos este activity, se activa el true al seleccionar. De lo contrario nos encerramos aquí
            startActivity(i);
        } else{
            Intent i = new Intent(ActividadCalendario.this, MostradorPastillas.class);
            i.putExtra("dia", String.valueOf(getDia())); // La información que queremos llevarnos
            i.putExtra("fecha", getFecha());
            startActivity(i);
            Toast.makeText(ActividadCalendario.this, "Accediendo a mis pastillas", Toast.LENGTH_LONG).show();
        }
    }



    public void setDia(int dia){
        this.dia = dia;
    }

    public int getDia(){
        return dia;
    }

    public void setFecha(String fecha){
        this.fecha = fecha;
    }

    public String getFecha(){
        return fecha;
    }



    public void volver(View view){
        Intent i = new Intent(this, ActividadMain.class);
        startActivity(i);
        Toast.makeText(ActividadCalendario.this, "Volviendo al menú", Toast.LENGTH_LONG).show();

    }
}
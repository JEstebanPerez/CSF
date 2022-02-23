package com.pastillasCreator.pill_box.actividades;


import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CalendarView;
import android.widget.Toast;


import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.example.pill_box.R;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;


public class ActividadCalendario extends AppCompatActivity {

    private Button acf;
    private String fecha;
    private int dia;
    private int año;
    private int mes;
    private boolean seleccionado;

    @SuppressLint("NonConstantResourceId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calendario);

        CalendarView cal = findViewById(R.id.calendarView);
        acf = findViewById(R.id.AccederFecha);

        seleccionado = false;

        cal.setOnDateChangeListener(new CalendarView.OnDateChangeListener() {
            @Override
            public void onSelectedDayChange(@NonNull CalendarView calendarView, int year, int month, int dayOfMonth) {
                String fecha = dayOfMonth + "/" + (month+1) + "/" + year;
                setFecha(fecha);
                acf.setText(fecha);
                seleccionado = true;
                Toast.makeText(getApplicationContext(),fecha,Toast.LENGTH_LONG).show();

                Date date1 = null;
                try {
                    date1=new SimpleDateFormat("dd/MM/yyyy").parse(fecha);
                } catch (ParseException e) {
                    e.printStackTrace();
                }

                Calendar c = Calendar.getInstance();
                c.setTime(date1);

                dia = c.get(Calendar.DAY_OF_WEEK);
                switch (dia) {
                    case Calendar.MONDAY:
                        setDia(0);
                        break;
                    case Calendar.TUESDAY:
                        setDia(1);
                        break;
                    case Calendar.WEDNESDAY:
                        setDia(2);
                        break;
                    case Calendar.THURSDAY:
                        setDia(3);
                        break;
                    case Calendar.FRIDAY:
                        setDia(4);
                        break;
                    case Calendar.SATURDAY:
                        setDia(5);
                        break;
                    case Calendar.SUNDAY:
                        setDia(6);
                        break;
                }
            }
        });


        BottomNavigationView bottomNavigationView = findViewById(R.id.bottom_navigation);
        bottomNavigationView.setSelectedItemId(R.id.calendario);

        bottomNavigationView.setOnNavigationItemSelectedListener(menuItem -> {
            switch (menuItem.getItemId()){

                case R.id.home:
                    startActivity(new Intent(getApplicationContext(),
                            ActividadMain.class));
                    overridePendingTransition(0,0);
                    return true;
                case R.id.pastillero:
                    startActivity(new Intent(getApplicationContext(),
                            ActividadPastillero.class));
                    overridePendingTransition(0,0);
                    return true;
                case R.id.calendario:
                    return true;
                case R.id.citas:
                    startActivity(new Intent(getApplicationContext(),
                            ActividadCitero.class));
                    overridePendingTransition(0,0);
                    return true;
                case  R.id.ayuda:
                    startActivity(new Intent(getApplicationContext(),
                            Ayuda.class));
                    overridePendingTransition(0,0);
                    return true;
            }
            return false;

        });
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
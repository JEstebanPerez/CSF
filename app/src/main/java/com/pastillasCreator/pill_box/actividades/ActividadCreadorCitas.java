package com.pastillasCreator.pill_box.actividades;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import com.example.pill_box.R;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.pastillasCreator.pill_box.creadorElementosCalendario.CreadorCitas;
import com.pastillasCreator.pill_box.herramientas.FunctionsWhenClick;
import com.pastillasCreator.pill_box.herramientas.ManipuladorFechas;

import java.util.Calendar;

public class ActividadCreadorCitas extends AppCompatActivity {

    // Atributos de la actividad
    private EditText nombreActividad;
    private EditText descripcionActividad;
    private TextView textoHoraActividad;
    private boolean seleccionado;
    private Button acf;
    private TextView textoFecha;

    // Atributos para crear Cita
    private String nombre;
    private String descripcion;
    private String hora;
    private String fecha;

    ManipuladorFechas manipuladorFechas = new ManipuladorFechas();

    // Se conecta los campos de texto con el código y el desplegable asigna qué valor tendrá al final cita
    @androidx.annotation.RequiresApi(api = Build.VERSION_CODES.R)
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_citas_creator);

        // Datos imprescindibles
        nombreActividad = findViewById(R.id.nombreCita);
        descripcionActividad = findViewById(R.id.descripcionCita);
        textoHoraActividad = findViewById(R.id.horaCita);
        acf = findViewById(R.id.buttonFecha);
        textoFecha = findViewById(R.id.fechaView);

        BottomNavigationView bottomNavigationView = findViewById(R.id.bottom_navigation);
        bottomNavigationView.setSelectedItemId(R.id.citas);

        FunctionsWhenClick functionsWhenClick = new FunctionsWhenClick();
        bottomNavigationView.setOnItemSelectedListener(functionsWhenClick::getApply);
    }

    // Comprueba que los datos importantes no estén vacíos y si lo están impide que se guarden
    @RequiresApi(api = Build.VERSION_CODES.O)
    public void guardar (View view) {
        boolean cambiaActividad ;
        nombre = nombreActividad.getText().toString();
        descripcion = descripcionActividad.getText().toString();

        String mensaje;
        StringBuilder mensajeBuilder = new StringBuilder();

        CreadorCitas creadorCitas = new CreadorCitas(nombre, descripcion, hora, fecha);
        cambiaActividad = creadorCitas.crearCita(mensajeBuilder);

        mensaje = mensajeBuilder.toString();

        Toast.makeText(this, mensaje, Toast.LENGTH_SHORT).show();
        if (cambiaActividad) {
            startActivity(new Intent(getApplicationContext(), ActividadCitero.class));
        }
    }

    public void abrirHora(View view) {
        Calendar c = Calendar.getInstance();
        int hora = c.get(Calendar.HOUR_OF_DAY);
        int minuto = c.get(Calendar.MINUTE);

        TimePickerDialog tmd = new TimePickerDialog(ActividadCreadorCitas.this,
                (timePicker, hourOfDay, minute) -> {
                    String minuto1 = manipuladorFechas.IntegerAStringFecha(minute);
                    String hora1 = manipuladorFechas.IntegerAStringFecha(hourOfDay);
                    String horaCompleta = hora1 + ":" + minuto1;
                    textoHoraActividad.setText(horaCompleta);
                    setHora(horaCompleta);
                },hora, minuto, false); // Contexto, listener
        tmd.show(); // Si no ponemos la función show, no se va a mostrar
    }

    public void abrirCalendario(View view) {

        Calendar cal = Calendar.getInstance(); //Inicializamos el calendario
        int anio = cal.get(Calendar.YEAR); //Variable para guardar el año
        int mes = cal.get(Calendar.MONTH); //Variable para guardar el mes
        int dia = cal.get(Calendar.DAY_OF_MONTH); //Variable para guardar el día

        DatePickerDialog dpd = new DatePickerDialog(ActividadCreadorCitas.this,
                (datePicker, year, month, dayOfMonth) -> {
                    String day = manipuladorFechas.IntegerAStringFecha(dayOfMonth);
                    String mon = manipuladorFechas.IntegerAStringFecha(month);
                    String fecha = day + "/" + mon + "/" + year;
                    textoFecha.setText(fecha);
                    setFecha(year + "-" + mon + "-" + day);
                    }, anio, mes, dia);

        dpd.show();
    }

    public void setHora(String hora) {
        this.hora = hora;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }
}


package com.pastillasCreator.pill_box.actividades;

import static com.pastillasCreator.pill_box.almacenaje.Citero.getCitero;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import com.example.pill_box.R;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.pastillasCreator.pill_box.almacenaje.Citero;
import com.pastillasCreator.pill_box.almacenaje.Pastillero;
import com.pastillasCreator.pill_box.elementosCalendario.Cita;
import com.pastillasCreator.pill_box.elementosCalendario.Pastilla;
import com.pastillasCreator.pill_box.herramientas.ManipuladorFechas;

import java.time.LocalDateTime;
import java.util.Calendar;
import java.util.List;

public class EditarCitas extends AppCompatActivity {

    private EditText nombr;
    private EditText descrip;
    private TextView hor;
    private TextView fech;

    private String hora;
    private String fecha;

    Cita cita;
    Cita citaCopia;
    int posicion;

    ManipuladorFechas manipuladorFechas = new ManipuladorFechas();

    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_editar_citas);

        nombr = findViewById(R.id.nombrecitaEdit);
        descrip = findViewById(R.id.DescripCitaEdit);
        hor = findViewById(R.id.horaCitaEdit);
        fech = findViewById(R.id.fechaCitaEdit);

        Intent incomingIntent = getIntent();
        posicion = Integer.parseInt(incomingIntent.getStringExtra("posicion"));

        Citero citero = getCitero();
        cita = citero.getCita(posicion);
        citaCopia = citero.getCita(posicion);

        hora = String.valueOf(cita.getDate().getHour()) + ":" + String.valueOf(cita.getDate().getMinute());
        fecha = String.valueOf(cita.getDate().getYear() + "-" +
                String.valueOf(cita.getDate().getMonthValue()) + "-" +
                String.valueOf(cita.getDate().getDayOfMonth()));

        nombr.setText(cita.getNombre());
        descrip.setText(cita.getDescripcion());
        hor.setText(hora);
        fech.setText(String.valueOf(cita.getDate().getDayOfMonth()) + "/" +
                String.valueOf(cita.getDate().getMonthValue()) + "/" +
                String.valueOf(cita.getDate().getYear()));

        BottomNavigationView bottomNavigationView = findViewById(R.id.bottom_navigation);
        bottomNavigationView.setSelectedItemId(R.id.citas);

        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @RequiresApi(api = Build.VERSION_CODES.O)
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                switch (menuItem.getItemId()){

                    case R.id.home:
                        citero.addCita(citaCopia);
                        startActivity(new Intent(getApplicationContext(),
                                ActividadMain.class));
                        overridePendingTransition(0,0);
                        return true;
                    case R.id.pastillero:
                        citero.addCita(citaCopia);
                        startActivity(new Intent(getApplicationContext(),
                                ActividadPastillero.class));
                        overridePendingTransition(0,0);
                        return true;
                    case R.id.calendario:
                        citero.addCita(citaCopia);
                        startActivity(new Intent(getApplicationContext(),
                                ActividadCalendario.class));
                        overridePendingTransition(0,0);
                        return true;
                    case R.id.citas:
                        citero.addCita(citaCopia);
                        startActivity(new Intent(getApplicationContext(),
                                ActividadCitero.class));
                        overridePendingTransition(0,0);
                        return true;
                    case  R.id.ayuda:
                        citero.addCita(citaCopia);
                        startActivity(new Intent(getApplicationContext(),
                                Ayuda.class));
                        overridePendingTransition(0,0);
                        return true;
                }
                return false;
            }
        });

    }

    // Comprueba que los datos importantes no estén vacíos y si lo están impide que se guarden
    @RequiresApi(api = Build.VERSION_CODES.O)
    public void guardar (View view) {
        String nomCita = nombr.getText().toString();
        String desCita = descrip.getText().toString();
        if (!nomCita.isEmpty()) {
            cita.setNombre(nomCita);
            if (!desCita.isEmpty()) {
                cita.setDescripcion(desCita);
                Citero citero = getCitero();
                citero.addCita(cita);
                Toast.makeText(this, "Se modificó la cita: " + nomCita + " correctamente.", Toast.LENGTH_SHORT).show();
                startActivity(new Intent(getApplicationContext(), ActividadCitero.class));
            } else {
                Toast.makeText(this, "Se necesita incluir una descripción.", Toast.LENGTH_SHORT).show();
            }
        } else{
            Toast.makeText(this, "Se necesita incluir un nombre.", Toast.LENGTH_SHORT).show();
        }
    }

    public void abrirHora(View view) {
        Calendar c = Calendar.getInstance();
        int hora = c.get(Calendar.HOUR_OF_DAY);
        int minuto = c.get(Calendar.MINUTE);

        TimePickerDialog tmd = new TimePickerDialog(EditarCitas.this,
                new TimePickerDialog.OnTimeSetListener() {
                    @RequiresApi(api = Build.VERSION_CODES.O)
                    @Override
                    public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
                        String minuto = manipuladorFechas.IntegerAStringFecha(minute);
                        String hora = manipuladorFechas.IntegerAStringFecha(hourOfDay);
                        String horaCompleta = hora + ":" + minuto;
                        hor.setText(horaCompleta);
                        hora = horaCompleta;
                        modificarDateCita(hora, fecha);
                    }
                },hora, minuto, false);
        tmd.show(); // Si no ponemos la función show, no se va a mostrar
    }

    public void abrirCalendario(View view) {

        Calendar cal = Calendar.getInstance(); //Inicializamos el calendario
        int anio = cal.get(Calendar.YEAR); //Variable para guardar el año
        int mes = cal.get(Calendar.MONTH); //Variable para guardar el mes
        int dia = cal.get(Calendar.DAY_OF_MONTH); //Variable para guardar el día

        DatePickerDialog dpd = new DatePickerDialog(EditarCitas.this,
                new DatePickerDialog.OnDateSetListener() {
                    @RequiresApi(api = Build.VERSION_CODES.O)
                    @Override
                    public void onDateSet(DatePicker datePicker, int year, int month, int dayOfMonth) {
                        String day = manipuladorFechas.IntegerAStringFecha(dayOfMonth);
                        String mon = manipuladorFechas.IntegerAStringFecha(month);
                        String fecha = day + "/" + mon + "/" + year;
                        fech.setText(fecha);
                        fecha = year + "-" + mon + "-" + day;
                        modificarDateCita(hora, fecha);
                    }
                }, anio, mes, dia);

        dpd.show();
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    private void modificarDateCita(String hora, String fecha) {
        LocalDateTime fechaConFormato = manipuladorFechas.fechaYHoraALocalDateTime(fecha, hora);
        cita.setDate(fechaConFormato);
    }

    public void borrarCita(View view){
        /*Intent i = new Intent(this, ActividadCalendario.class);
        startActivity(i);*/

        Citero citero = Citero.getCitero();
        List<Cita> listaCitas = citero.getListaCitas();

        listaCitas.remove(posicion);

        Intent i = new Intent(this, ActividadCitero.class);
        startActivity(i);
        Toast.makeText(EditarCitas.this, "Cita borrada correctamente", Toast.LENGTH_LONG).show();

    }

}
package com.pastillasCreator.pill_box.actividades;

import static com.pastillasCreator.pill_box.almacenaje.Citero.getCitero;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import com.example.pill_box.R;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.pastillasCreator.pill_box.almacenaje.Citero;
import com.pastillasCreator.pill_box.elementosCalendario.Cita;
import com.pastillasCreator.pill_box.herramientas.FunctionsWhenClick;
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

    @RequiresApi(api = Build.VERSION_CODES.R)
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

        hora = cita.getDate().getHour() + ":" + cita.getDate().getMinute();
        fecha = cita.getDate().getYear() + "-" +
                cita.getDate().getMonthValue() + "-" +
                cita.getDate().getDayOfMonth();

        nombr.setText(cita.getNombre());
        descrip.setText(cita.getDescripcion());
        hor.setText(hora);
        String fecha = cita.getDate().getDayOfMonth() + "/" +
                cita.getDate().getMonthValue() + "/" +
                cita.getDate().getYear();
        fech.setText(fecha);

        BottomNavigationView bottomNavigationView = findViewById(R.id.bottom_navigation);
        bottomNavigationView.setSelectedItemId(R.id.citas);

        FunctionsWhenClick functionsWhenClick = new FunctionsWhenClick();
        bottomNavigationView.setOnItemSelectedListener(functionsWhenClick::getApply);

    }

    // Comprueba que los datos importantes no estén vacíos y si lo están impide que se guarden
    @RequiresApi(api = Build.VERSION_CODES.O)
    public void guardar(View view) {
        String nomCita = nombr.getText().toString();
        String desCita = descrip.getText().toString();
        if (nomCita.isEmpty()) {
            Toast.makeText(this, "Se necesita incluir un nombre.", Toast.LENGTH_SHORT).show();
            return;
        }
        cita.setNombre(nomCita);
        if (desCita.isEmpty()) {
            Toast.makeText(this, "Se necesita incluir una descripción.", Toast.LENGTH_SHORT).show();
            return;
        }
        cita.setDescripcion(desCita);
        Citero citero = getCitero();
        citero.addCita(cita);
        Toast.makeText(this, "Se modificó la cita: " + nomCita + " correctamente.", Toast.LENGTH_SHORT).show();
        startActivity(new Intent(getApplicationContext(), ActividadCitero.class));
    }

    @androidx.annotation.RequiresApi(api = Build.VERSION_CODES.O)
    public void abrirHora(View view) {
        Calendar c = Calendar.getInstance();
        int hora = c.get(Calendar.HOUR_OF_DAY);
        int minuto = c.get(Calendar.MINUTE);

        TimePickerDialog tmd = new TimePickerDialog(EditarCitas.this,
                (view1, hourOfDay, minute) -> {
                    String minuto1 = manipuladorFechas.IntegerAStringFecha(minute);
                    String hora1 = manipuladorFechas.IntegerAStringFecha(hourOfDay);
                    String horaCompleta = hora1 + ":" + minuto1;
                    hor.setText(horaCompleta);
                    hora1 = horaCompleta;
                    modificarDateCita(hora1, fecha);
                }, hora, minuto, false);
        tmd.show();
    }

    @androidx.annotation.RequiresApi(api = Build.VERSION_CODES.O)
    public void abrirCalendario(View view) {

        Calendar cal = Calendar.getInstance();
        int anio = cal.get(Calendar.YEAR);
        int mes = cal.get(Calendar.MONTH);
        int dia = cal.get(Calendar.DAY_OF_MONTH);

        DatePickerDialog dpd = new DatePickerDialog(EditarCitas.this,
                (datePicker, year, month, dayOfMonth) -> {
                    String day = manipuladorFechas.IntegerAStringFecha(dayOfMonth);
                    String mon = manipuladorFechas.IntegerAStringFecha(month);
                    String fecha = day + "/" + mon + "/" + year;
                    fech.setText(fecha);
                    fecha = year + "-" + mon + "-" + day;
                    modificarDateCita(hora, fecha);
                }, anio, mes, dia);

        dpd.show();
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    private void modificarDateCita(String hora, String fecha) {
        LocalDateTime fechaConFormato = manipuladorFechas.fechaYHoraALocalDateTime(fecha, hora);
        cita.setDate(fechaConFormato);
    }

    public void borrarCita(View view) {
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
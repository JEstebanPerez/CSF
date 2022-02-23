package com.pastillasCreator.pill_box.actividades;


import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.CalendarView;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.example.pill_box.R;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.pastillasCreator.pill_box.almacenaje.Citero;
import com.pastillasCreator.pill_box.almacenaje.Pastillero;
import com.pastillasCreator.pill_box.elementosCalendario.Pastilla;

import java.util.List;

public class Informacion extends AppCompatActivity {

    private EditText nombr;
    private EditText descrip;
    private EditText tot;
    private EditText tip;
    private EditText fech;
    private String hora;
    private Button botonHora;
    private EditText hor;
    private Button botonBorr;
    private int pos;

    CalendarView cal;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_informacion);
        /*Todos los botones y cosas que vayas a usar en la vista
         *debes inicializarlo aquí
         */
        nombr = findViewById(R.id.textoNombre);
        descrip = findViewById(R.id.textoDescripcion);
        tot = findViewById(R.id.textoTotal);
        tip = findViewById(R.id.textoTipo);
        fech = findViewById(R.id.textoFecha);
        hor = findViewById(R.id.textoHora); //No olvides inicializarlo aquí, si no te pueden dar errores.
        botonBorr = findViewById(R.id.botonBorrar);

        Intent incomingIntent = getIntent(); //llamamos para coger la información de la fecha.
        String nombre = incomingIntent.getStringExtra("nombre");
        String descripcion = incomingIntent.getStringExtra("descripcion");
        String total = incomingIntent.getStringExtra("total");
        String tipo = incomingIntent.getStringExtra("tipo");
        String fecha = incomingIntent.getStringExtra("fecha"); //Cogemos la información de la fecha.
        String hora = incomingIntent.getStringExtra("hora");
        String posicion = incomingIntent.getStringExtra("posicion");
        pos = Integer.valueOf(posicion);

        nombr.setText("Nombre" + ":" + nombre);
        descrip.setText("Descripcion" + ":" + descripcion);
        tot.setText("Total" + ":" + total);
        tip.setText("Tipo" + ":" + tipo);
        fech.setText("Fecha" + ":" + fecha);
        hor.setText("Hora" + ":" + hora);


        BottomNavigationView bottomNavigationView = findViewById(R.id.bottom_navigation);
        bottomNavigationView.setSelectedItemId(R.id.calendario);

        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                switch (menuItem.getItemId()){

                    case R.id.home:
                        startActivity(new Intent(getApplicationContext(),
                                ActividadMain.class));
                        overridePendingTransition(0,0);
                        return true;
                    case R.id.pastillero:
                        startActivity(new Intent(getApplicationContext(),
                                ActividadPastillero.class)); //modificacion
                        overridePendingTransition(0,0);
                        return true;
                    case R.id.calendario:
                        startActivity(new Intent(getApplicationContext(),
                                ActividadCalendario.class));
                        overridePendingTransition(0,0);
                        return true;
                    case R.id.citas:
                        startActivity(new Intent(getApplicationContext(),
                                ActividadCitero.class));
                        overridePendingTransition(0,0);
                        return true;
                }
                return false;
            }
        });

    }

    /**public void abrirHora(View view) {
        Calendar c = Calendar.getInstance();
        int hora = c.get(Calendar.HOUR_OF_DAY);
        int min = c.get(Calendar.MINUTE);

        TimePickerDialog tmd = new TimePickerDialog(Informacion.this, new TimePickerDialog.OnTimeSetListener() {
            @Override
            public void onTimeSet(TimePicker timePicker, int hourOfDay, int minute) {
                String hora = hourOfDay + ":" + minute;
                setHora(hora);
                hor.setText("Hora" + ":" + hora);
            }
        },hora, min, false); // Contexto, listener,
        tmd.show(); // Si no ponemos la función show, no se va a mostrar.
    }*/

    public void setHora(String hora){
        this.hora = hora;
    }

    public String getHora(){
        return hora;
    }

    public void borrarPastilla(View view){
        /*Intent i = new Intent(this, ActividadCalendario.class);
        startActivity(i);*/

        Pastillero pastillero = Pastillero.getPastillero();
        List<Pastilla> listaPastillas = pastillero.getListaPastillas();

        listaPastillas.remove(pos);

        Intent i = new Intent(this, ActividadCalendario.class);
        startActivity(i);
        Toast.makeText(Informacion.this, "Pastilla borrada correctamente", Toast.LENGTH_LONG).show();

    }

    /*public void volverCalendario (View view){
        Intent i = new Intent(this, MostradorPastillas.class);
        startActivity(i);
    }*/
}
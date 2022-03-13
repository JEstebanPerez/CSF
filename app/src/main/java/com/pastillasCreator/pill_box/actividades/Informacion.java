package com.pastillasCreator.pill_box.actividades;


import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import com.example.pill_box.R;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.pastillasCreator.pill_box.almacenaje.Pastillero;

import java.util.HashMap;
import java.util.Map;

public class Informacion extends AppCompatActivity {

    private String hora;
    private int pos;

    @RequiresApi(api = Build.VERSION_CODES.R)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_informacion);
        Intent incomingIntent = getIntent(); //llamamos para coger la información de la fecha.

        HashMap<Integer,String> textSetter = (HashMap<Integer, String>) Map.of(
                R.id.textoNombre,"Nombre",
                R.id.textoDescripcion,"Descripcion",
                R.id.textoTotal,"Total",
                R.id.textoTipo,"Tipo",
                R.id.textoFecha,"Fecha",
                R.id.textoHora,"Hora"
        );

        Map<Integer,Class<? extends AppCompatActivity>> redirect= Map.of(
                R.id.home,ActividadMain.class,
                R.id.pastillero,ActividadPastillero.class,
                R.id.calendario,ActividadCalendario.class,
                R.id.citas,ActividadCitero.class
        );

        textSetter.forEach((k,v)-> {
            String incomingText = incomingIntent.getStringExtra(v.toLowerCase());
            String textToShow = v.concat(":").concat(incomingText);
            EditText view = findViewById(k);
            view.setText(textToShow);
        });
        pos = Integer.parseInt(incomingIntent.getStringExtra("posicion"));

        BottomNavigationView bottomNavigationView = findViewById(R.id.bottom_navigation);
        bottomNavigationView.setSelectedItemId(R.id.calendario);

        bottomNavigationView.setOnItemSelectedListener(menuItem -> {
            if(redirect.containsKey(menuItem.getItemId())){
                Class<?> clase = redirect.get(menuItem.getItemId());
                startActivity(new Intent(getApplicationContext(),clase));
                overridePendingTransition(0,0);
                return true;
            }
            return false;
        });
    }

    public void setHora(String hora){
        this.hora = hora;
    }

    public String getHora(){
        return hora;
    }

    public void borrarPastilla(View view){
        Pastillero pastillero = Pastillero.getPastillero();
        pastillero.getListaPastillas().remove(pos);
        Intent i = new Intent(this, ActividadCalendario.class);
        startActivity(i);
        Toast.makeText(Informacion.this, "Pastilla borrada correctamente", Toast.LENGTH_LONG).show();

    }
}
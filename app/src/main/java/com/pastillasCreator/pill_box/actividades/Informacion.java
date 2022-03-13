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
import com.pastillasCreator.pill_box.herramientas.FunctionsWhenClick;

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

        textSetter.forEach((k,v)-> {
            String incomingText = incomingIntent.getStringExtra(v.toLowerCase());
            String textToShow = v.concat(":").concat(incomingText);
            EditText view = findViewById(k);
            view.setText(textToShow);
        });

        pos = Integer.parseInt(incomingIntent.getStringExtra("posicion"));

        BottomNavigationView bottomNavigationView = findViewById(R.id.bottom_navigation);
        bottomNavigationView.setSelectedItemId(R.id.calendario);

        FunctionsWhenClick functionsWhenClick = new FunctionsWhenClick();
        bottomNavigationView.setOnItemSelectedListener(functionsWhenClick::getApply);
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
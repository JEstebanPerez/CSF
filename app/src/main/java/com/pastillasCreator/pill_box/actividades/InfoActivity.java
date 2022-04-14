package com.pastillasCreator.pill_box.actividades;


import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.RequiresApi;

import com.example.pill_box.R;
import com.pastillasCreator.pill_box.almacenaje.PillAccumulator;

import java.util.HashMap;

public class InfoActivity extends DefaultActivity {

    private int pos;

    @RequiresApi(api = Build.VERSION_CODES.R)
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.selectedItem = R.id.calendario;
        super.selectedContentView = R.layout.activity_informacion;
        super.onCreate(savedInstanceState);

        Intent incomingIntent = getIntent();

        HashMap<Integer,String> textSetter = new HashMap<>();
        textSetter.put(R.id.textoNombre,"Nombre");
        textSetter.put(R.id.textoDescripcion,"Descripcion");
        textSetter.put(R.id.textoTotal,"Total");
        textSetter.put(R.id.textoTipo,"Tipo");
        textSetter.put(R.id.textoFecha,"Fecha");
        textSetter.put(R.id.textoHora,"Hora");

        textSetter.forEach((k,v)-> {
            String incomingText = incomingIntent.getStringExtra(v.toLowerCase());
            String textToShow = v.concat(":").concat(incomingText);
            EditText view = findViewById(k);
            view.setText(textToShow);
        });

        pos = Integer.parseInt(incomingIntent.getStringExtra("posicion"));

    }

    public void removePill(View view){
        PillAccumulator pillAccumulator = PillAccumulator.getPillAccumulator();
        pillAccumulator.removeElement(pos);
        Intent i = new Intent(this, CalendarActivity.class);
        startActivity(i);
        Toast.makeText(InfoActivity.this, "Pastilla borrada correctamente", Toast.LENGTH_LONG).show();

    }
}
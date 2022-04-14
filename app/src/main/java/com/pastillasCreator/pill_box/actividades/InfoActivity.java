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
import com.pastillasCreator.pill_box.elementosCalendario.Pill;
import com.pastillasCreator.pill_box.generic_activityes.DefaultActivity;

import java.util.HashMap;

public class InfoActivity extends DefaultActivity {

    private Pill pill;

    @RequiresApi(api = Build.VERSION_CODES.R)
    @Override
    public void onCreate(Bundle savedInstanceState) {
        selectedItem = R.id.calendario;
        selectedContentView = R.layout.activity_informacion;

        context = InfoActivity.this;

        super.onCreate(savedInstanceState);

        Intent incomingIntent = getIntent();

        pill = (Pill) incomingIntent.getSerializableExtra("pill");

        HashMap<Integer,String> textSetter = new HashMap<>();
        textSetter.put(R.id.textoNombre,pill.getName());
        textSetter.put(R.id.textoDescripcion,pill.getDescription());
        textSetter.put(R.id.textoTotal,pill.getTotal()+"");
        textSetter.put(R.id.textoTipo,pill.getType().name());
        textSetter.put(R.id.textoHora,pill.getHour());

        textSetter.forEach((k,v)-> {
            EditText view = findViewById(k);
            view.setText(v);
        });

    }

    public void removePill(View view){
        PillAccumulator pillAccumulator = PillAccumulator.getPillAccumulator();
        pillAccumulator.removeElement(pill);
        Intent i = new Intent(this, CalendarActivity.class);
        startActivity(i);
        Toast.makeText(InfoActivity.this, "Pastilla borrada correctamente", Toast.LENGTH_LONG).show();
    }
}
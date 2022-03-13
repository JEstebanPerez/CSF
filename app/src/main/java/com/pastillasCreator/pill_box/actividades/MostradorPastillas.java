package com.pastillasCreator.pill_box.actividades;


import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import com.example.pill_box.R;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.pastillasCreator.pill_box.almacenaje.Pastillero;
import com.pastillasCreator.pill_box.elementosCalendario.Pastilla;
import com.pastillasCreator.pill_box.herramientas.FunctionsWhenClick;

import java.util.ArrayList;
import java.util.List;

public class MostradorPastillas extends AppCompatActivity {

    ListView listView;

    @RequiresApi(api = Build.VERSION_CODES.R)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mostrador_pastillas);

        Intent incomingIntent = getIntent(); //llamamos para coger la información de la fecha.
        String dia = incomingIntent.getStringExtra("dia"); //Cogemos la información del dia.
        String fecha = incomingIntent.getStringExtra("fecha");

        listView = (ListView) findViewById(R.id.listViewPastillas);
        Pastillero pastillero = Pastillero.getPastillero();
        List<Pastilla> listaPastillas = pastillero.getListaPastillas();

        ArrayList<String> arrayDeNombres = new ArrayList<>();

        for (Pastilla pastilla: listaPastillas) {
            if(pastilla.getDayOfWeekList()[Integer.parseInt(dia)]) {
                arrayDeNombres.add(pastilla.getNombre());
            }
        }
        ArrayAdapter arrayAdapter=new ArrayAdapter(this,android.R.layout.simple_list_item_1,arrayDeNombres);
        
        listView.setAdapter(arrayAdapter);
        listView.setOnItemClickListener((adapterView, view, position, id) -> {
            Intent i = new Intent(MostradorPastillas.this, Informacion.class);
            i.putExtra("nombre",listaPastillas.get(position).getNombre());
            i.putExtra("descripcion",listaPastillas.get(position).getDescripcion());
            i.putExtra("total",String.valueOf(listaPastillas.get(position).getTotal()));
            i.putExtra("tipo",String.valueOf(listaPastillas.get(position).getTipo()));
            i.putExtra("fecha", fecha);
            i.putExtra("hora", listaPastillas.get(position).getHora());
            i.putExtra("posicion", String.valueOf(position));
            startActivity(i);
        });

        BottomNavigationView bottomNavigationView = findViewById(R.id.bottom_navigation);
        bottomNavigationView.setSelectedItemId(R.id.calendario);
        FunctionsWhenClick functionsWhenClick = new FunctionsWhenClick();

        bottomNavigationView.setOnItemSelectedListener(menuItem -> {
            Integer id = menuItem.getItemId();
            return functionsWhenClick.apply().apply(id);
        });
    }

}
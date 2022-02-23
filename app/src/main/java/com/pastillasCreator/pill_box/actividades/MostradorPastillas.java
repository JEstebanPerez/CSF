package com.pastillasCreator.pill_box.actividades;


import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Spinner;

import com.example.pill_box.R;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.pastillasCreator.pill_box.almacenaje.Citero;
import com.pastillasCreator.pill_box.almacenaje.Pastillero;
import com.pastillasCreator.pill_box.elementosCalendario.Pastilla;

import java.util.ArrayList;
import java.util.List;

public class MostradorPastillas extends AppCompatActivity {

    ListView listView;
    private String dia;
    private String fecha;
    private Spinner spinner;
    //Pastillero pastillero = Pastillero.getPastillero();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mostrador_pastillas);


        Intent incomingIntent = getIntent(); //llamamos para coger la información de la fecha.
        dia = incomingIntent.getStringExtra("dia"); //Cogemos la información del dia.
        fecha = incomingIntent.getStringExtra("fecha");

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
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long id) {
                Intent i = new Intent(MostradorPastillas.this, Informacion.class);
                i.putExtra("nombre",listaPastillas.get(position).getNombre());
                i.putExtra("descripcion",listaPastillas.get(position).getDescripcion());
                i.putExtra("total",String.valueOf(listaPastillas.get(position).getTotal()));
                i.putExtra("tipo",String.valueOf(listaPastillas.get(position).getTipo()));
                i.putExtra("fecha", fecha);
                i.putExtra("hora", listaPastillas.get(position).getHora());
                i.putExtra("posicion", String.valueOf(position));
                startActivity(i);
            }
        });

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
                                ActividadPastillero.class));
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

    public String getDia() {
        return dia;
    }

    public void setDia(String dia) {
        this.dia = dia;
    }

}
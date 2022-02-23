package com.pastillasCreator.pill_box.actividades;

import static com.pastillasCreator.pill_box.almacenaje.Citero.getCitero;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.example.pill_box.R;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.pastillasCreator.pill_box.almacenaje.Citero;

import java.util.List;

public class ActividadCitero extends AppCompatActivity {

    ListView listView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_citero);

        BottomNavigationView bottomNavigationView = findViewById(R.id.bottom_navigation);
        bottomNavigationView.setSelectedItemId(R.id.citas);

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
                    case  R.id.ayuda:
                        startActivity(new Intent(getApplicationContext(),
                                Ayuda.class));
                        overridePendingTransition(0,0);
                        return true;
                }
                return false;
            }
        });

        visualizarLista();
    }

    private void visualizarLista() {
        Citero citero = getCitero();
        List<String> listaNombreCitas = citero.getListaNombreCitas() ;

        listView = (ListView) findViewById(R.id.listCita);

        ArrayAdapter arrayAdapter= new ArrayAdapter(this,android.R.layout.simple_list_item_1,listaNombreCitas);

        listView.setAdapter(arrayAdapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent i = new Intent(ActividadCitero.this, EditarCitas.class);
                i.putExtra("posicion", Integer.toString(position));
                startActivity(i);
            }
        });
    }

    public void añadirCita(View view) {
        startActivity(new Intent(getApplicationContext(), ActividadCreadorCitas.class));
    }
}
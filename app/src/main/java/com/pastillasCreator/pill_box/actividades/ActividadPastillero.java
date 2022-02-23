package com.pastillasCreator.pill_box.actividades;

import static com.pastillasCreator.pill_box.almacenaje.Pastillero.getPastillero;

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
import com.pastillasCreator.pill_box.almacenaje.Pastillero;
import com.pastillasCreator.pill_box.creadorElementosCalendario.PastillasCreador;

import java.util.List;

public class ActividadPastillero extends AppCompatActivity {

    ListView listView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pastillero);

        BottomNavigationView bottomNavigationView = findViewById(R.id.bottom_navigation);
        bottomNavigationView.setSelectedItemId(R.id.pastillero);

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

        visualizadorLista();
    }

    private void visualizadorLista() {
        Pastillero listado = getPastillero();
        List<String> listaNombrePastillas = listado.getListaNombrePastillas();

        listView = (ListView) findViewById(R.id.listaPastillas);

        ArrayAdapter arrayAdapter = new ArrayAdapter(this,android.R.layout.simple_list_item_1,listaNombrePastillas);

        listView.setAdapter(arrayAdapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {
                Intent i = new Intent(ActividadPastillero.this, EditarPastilla.class);
                i.putExtra("posicion", Integer.toString(position)); // Llevarse a la actividad la posición de la pastilla que queremos editar
                startActivity(i);
            }
        });
    }

    public void añadirPastilla(View view) {
        startActivity(new Intent(getApplicationContext(), PastillasCreador.class));
    }
}
package com.pastillasCreator.pill_box.actividades;

import static com.pastillasCreator.pill_box.almacenaje.Pastillero.getPastillero;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListAdapter;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.pill_box.R;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.pastillasCreator.pill_box.almacenaje.Pastillero;
import com.pastillasCreator.pill_box.herramientas.FunctionsWhenClick;

import java.util.List;

public class ActividadPastillero extends AppCompatActivity {

    ListView listView;
    @androidx.annotation.RequiresApi(api = Build.VERSION_CODES.R)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pastillero);

        BottomNavigationView bottomNavigationView = findViewById(R.id.bottom_navigation);
        bottomNavigationView.setSelectedItemId(R.id.pastillero);

        FunctionsWhenClick functionsWhenClick = new FunctionsWhenClick();
        bottomNavigationView.setOnItemSelectedListener(x -> functionsWhenClick.getApply(x,getApplicationContext(),this));

        visualizadorLista();
    }

    private void visualizadorLista() {
        Pastillero listado = getPastillero();
        List<String> listaNombrePastillas = listado.getListaNombrePastillas();

        listView = findViewById(R.id.listaPastillas);

        ListAdapter arrayAdapter = new ArrayAdapter<>(this,android.R.layout.simple_list_item_1,listaNombrePastillas);

        listView.setAdapter(arrayAdapter);
        listView.setOnItemClickListener((adapterView, view, position, l) -> {
            Intent i = new Intent(ActividadPastillero.this, EditarPastilla.class);
            i.putExtra("posicion", Integer.toString(position)); // Llevarse a la actividad la posición de la pastilla que queremos editar
            startActivity(i);
        });
    }
}
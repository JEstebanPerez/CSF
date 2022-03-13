package com.pastillasCreator.pill_box.actividades;

import static com.pastillasCreator.pill_box.almacenaje.Citero.getCitero;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListAdapter;
import android.widget.ListView;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import com.example.pill_box.R;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.pastillasCreator.pill_box.almacenaje.Citero;
import com.pastillasCreator.pill_box.herramientas.FunctionsWhenClick;

import java.util.List;

public class ActividadCitero extends AppCompatActivity {

    ListView listView;

    @RequiresApi(api = Build.VERSION_CODES.R)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_citero);

        BottomNavigationView bottomNavigationView = findViewById(R.id.bottom_navigation);
        bottomNavigationView.setSelectedItemId(R.id.citas);

        FunctionsWhenClick functionsWhenClick = new FunctionsWhenClick();
        bottomNavigationView.setOnItemSelectedListener(functionsWhenClick::getApply);

        visualizarLista();
    }

    private void visualizarLista() {
        Citero citero = getCitero();
        List<String> listaNombreCitas = citero.getListaNombreCitas() ;

        listView = findViewById(R.id.listCita);
        int layout = android.R.layout.simple_list_item_1;

        ListAdapter arrayAdapter= new ArrayAdapter<>(this,layout,listaNombreCitas);

        listView.setAdapter(arrayAdapter);
        listView.setOnItemClickListener((parent, view, position, id) -> {
            Intent i = new Intent(ActividadCitero.this, EditarCitas.class);
            i.putExtra("posicion", Integer.toString(position));
            startActivity(i);
        });
    }

    public void añadirCita(View view) {
        startActivity(new Intent(getApplicationContext(), ActividadCreadorCitas.class));
    }
}
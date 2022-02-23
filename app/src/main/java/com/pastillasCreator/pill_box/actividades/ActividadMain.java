
package com.pastillasCreator.pill_box.actividades;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import com.example.pill_box.R;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.pastillasCreator.pill_box.actividades.ActividadCalendario;
import com.pastillasCreator.pill_box.actividades.ActividadCitero;
import com.pastillasCreator.pill_box.actividades.ActividadPastillero;
import com.pastillasCreator.pill_box.actividades.Ayuda;

public class ActividadMain extends AppCompatActivity {



    @SuppressLint("NonConstantResourceId")
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        BottomNavigationView bottomNavigationView = findViewById(R.id.bottom_navigation);
        bottomNavigationView.setSelectedItemId(R.id.home);

        bottomNavigationView.setOnNavigationItemSelectedListener(menuItem -> {
            switch (menuItem.getItemId()){

                case R.id.home:
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

        });
    }
}

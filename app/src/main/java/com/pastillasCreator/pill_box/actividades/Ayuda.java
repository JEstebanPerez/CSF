package com.pastillasCreator.pill_box.actividades;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.pill_box.R;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class Ayuda extends AppCompatActivity {

    private String mensaje ="¡Gracias por instalar esta app! \n\nPara comenzar su experiencia vaya a la sección Pastillero y añada una pastilla o a la sección Citas y añada una. \n\nToda la información que añada se guardará en las listas correspondientes y se creará una alarma automáticamente.\n\n\nNunca volverá a olvidarse de sus pastillas.";
    private TextView textoAyuda;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ayuda);

        textoAyuda = findViewById(R.id.textViewAyuda);
        textoAyuda.setText(mensaje);

        BottomNavigationView bottomNavigationView = findViewById(R.id.bottom_navigation);
        bottomNavigationView.setSelectedItemId(R.id.ayuda);

        bottomNavigationView.setOnNavigationItemSelectedListener(menuItem -> {
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
                    return true;
            }
            return false;
        });

    }
}
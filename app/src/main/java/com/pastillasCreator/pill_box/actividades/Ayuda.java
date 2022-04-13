package com.pastillasCreator.pill_box.actividades;

import android.os.Build;
import android.os.Bundle;
import android.widget.TextView;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import com.example.pill_box.R;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.pastillasCreator.pill_box.herramientas.FunctionsWhenClick;

public class Ayuda extends AppCompatActivity {

    private String mensaje ="¡Gracias por instalar esta app! \n\nPara comenzar su experiencia vaya a la sección Pastillero y añada una pastilla o a la sección Citas y añada una. \n\nToda la información que añada se guardará en las listas correspondientes y se creará una alarma automáticamente.\n\n\nNunca volverá a olvidarse de sus pastillas.";
    private TextView textoAyuda;

    @RequiresApi(api = Build.VERSION_CODES.R)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ayuda);

        textoAyuda = findViewById(R.id.textViewAyuda);
        textoAyuda.setText(mensaje);

        BottomNavigationView bottomNavigationView = findViewById(R.id.bottom_navigation);
        bottomNavigationView.setSelectedItemId(R.id.ayuda);

        FunctionsWhenClick functionsWhenClick = new FunctionsWhenClick();
        bottomNavigationView.setOnItemSelectedListener(x -> functionsWhenClick.getApply(x,getApplicationContext(),this));

    }
}
package com.pastillasCreator.pill_box.actividades;

import android.os.Build;
import android.os.Bundle;
import android.widget.TextView;

import androidx.annotation.RequiresApi;

import com.example.pill_box.R;
import com.pastillasCreator.pill_box.generic_activityes.DefaultActivity;

public class HelpActivity extends DefaultActivity {

    @RequiresApi(api = Build.VERSION_CODES.R)
    @Override
    public void onCreate(Bundle savedInstanceState) {
        selectedItem = R.id.ayuda;
        selectedContentView = R.layout.activity_ayuda;

        context = HelpActivity.this;

        super.onCreate(savedInstanceState);

        TextView helpingText = findViewById(R.id.textViewAyuda);
        String message = """
                ¡Gracias por instalar esta app!
                Para comenzar su experiencia vaya a la sección Pastillero y añada una pastilla o a la sección Citas y añada una.
                Toda la información que añada se guardará en las listas correspondientes y se creará una alarma automáticamente.
                Nunca volverá a olvidarse de sus pastillas.""";

        helpingText.setText(message);

    }
}
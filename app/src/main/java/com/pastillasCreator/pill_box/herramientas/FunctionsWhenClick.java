package com.pastillasCreator.pill_box.herramientas;

import android.content.Intent;
import android.os.Build;
import android.view.MenuItem;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import com.example.pill_box.R;
import com.pastillasCreator.pill_box.actividades.ActividadCitero;
import com.pastillasCreator.pill_box.actividades.ActividadMain;
import com.pastillasCreator.pill_box.actividades.ActividadPastillero;
import com.pastillasCreator.pill_box.actividades.Ayuda;

import java.util.Map;

public class FunctionsWhenClick extends AppCompatActivity {

    private final Map<Integer, Class<? extends AppCompatActivity>> elements;

    @RequiresApi(api = Build.VERSION_CODES.R)
    public FunctionsWhenClick() {
        elements = Map.of(
                R.id.home, ActividadMain.class,
                R.id.pastillero, ActividadPastillero.class,
                R.id.calendario, ActividadCitero.class,
                R.id.ayuda, Ayuda.class,
                R.id.citas, ActividadCitero.class
        );
    }


    @RequiresApi(api = Build.VERSION_CODES.R)
    public Boolean getApply(MenuItem menuItem) {
        Integer menuId = menuItem.getItemId();
        Object o = elements.computeIfPresent(menuId, (id, clase) -> {
            startActivity(new Intent(getApplicationContext(), clase));
            overridePendingTransition(0, 0);
            return clase;
        });
        return o != null;
    }
}

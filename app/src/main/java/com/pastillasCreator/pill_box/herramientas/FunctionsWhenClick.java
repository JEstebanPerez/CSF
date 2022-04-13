package com.pastillasCreator.pill_box.herramientas;

import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.view.MenuItem;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import com.example.pill_box.R;
import com.pastillasCreator.pill_box.actividades.ActividadCalendario;
import com.pastillasCreator.pill_box.actividades.ActividadCitero;
import com.pastillasCreator.pill_box.actividades.ActividadMain;
import com.pastillasCreator.pill_box.actividades.ActividadPastillero;
import com.pastillasCreator.pill_box.actividades.Ayuda;

import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

public class FunctionsWhenClick extends AppCompatActivity {

    private final Map<Integer,Class<? extends AppCompatActivity>> elements = new HashMap<>();

    @RequiresApi(api = Build.VERSION_CODES.R)
    public FunctionsWhenClick() {
        elements.put(R.id.home, ActividadMain.class);
        elements.put(R.id.pastillero, ActividadPastillero.class);
        elements.put(R.id.calendario, ActividadCalendario.class);
        elements.put(R.id.ayuda, Ayuda.class);
        elements.put(R.id.citas, ActividadCitero.class);
    }

    @RequiresApi(api = Build.VERSION_CODES.R)
    public Boolean getApply(MenuItem menuItem, Context applicationContext,AppCompatActivity activity ) {
        Integer menuId = menuItem.getItemId();
        Object o = elements.computeIfPresent(menuId, (id, clase) -> {
            activity.startActivity(new Intent(applicationContext, clase));
            activity.overridePendingTransition(0, 0);
            return clase;
        });
        return o != null;
    }


}

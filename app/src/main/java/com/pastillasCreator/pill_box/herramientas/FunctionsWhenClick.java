package com.pastillasCreator.pill_box.herramientas;

import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.view.MenuItem;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import com.example.pill_box.R;
import com.pastillasCreator.pill_box.actividades.CalendarActivity;
import com.pastillasCreator.pill_box.actividades.AppointmentAccumulatorActivity;
import com.pastillasCreator.pill_box.actividades.MainActivity;
import com.pastillasCreator.pill_box.actividades.PillAccumulatorActivity;
import com.pastillasCreator.pill_box.actividades.HelpActivity;

import java.util.HashMap;
import java.util.Map;

public class FunctionsWhenClick extends AppCompatActivity {

    private static final Map<Integer,Class<? extends AppCompatActivity>> elements = new HashMap<>();

    @RequiresApi(api = Build.VERSION_CODES.R)
    public static Boolean getApply(MenuItem menuItem, Context applicationContext,AppCompatActivity activity ) {
        elements.put(R.id.home, MainActivity.class);
        elements.put(R.id.pastillero, PillAccumulatorActivity.class);
        elements.put(R.id.calendario, CalendarActivity.class);
        elements.put(R.id.ayuda, HelpActivity.class);
        elements.put(R.id.citas, AppointmentAccumulatorActivity.class);
        Integer menuId = menuItem.getItemId();
        Object o = elements.computeIfPresent(menuId, (id, clase) -> {
            activity.startActivity(new Intent(applicationContext, clase));
            activity.overridePendingTransition(0, 0);
            return clase;
        });
        return o != null;
    }


}

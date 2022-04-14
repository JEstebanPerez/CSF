package com.pastillasCreator.pill_box.actividades;


import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListAdapter;
import android.widget.ListView;

import androidx.annotation.RequiresApi;

import com.example.pill_box.R;
import com.pastillasCreator.pill_box.almacenaje.PillAccumulator;
import com.pastillasCreator.pill_box.elementosCalendario.CalendarElement;
import com.pastillasCreator.pill_box.elementosCalendario.Pill;

import java.util.List;
import java.util.stream.Collectors;

public class PillShower extends DefaultActivity {

    @RequiresApi(api = Build.VERSION_CODES.R)
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.selectedItem = R.id.calendario;
        super.selectedContentView = R.layout.activity_mostrador_pastillas;
        super.onCreate(savedInstanceState);

        Intent incomingIntent = getIntent();
        String day = incomingIntent.getStringExtra("dia");
        String date = incomingIntent.getStringExtra("fecha");

        ListView listView = findViewById(R.id.listViewPastillas);
        PillAccumulator pillAccumulator = PillAccumulator.getPillAccumulator();
        List<Pill> pillList = pillAccumulator.getAllElements();

        List<String> nameList = pillList.stream()
                .filter(x -> x.getDayOfWeekList()[Integer.parseInt(day)])
                .map(CalendarElement::getName).collect(Collectors.toList());

        int layout = android.R.layout.simple_list_item_1;
        ListAdapter arrayAdapter=new ArrayAdapter<>(this,layout,nameList);

        listView.setAdapter(arrayAdapter);
        listView.setOnItemClickListener((adapterView, view, position, id) -> {
            Intent i = new Intent(PillShower.this, InfoActivity.class);
            i.putExtra("nombre",pillList.get(position).getName());
            i.putExtra("descripcion",pillList.get(position).getDescription());
            i.putExtra("total",String.valueOf(pillList.get(position).getTotal()));
            i.putExtra("tipo",String.valueOf(pillList.get(position).getType()));
            i.putExtra("fecha", date);
            i.putExtra("hora", pillList.get(position).getHour());
            i.putExtra("posicion", String.valueOf(position));
            startActivity(i);
        });

    }

}
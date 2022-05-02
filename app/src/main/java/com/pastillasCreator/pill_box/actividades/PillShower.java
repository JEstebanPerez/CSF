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
import com.pastillasCreator.pill_box.elementosCalendario.Pill;
import com.pastillasCreator.pill_box.generic_activityes.DefaultActivity;

import java.util.List;
import java.util.stream.Collectors;

public class PillShower extends DefaultActivity {

    @RequiresApi(api = Build.VERSION_CODES.R)
    @Override
    public void onCreate(Bundle savedInstanceState) {
        selectedItem = R.id.calendario;
        selectedContentView = R.layout.activity_mostrador_pastillas;

        context = PillShower.this;

        super.onCreate(savedInstanceState);

        Intent incomingIntent = getIntent();
        String day = incomingIntent.getStringExtra("dia");

        ListView listView = findViewById(R.id.listViewPastillas);
        PillAccumulator pillAccumulator = PillAccumulator.getPillAccumulator();

        List<Pill> pillList = pillAccumulator.getAllElements().stream()
                .filter(x -> x.getDayOfWeekList()[Integer.parseInt(day)])
                .collect(Collectors.toList());

        List<String> nameList = pillList.stream().map(Pill::getName).collect(Collectors.toList());

        int layout = android.R.layout.simple_list_item_1;
        ListAdapter arrayAdapter=new ArrayAdapter<>(this,layout,nameList);

        listView.setAdapter(arrayAdapter);
        listView.setOnItemClickListener((adapterView, view, position, id) -> {
            Pill pill = pillList.get(position);
            Intent i = new Intent(PillShower.this, InfoActivity.class);
            i.putExtra("pill",pill);
            startActivity(i);
        });

    }

}
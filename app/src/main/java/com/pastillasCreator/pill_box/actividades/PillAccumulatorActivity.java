package com.pastillasCreator.pill_box.actividades;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListAdapter;
import android.widget.ListView;

import androidx.annotation.RequiresApi;

import com.example.pill_box.R;
import com.pastillasCreator.pill_box.almacenaje.PillAccumulator;

import java.util.List;

public class PillAccumulatorActivity extends DefaultActivity {

    @RequiresApi(api = Build.VERSION_CODES.R)
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.selectedItem = R.id.pastillero;
        super.selectedContentView = R.layout.activity_pastillero;
        super.onCreate(savedInstanceState);

        listViewer();
    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    private void listViewer() {
        PillAccumulator pillAccumulator = PillAccumulator.getPillAccumulator();
        List<String> nameList = pillAccumulator.getNameListOfElements();

        ListView listView = findViewById(R.id.listaPastillas);

        ListAdapter arrayAdapter = new ArrayAdapter<>(this,android.R.layout.simple_list_item_1,nameList);

        listView.setAdapter(arrayAdapter);
        listView.setOnItemClickListener((adapterView, view, position, l) -> {
            Intent i = new Intent(PillAccumulatorActivity.this, PillEditorActivity.class);
            i.putExtra("posicion", Integer.toString(position));
            startActivity(i);
        });
    }

    public void addPillView(View view) {
        startActivity(new Intent(getApplicationContext(), PillCreatorActivity.class));
    }
}
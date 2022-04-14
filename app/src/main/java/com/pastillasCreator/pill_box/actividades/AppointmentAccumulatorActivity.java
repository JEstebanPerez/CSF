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
import com.pastillasCreator.pill_box.almacenaje.AppointmentAccumulator;

import java.util.List;

public class AppointmentAccumulatorActivity extends DefaultActivity {

    @RequiresApi(api = Build.VERSION_CODES.R)
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.selectedItem = R.id.citas;
        super.selectedContentView = R.layout.activity_citero;
        super.onCreate(savedInstanceState);

        viewList();
    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    private void viewList() {
        AppointmentAccumulator appointmentAccumulator = AppointmentAccumulator.getAppointmentAccumulator();
        List<String> nameList = appointmentAccumulator.getNameListOfElements();

        ListView listView = findViewById(R.id.listCita);
        int layout = android.R.layout.simple_list_item_1;

        ListAdapter arrayAdapter= new ArrayAdapter<>(this,layout,nameList);

        listView.setAdapter(arrayAdapter);
        listView.setOnItemClickListener((parent, view, position, id) -> {
            Intent i = new Intent(AppointmentAccumulatorActivity.this, AppointmentEditorActivity.class);
            i.putExtra("posicion", Integer.toString(position));
            startActivity(i);
        });
    }

    public void addAppointment(View view) {
        startActivity(new Intent(getApplicationContext(), AppointmentCreatorActivity.class));
    }
}
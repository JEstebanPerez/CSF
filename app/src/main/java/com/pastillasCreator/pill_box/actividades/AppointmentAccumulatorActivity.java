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
import com.pastillasCreator.pill_box.generic_activityes.AccumulatorActivity;
import com.pastillasCreator.pill_box.generic_activityes.DefaultActivity;

import java.util.List;

public class AppointmentAccumulatorActivity extends AccumulatorActivity {

    @RequiresApi(api = Build.VERSION_CODES.R)
    @Override
    public void onCreate(Bundle savedInstanceState) {
        selectedItem = R.id.citas;
        selectedContentView = R.layout.activity_citero;
        context = AppointmentAccumulatorActivity.this;

        accumulator = AppointmentAccumulator.getAccumulator();
        listId = R.id.listCita;
        editorClass = AppointmentEditorActivity.class;
        creatorClass = AppointmentCreatorActivity.class;

        super.onCreate(savedInstanceState);
    }



}
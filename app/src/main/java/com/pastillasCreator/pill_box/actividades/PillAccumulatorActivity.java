package com.pastillasCreator.pill_box.actividades;

import android.os.Build;
import android.os.Bundle;

import androidx.annotation.RequiresApi;

import com.example.pill_box.R;
import com.pastillasCreator.pill_box.almacenaje.PillAccumulator;
import com.pastillasCreator.pill_box.generic_activityes.AccumulatorActivity;

public class PillAccumulatorActivity extends AccumulatorActivity {

    @RequiresApi(api = Build.VERSION_CODES.R)
    @Override
    public void onCreate(Bundle savedInstanceState) {
        selectedItem = R.id.pastillero;
        selectedContentView = R.layout.activity_pastillero;

        accumulator = PillAccumulator.getPillAccumulator();
        listId = R.id.listaPastillas;

        editorClass = PillEditorActivity.class;
        creatorClass = PillCreatorActivity.class;

        context =PillAccumulatorActivity.this;

        super.onCreate(savedInstanceState);
    }
}
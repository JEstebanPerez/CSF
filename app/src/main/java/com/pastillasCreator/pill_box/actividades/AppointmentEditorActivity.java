package com.pastillasCreator.pill_box.actividades;


import android.os.Build;
import android.os.Bundle;

import androidx.annotation.RequiresApi;

import com.example.pill_box.R;
import com.pastillasCreator.pill_box.generic_activityes.AppointmentBuilderActivity;

public class AppointmentEditorActivity extends AppointmentBuilderActivity {

    @RequiresApi(api = Build.VERSION_CODES.R)
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.selectedItem = R.id.citas;
        super.selectedContentView = R.layout.activity_editar_citas;

        activityNameEditTextId = R.id.nombrecitaEdit;
        activityDescriptionEditTextId = R.id.DescripCitaEdit;
        activityHourTextId = R.id.horaCitaEdit;
        activityDateTextId = R.id.fechaCitaEdit;

        context = AppointmentEditorActivity.this;

        super.onCreate(savedInstanceState);
    }

}
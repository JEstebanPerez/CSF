package com.pastillasCreator.pill_box.actividades;

import android.os.Build;
import android.os.Bundle;

import androidx.annotation.RequiresApi;

import com.example.pill_box.R;
import com.pastillasCreator.pill_box.generic_activityes.AppointmentBuilderActivity;

public class AppointmentCreatorActivity extends AppointmentBuilderActivity {

    @RequiresApi(api = Build.VERSION_CODES.R)
    @Override
    public void onCreate(Bundle savedInstanceState) {
        selectedItem = R.id.citas;
        selectedContentView = R.layout.activity_citas_creator;
        context = AppointmentCreatorActivity.this;

        activityNameEditTextId = R.id.nombreCita;
        activityDescriptionEditTextId = R.id.descripcionCita;
        activityHourTextId = R.id.horaCita;
        activityDateTextId = R.id.fechaView;

        super.onCreate(savedInstanceState);
    }

}


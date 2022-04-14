package com.pastillasCreator.pill_box.actividades;


import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import androidx.annotation.RequiresApi;

import com.example.pill_box.R;
import com.pastillasCreator.pill_box.almacenaje.AppointmentAccumulator;
import com.pastillasCreator.pill_box.elementosCalendario.Appointment;
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

    public void removeAppointment(View view) {
        Toast.makeText(AppointmentEditorActivity.this, "Cita borrada correctamente", Toast.LENGTH_LONG).show();


        accumulator.removeElement(elementToUpdate);

        Intent i = new Intent(this, AppointmentAccumulatorActivity.class);
        startActivity(i);

    }

}
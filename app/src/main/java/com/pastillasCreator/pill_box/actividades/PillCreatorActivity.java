package com.pastillasCreator.pill_box.actividades;

import android.os.Build;
import android.os.Bundle;

import androidx.annotation.RequiresApi;

import com.example.pill_box.R;
import com.pastillasCreator.pill_box.generic_activityes.PillBuilderActivity;

public class PillCreatorActivity extends PillBuilderActivity {

    @RequiresApi(api = Build.VERSION_CODES.R)
    @Override
    public void onCreate(Bundle savedInstanceState) {

        activityNameEditTextId = R.id.nombrePastilla;
        activityDescriptionEditTextId = R.id.descripcionPastilla;
        activityTotalEditTextId = R.id.totalPastilla;
        activityHourTextId = R.id.horaPastillaView;
        spinnerId = R.id.spinnerTipoPastilla;
        weekListTextViewId = R.id.listaSemana;


        selectedItem = R.id.pastillero;
        selectedContentView = R.layout.activity_pastillas_creator;

        context = PillCreatorActivity.this;

        super.onCreate(savedInstanceState);
    }


}

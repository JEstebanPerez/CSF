package com.pastillasCreator.pill_box.actividades;

import android.os.Build;
import android.os.Bundle;
import android.widget.TextView;

import androidx.annotation.RequiresApi;

import com.example.pill_box.R;
import com.pastillasCreator.pill_box.generic_activityes.PillBuilderActivity;

import java.util.stream.IntStream;

public class PillEditorActivity extends PillBuilderActivity {

    private StringBuilder textList;
    @RequiresApi(api = Build.VERSION_CODES.R)
    @Override
    public void onCreate(Bundle savedInstanceState) {
        selectedItem = R.id.pastillero;
        selectedContentView = R.layout.activity_editar_pastilla;

        activityNameEditTextId = R.id.nombrePastillaEdit;
        activityDescriptionEditTextId = R.id.DescripcionPastillaEdit;
        activityTotalEditTextId = R.id.TotalPastillaEdit;
        activityHourTextId = R.id.HoraPastillaEdit;
        spinnerId = R.id.spinnerPastillaEdit;
        weekListTextViewId = R.id.listaSemanaPastillaEdit;

        context = PillEditorActivity.this;

        super.onCreate(savedInstanceState);

        TextView tip = findViewById(R.id.tipoPastillaEdit);

        String total = elementToUpdate.getTotal()+"";
        selectedDays = elementToUpdate.getDayOfWeekList();
        type = elementToUpdate.getType();

        activityTotalEditText.setText(total);

        tip.setText(type.name());
        setWeekListText();
        weekListTextView.setText(textList);
    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    private void setWeekListText() {
        if (elementToUpdate.getDayOfWeekList() != null) {
            StringBuilder stringBuilder = new StringBuilder();
            boolean[] days = elementToUpdate.getDayOfWeekList();

            IntStream.range(0, days.length)
                    .filter(x -> days[x])
                    .mapToObj(x -> possibleDays[x] + ", ")
                    .forEach(stringBuilder::append);
            textList = stringBuilder;
        }
    }

}
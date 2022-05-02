package com.pastillasCreator.pill_box.generic_activityes;

import android.app.AlertDialog;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.SpinnerAdapter;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.RequiresApi;

import com.pastillasCreator.pill_box.actividades.PillAccumulatorActivity;
import com.pastillasCreator.pill_box.almacenaje.PillAccumulator;
import com.pastillasCreator.pill_box.creadorElementosCalendario.PillCreator;
import com.pastillasCreator.pill_box.elementosCalendario.Pill;
import com.pastillasCreator.pill_box.elementosCalendario.PillType;
import com.pastillasCreator.pill_box.exceptions.CreatorException;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public abstract class PillBuilderActivity extends CreatorActivity<Pill> {

    protected EditText activityTotalEditText;
    protected Spinner spinner;
    protected TextView weekListTextView;

    protected Integer activityTotalEditTextId;
    protected Integer spinnerId;
    protected Integer weekListTextViewId;


    protected boolean[] selectedDays;
    protected final ArrayList<Integer> dayList = new ArrayList<>();
    protected final String[] possibleDays = {"Lunes", "Martes", "Miércoles", "Jueves", "Viernes", "Sábado", "Domingo"};
    protected PillType type;


    @RequiresApi(api = Build.VERSION_CODES.R)
    @Override
    public void onCreate(Bundle savedInstanceState) {

        accumulator = PillAccumulator.getPillAccumulator();
        menu = PillAccumulatorActivity.class;
        super.onCreate(savedInstanceState);

        activityTotalEditText = findViewById(activityTotalEditTextId);
        spinner = findViewById(spinnerId);
        weekListTextView = findViewById(weekListTextViewId);

        configureSpinner();
        configureWeekList();
    }

    protected void configureWeekList() {
        selectedDays = new boolean[possibleDays.length];
        weekListTextView.setOnClickListener(v -> {
            AlertDialog.Builder builder = new AlertDialog.Builder(this);

            builder.setTitle("Seleccionar día");
            builder.setCancelable(true);
            builder.setMultiChoiceItems(possibleDays, selectedDays, (dialog, which, isChecked) -> {
                if (isChecked) {
                    dayList.add(which);
                    Collections.sort(dayList);
                } else {
                    dayList.remove(which);
                }
            });
            builder.setPositiveButton("OK", (dialog, which) -> {
                StringBuilder stringBuilder = new StringBuilder();
                for (int j = 0; j < dayList.size(); j++) {
                    stringBuilder.append(possibleDays[dayList.get(j)]);
                    if (j != dayList.size() - 1) {
                        stringBuilder.append(", ");
                    }
                }
                weekListTextView.setText(stringBuilder.toString());
            });

            builder.setNegativeButton("Cancelar", (dialog, which) -> dialog.dismiss());
            builder.setNeutralButton("Limpiar", (dialog, which) -> {
                dayList.clear();
                weekListTextView.setText("");
                Arrays.fill(selectedDays, false);
            });
            builder.show();
        });
    }

    protected void configureSpinner() {
        List<PillType> pillTypeList = Arrays.asList(PillType.values());

        int layout = android.R.layout.simple_spinner_dropdown_item;
        SpinnerAdapter adapter = new ArrayAdapter<>(getApplicationContext(), layout, pillTypeList);
        spinner.setAdapter(adapter);

        type = type == null ? (PillType) spinner.getAdapter().getItem(0) : type;
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                type = (PillType) spinner.getAdapter().getItem(position);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {}
        });
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    public void saveNewItem(View view) {
        super.readFromViews();
        String quantity = activityTotalEditText.getText().toString();
        try{
            int number = Integer.parseInt(quantity);
            elementCreator = new PillCreator(name, description, number, hour, selectedDays, type);
        }catch (NumberFormatException e){
            Toast.makeText(this, String.format(CreatorException.NOT_VALID_VALUE, "quantity"),
                    Toast.LENGTH_SHORT).show();
            return;
        }
        super.saveNewItem(view);
    }

}

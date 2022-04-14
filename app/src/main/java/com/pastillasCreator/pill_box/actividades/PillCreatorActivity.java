package com.pastillasCreator.pill_box.actividades;

import android.app.AlertDialog;
import android.content.Intent;
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

import com.example.pill_box.R;
import com.pastillasCreator.pill_box.creadorElementosCalendario.PillCreator;
import com.pastillasCreator.pill_box.elementosCalendario.PillType;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class PillCreatorActivity extends DefaultActivity {

    private EditText activityNameEditText;
    private EditText activityTotalEditText;
    private EditText activityDescriptionEditText;
    private Spinner spinner;
    private TextView hourTextView;

    private TextView weekTextView;
    private boolean[] selectedDaysArray;
    private PillType pillType;
    private final ArrayList<Integer> dayList = new ArrayList<>();
    private final String[] weekDaysNames = {"Lunes", "Martes", "Miércoles", "Jueves", "Viernes", "Sábado", "Domingo"};

    private String hour="";


    @RequiresApi(api = Build.VERSION_CODES.R)
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.selectedItem = R.id.pastillero;
        super.selectedContentView = R.layout.activity_pastillas_creator;
        super.onCreate(savedInstanceState);

        activityNameEditText = findViewById(R.id.nombrePastilla);
        activityTotalEditText = findViewById(R.id.totalPastilla);
        activityDescriptionEditText = findViewById(R.id.descripcionPastilla);
        spinner = findViewById(R.id.spinnerTipoPastilla);

        weekTextView = findViewById(R.id.listaSemana);
        hourTextView = findViewById(R.id.horaPastillaView);



        configureSpinner();

        configureWeekList();
    }

    private void configureWeekList() {
        selectedDaysArray = new boolean[weekDaysNames.length];
        weekTextView.setOnClickListener(v -> {
            AlertDialog.Builder builder = new AlertDialog.Builder(this);

            builder.setTitle("Seleccionar día");
            builder.setCancelable(true);
            builder.setMultiChoiceItems(weekDaysNames, selectedDaysArray, (dialog, which, isChecked) -> {
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
                    stringBuilder.append(weekDaysNames[dayList.get(j)]);
                    if (j != dayList.size() - 1) {
                        stringBuilder.append(", ");
                    }
                }
                weekTextView.setText(stringBuilder.toString());
            });

            builder.setNegativeButton("Cancelar", (dialog, which) -> dialog.dismiss());
            builder.setNeutralButton("Limpiar", (dialog, which) -> {
                for (int j = 0; j < selectedDaysArray.length; j++) {
                    selectedDaysArray[j] = false;
                    dayList.clear();
                    weekTextView.setText("");
                }
            });
            builder.show();
        });
    }

    private void configureSpinner() {
        List<PillType> listaPastillas = Arrays.asList(PillType.values());

        int layout = android.R.layout.simple_spinner_dropdown_item;
        SpinnerAdapter adapter = new ArrayAdapter<>(PillCreatorActivity.this, layout, listaPastillas);

        spinner.setAdapter(adapter);
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                pillType = (PillType) spinner.getAdapter().getItem(position);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                pillType = (PillType) spinner.getAdapter().getItem(0);
            }
        });
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    public void saveNotEmptyElements(View view) {
        String name = activityNameEditText.getText().toString();
        String quantity = activityTotalEditText.getText().toString();
        String description = activityDescriptionEditText.getText().toString();
        try{
            int number = Integer.parseInt(quantity);
            PillCreator pillCreator = new PillCreator(name, description, number,selectedDaysArray,pillType);
            pillCreator.saveIfPossible();
            startActivity(new Intent(getApplicationContext(), PillAccumulatorActivity.class));
        } catch(Exception e){
            Toast.makeText(this, e.getMessage(), Toast.LENGTH_SHORT).show();
        }
    }


}

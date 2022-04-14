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
import com.pastillasCreator.pill_box.almacenaje.PillAccumulator;
import com.pastillasCreator.pill_box.creadorElementosCalendario.PillCreator;
import com.pastillasCreator.pill_box.elementosCalendario.Pill;
import com.pastillasCreator.pill_box.elementosCalendario.PillType;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.IntStream;

public class PillEditorActivity extends DefaultActivity {

    private EditText nameEditText;
    private EditText descriptionEditText;
    private EditText totalEditText;
    private TextView hourTextView;
    private Spinner spinner;
    int position;

    // Atributos para el multiseleccionar día de la semana
    private TextView weekListTextView;
    private boolean[] selectedDays;
    private final ArrayList<Integer> dayList = new ArrayList<>();
    private final String[] possibleDays = {"Lunes", "Martes", "Miércoles", "Jueves", "Viernes", "Sábado", "Domingo"};
    private boolean[] dayOfWeekList;
    private StringBuilder textList;

    private Pill pill;

    @RequiresApi(api = Build.VERSION_CODES.R)
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.selectedItem = R.id.pastillero;
        super.selectedContentView = R.layout.activity_editar_pastilla;
        super.onCreate(savedInstanceState);

        nameEditText = findViewById(R.id.nombrePastillaEdit);
        descriptionEditText = findViewById(R.id.DescripcionPastillaEdit);
        totalEditText = findViewById(R.id.TotalPastillaEdit);
        hourTextView = findViewById(R.id.HoraPastillaEdit);
        TextView tip = findViewById(R.id.tipoPastillaEdit);
        spinner = findViewById(R.id.spinnerPastillaEdit);
        weekListTextView = findViewById(R.id.listaSemanaPastillaEdit);

        Intent incomingIntent = getIntent();
        position = Integer.parseInt(incomingIntent.getStringExtra("posicion"));

        PillAccumulator pillAccumulator = PillAccumulator.getPillAccumulator();
        pill = pillAccumulator.getElement(position);

        nameEditText.setText(pill.getName());
        descriptionEditText.setText(pill.getDescription());
        String total = Integer.toString(pill.getTotal());
        totalEditText.setText(total);
        String hora = "Hora:" + pill.getHour();
        hourTextView.setText(hora);
        tip.setText(pill.getType().toString());
        setWeekListText();
        weekListTextView.setText(textList);

        configureSpinner();
        configuracionListaSemana();
    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    private void configuracionListaSemana() {
        if (pill.getDayOfWeekList() != null) {
            selectedDays = pill.getDayOfWeekList();
        } else {
            selectedDays = new boolean[possibleDays.length];
        }
        weekListTextView.setOnClickListener(v -> {
            AlertDialog.Builder builder = new AlertDialog.Builder(PillEditorActivity.this);

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
                String text = dayList.stream()
                        .map(x -> possibleDays[x] + ", ")
                        .reduce(String::concat).orElse("  ");
                int length = text.length();
                weekListTextView.setText(text.substring(length - 2));
            });
            builder.setNegativeButton("Cancelar", (dialog, which) -> dialog.dismiss());
            builder.setNeutralButton("Limpiar", (dialog, which) -> {
                IntStream.range(0, selectedDays.length)
                        .forEach(x -> selectedDays[x] = false);
                dayList.clear();
                weekListTextView.setText("");
            });
            builder.show();
        });
    }

    private void configureSpinner() {
        ArrayList<PillType> pillTypes = new ArrayList<>(Arrays.asList(PillType.values()));

        int layout = android.R.layout.simple_spinner_dropdown_item;
        SpinnerAdapter spinnerAdapter = new ArrayAdapter<>(PillEditorActivity.this,layout, pillTypes);

        spinner.setAdapter(spinnerAdapter);
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                spinner.getAdapter().getItem(position);
            }
            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    public void saveNotEmptyElements(View view) {
        String name = nameEditText.getText().toString();
        String quantity = totalEditText.getText().toString();
        String description = descriptionEditText.getText().toString();
        try{
            int number = Integer.parseInt(quantity);
            PillCreator pillCreator = new PillCreator(name, description, number,selectedDays,pill.getType());
            pillCreator.saveIfPossible();
            startActivity(new Intent(getApplicationContext(), PillAccumulatorActivity.class));
        } catch(Exception e){
            //TODO
        }
    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    private void setWeekListText() {
        if (pill.getDayOfWeekList() != null) {
            StringBuilder stringBuilder = new StringBuilder();
            boolean[] days = pill.getDayOfWeekList();

            IntStream.range(0, days.length)
                    .filter(x -> days[x])
                    .mapToObj(x -> possibleDays[x] + ", ")
                    .forEach(stringBuilder::append);
            textList = stringBuilder;
        }
    }


    public void removePill(View view) {

        PillAccumulator pillAccumulator = PillAccumulator.getPillAccumulator();
        List<Pill> pillList = pillAccumulator.getAllElements();

        pillList.remove(position);

        Intent i = new Intent(this, PillAccumulatorActivity.class);
        startActivity(i);
        Toast.makeText(PillEditorActivity.this, "Pastilla borrada correctamente", Toast.LENGTH_LONG).show();

    }

}
package com.pastillasCreator.pill_box.actividades;

import static com.pastillasCreator.pill_box.almacenaje.Pastillero.getPastillero;

import android.app.AlertDialog;
import android.app.TimePickerDialog;
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
import androidx.appcompat.app.AppCompatActivity;

import com.example.pill_box.R;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.pastillasCreator.pill_box.almacenaje.Pastillero;
import com.pastillasCreator.pill_box.elementosCalendario.Pastilla;
import com.pastillasCreator.pill_box.elementosCalendario.TipoPastilla;
import com.pastillasCreator.pill_box.herramientas.FunctionsWhenClick;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Collections;
import java.util.List;
import java.util.stream.IntStream;

public class EditarPastilla extends AppCompatActivity {

    private EditText nombr;
    private EditText descrip;
    private EditText tot;
    private TextView hor;
    private Spinner spinner;
    int posicion;

    // Atributos para el multiseleccionar día de la semana
    private TextView listaSemana;
    private boolean[] diaSeleccionados;
    private final ArrayList<Integer> listaDia = new ArrayList<>();
    private final String[] diaArray = {"Lunes", "Martes", "Miércoles", "Jueves", "Viernes", "Sábado", "Domingo"};
    private boolean[] dayOfWeekList;
    private StringBuilder textoLista;

    Pastilla pastilla;
    Pastilla pastillaCopia;

    @RequiresApi(api = Build.VERSION_CODES.R)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_editar_pastilla);


        nombr = findViewById(R.id.nombrePastillaEdit);
        descrip = findViewById(R.id.DescripcionPastillaEdit);
        tot = findViewById(R.id.TotalPastillaEdit);
        hor = findViewById(R.id.HoraPastillaEdit);
        TextView tip = findViewById(R.id.tipoPastillaEdit);
        spinner = findViewById(R.id.spinnerPastillaEdit);
        listaSemana = findViewById(R.id.listaSemanaPastillaEdit);

        Intent incomingIntent = getIntent();
        posicion = Integer.parseInt(incomingIntent.getStringExtra("posicion"));

        Pastillero pastillero = getPastillero();
        pastilla = pastillero.getPastilla(posicion);
        pastillaCopia = pastillero.getPastilla(posicion);

        nombr.setText(pastilla.getNombre());
        descrip.setText(pastilla.getDescripcion());
        String total = Integer.toString(pastilla.getTotal());
        tot.setText(total);
        String hora = "Hora:" + pastilla.getHora();
        hor.setText(hora);
        tip.setText(pastilla.getTipo().toString());
        setTextListaSemana();
        listaSemana.setText(textoLista);

        BottomNavigationView bottomNavigationView = findViewById(R.id.bottom_navigation);
        bottomNavigationView.setSelectedItemId(R.id.pastillero);

        FunctionsWhenClick functionsWhenClick = new FunctionsWhenClick();
        bottomNavigationView.setOnItemSelectedListener(functionsWhenClick::getApply);

        configuracionSpinner();
        configuracionListaSemana();
    }

    // Establece los datos que contendrá el desplegable multiseleccionable de lista de la semana
    @androidx.annotation.RequiresApi(api = Build.VERSION_CODES.N)
    private void configuracionListaSemana() {
        if (pastilla.getDayOfWeekList() != null) {
            diaSeleccionados = pastilla.getDayOfWeekList();
        } else {
            diaSeleccionados = new boolean[diaArray.length];
        }
        dayOfWeekList = getDiaSeleccionados();

        listaSemana.setOnClickListener(v -> {
            AlertDialog.Builder builder = new AlertDialog.Builder(EditarPastilla.this);

            builder.setTitle("Seleccionar día");
            builder.setCancelable(true);
            builder.setMultiChoiceItems(diaArray, diaSeleccionados, (dialog, which, isChecked) -> {
                if (isChecked) {
                    listaDia.add(which);
                    Collections.sort(listaDia);
                } else {
                    listaDia.remove(which);
                }
            });
            builder.setPositiveButton("OK", (dialog, which) -> {
                String texto = listaDia.stream()
                        .map(x -> diaArray[x] + ", ")
                        .reduce(String::concat).orElse("  ");
                int longitud = texto.length();
                listaSemana.setText(texto.substring(longitud - 2));
                dayOfWeekList = getDiaSeleccionados();
            });
            builder.setNegativeButton("Cancelar", (dialog, which) -> dialog.dismiss());
            builder.setNeutralButton("Limpiar", (dialog, which) -> {
                IntStream.range(0, diaSeleccionados.length)
                        .forEach(x -> diaSeleccionados[x] = false);
                listaDia.clear();
                listaSemana.setText("");
            });
            builder.show();
        });
    }

    private void configuracionSpinner() {
        ArrayList<TipoPastilla> listaPastillas = new ArrayList<>(Arrays.asList(TipoPastilla.values()));

        int layout = android.R.layout.simple_spinner_dropdown_item;
        SpinnerAdapter adaptador = new ArrayAdapter<>(EditarPastilla.this,layout, listaPastillas);

        spinner.setAdapter(adaptador);
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                spinner.getAdapter().getItem(position);
            }
            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                pastilla.getTipo();
            }
        });
    }

    public void abrirHora(View view) {
        Calendar calendar = Calendar.getInstance();
        int hora = calendar.get(Calendar.HOUR_OF_DAY);
        int min = calendar.get(Calendar.MINUTE);
        TimePickerDialog tmd = new TimePickerDialog(EditarPastilla.this, (timePicker, hourOfDay, minute) -> {
            String hora1 = "Hora:"+hourOfDay + ":" + minute;
            hor.setText(hora1);
            pastilla.setHora(hora1);
        }, hora, min, false); // Contexto, listener
        tmd.show(); // Si no ponemos la función show, no se va a mostrar
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    public void guardar(View view) {
        String nomPastilla = nombr.getText().toString();
        String num = tot.getText().toString();
        String desPastilla = descrip.getText().toString();
        if (nomPastilla.isEmpty()) {
            Toast.makeText(this, "Se necesita incluir un nombre.", Toast.LENGTH_SHORT).show();
            return;
        }
        pastilla.setNombre(nomPastilla);
        if (num.isEmpty()) {
            Toast.makeText(this, "Se necesita incluir una cantidad", Toast.LENGTH_SHORT).show();
            return;
        }
        int numPastilla = Integer.parseInt(num);
        if (!numeroAdecuado(numPastilla)) {
            String mensaje = mensajeNumeroAdecuado(numPastilla);
            Toast.makeText(this, mensaje, Toast.LENGTH_SHORT).show();
            return;
        }
        pastilla.setTotal(numPastilla);
        if (desPastilla.isEmpty()) {
            Toast.makeText(this, "Se necesita incluir una descripción.", Toast.LENGTH_SHORT).show();
            return;
        }
        pastilla.setDescripcion(desPastilla);
        Pastillero listado = getPastillero();
        int numOpcionales = contadorDatosOpcionales();
        if (numOpcionales == 1) {
            Toast.makeText(this, "Complete los datos opcionales.", Toast.LENGTH_SHORT).show();
            return;
        }
        if (numOpcionales == 2) {
            pastilla.setDayOfWeekList(this.getDayOfWeekList());
            pastilla.setHora(hor.getText().toString());
        }

        listado.addPastilla(pastilla);
        Toast.makeText(this, "Se modificó la pastilla: " + nomPastilla + " correctamente.", Toast.LENGTH_SHORT).show();
        startActivity(new Intent(getApplicationContext(), ActividadPastillero.class));
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    private int contadorDatosOpcionales() {
        boolean diasAnadidos;
        int cont=0;
        for(boolean aux:diaSeleccionados){
            diasAnadidos = aux;
            if(diasAnadidos){
                cont =1;
                break;
            }
        }

        return cont + (pastilla.getHora() != null ? 1 : 0);
    }

    private boolean numeroAdecuado(int numPastilla) {
        return (numPastilla >= 1) && (numPastilla <= 1000);
    }

    private String mensajeNumeroAdecuado(int numPastilla) {
        if (numPastilla < 1) {
            return "Se necesita un mínimo de 1 pastilla.";
        }
        if (numPastilla > 1000) {
            return "El máximo de pastillas es 1000.";
        }
        return "Número de pastillas incorrecto";
    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    private void setTextListaSemana() {
        if (pastilla.getDayOfWeekList() != null) {
            StringBuilder stringBuilder = new StringBuilder();
            boolean[] days = pastilla.getDayOfWeekList();

            IntStream.range(0, days.length)
                    .filter(x -> days[x])
                    .mapToObj(x -> diaArray[x] + ", ")
                    .forEach(stringBuilder::append);
            textoLista = stringBuilder;
        }
    }

    public boolean[] getDiaSeleccionados() {
        return diaSeleccionados;
    }

    public boolean[] getDayOfWeekList() {
        return dayOfWeekList;
    }

    public void borrarPastilla(View view) {

        Pastillero pastillero = Pastillero.getPastillero();
        List<Pastilla> listaPastillas = pastillero.getListaPastillas();

        listaPastillas.remove(posicion);

        Intent i = new Intent(this, ActividadPastillero.class);
        startActivity(i);
        Toast.makeText(EditarPastilla.this, "Pastilla borrada correctamente", Toast.LENGTH_LONG).show();

    }

}
package com.pastillasCreator.pill_box.creadorElementosCalendario;


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
import com.pastillasCreator.pill_box.actividades.ActividadPastillero;
import com.pastillasCreator.pill_box.almacenaje.Pastillero;
import com.pastillasCreator.pill_box.elementosCalendario.ElementoCalendario;
import com.pastillasCreator.pill_box.elementosCalendario.Pastilla;
import com.pastillasCreator.pill_box.elementosCalendario.TipoPastilla;
import com.pastillasCreator.pill_box.herramientas.FunctionsWhenClick;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Collections;
import java.util.List;

public class PastillasCreador extends AppCompatActivity implements CreadorElementosCalendario {

    // Atributos de la actividad
    private EditText nombreActividad;
    private EditText totalActividad;
    private EditText descripcionActividad;
    private Spinner spinner;
    private TextView textoHora;

    // Atributos para el multiseleccionar día de la semana
    private TextView listaSemana;
    private boolean[] diaSeleccionados;
    private final ArrayList<Integer> listaDia = new ArrayList<>();
    private final String[] diaArray = {"Lunes", "Martes", "Miércoles", "Jueves", "Viernes", "Sábado", "Domingo"};

    // Atributos para crear Pastilla
    private String name;
    private String description;
    private int total;
    private TipoPastilla tipo;
    private boolean[] dayOfWeekList;
    private String hora;

    // Se conecta los campos de texto con el código y el desplegable asigna qué valor tendrá al final el tipo de pastilla
    @RequiresApi(api = Build.VERSION_CODES.R)
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pastillas_creator);

        // Datos imprescindibles
        nombreActividad = findViewById(R.id.nombrePastilla);
        totalActividad = findViewById(R.id.totalPastilla);
        descripcionActividad = findViewById(R.id.descripcionPastilla);
        spinner = findViewById(R.id.spinnerTipoPastilla);

        // Datos opcionales
        listaSemana = findViewById(R.id.listaSemana);
        textoHora = findViewById(R.id.horaPastillaView);

        BottomNavigationView bottomNavigationView = findViewById(R.id.bottom_navigation);
        bottomNavigationView.setSelectedItemId(R.id.pastillero);

        FunctionsWhenClick functionsWhenClick = new FunctionsWhenClick();
        bottomNavigationView.setOnItemSelectedListener(x -> functionsWhenClick.getApply(x,getApplicationContext(),this));

        configuracionSpinner();

        configuracionListaSemana();
    }

    // Establece los datos que contendrá el desplegable multiseleccionable de lista de la semana
    private void configuracionListaSemana() {
        diaSeleccionados = new boolean[diaArray.length];
        listaSemana.setOnClickListener(v -> {
            AlertDialog.Builder builder = new AlertDialog.Builder(PastillasCreador.this);

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
                StringBuilder stringBuilder = new StringBuilder();
                for (int j = 0; j < listaDia.size(); j++) {
                    stringBuilder.append(diaArray[listaDia.get(j)]);
                    if (j != listaDia.size() - 1) {
                        stringBuilder.append(", ");
                    }
                }
                listaSemana.setText(stringBuilder.toString());
                dayOfWeekList = getDiaSeleccionados();
            });

            builder.setNegativeButton("Cancelar", (dialog, which) -> dialog.dismiss());
            builder.setNeutralButton("Limpiar", (dialog, which) -> {
                for (int j = 0; j < diaSeleccionados.length; j++) {
                    diaSeleccionados[j] = false;
                    listaDia.clear();
                    listaSemana.setText("");
                }
            });
            builder.show();
        });
    }

    // Establece los datos que contendrá el desplegable del tipo de pastilla
    private void configuracionSpinner() {
        List<TipoPastilla> listaPastillas = Arrays.asList(TipoPastilla.values());

        int layout = android.R.layout.simple_spinner_dropdown_item;
        SpinnerAdapter adaptador = new ArrayAdapter<>(PastillasCreador.this, layout, listaPastillas);

        spinner.setAdapter(adaptador);
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                TipoPastilla type = (TipoPastilla) spinner.getAdapter().getItem(position);
                setType(type);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                TipoPastilla type = (TipoPastilla) spinner.getAdapter().getItem(0);
                setType(type);
            }
        });
    }

    public void abrirHora(View view) {
        Calendar c = Calendar.getInstance();
        int hora = c.get(Calendar.HOUR_OF_DAY);
        int min = c.get(Calendar.MINUTE);
        TimePickerDialog tmd = new TimePickerDialog(PastillasCreador.this,
                (timePicker, hourOfDay, minute) -> {
                    String hora1 = "Hora:" + hourOfDay + ":" + minute;
                    textoHora.setText(hora1);
                    setHora(hora1);
                }, hora, min, false);
        tmd.show();
    }

    // Comprueba que los datos importantes no estén vacíos y si lo están impide que se guarden
    @RequiresApi(api = Build.VERSION_CODES.O)
    public void guardar(View view) {
        String nomPastilla = nombreActividad.getText().toString();
        String num = totalActividad.getText().toString();
        String desPastilla = descripcionActividad.getText().toString();
        int len = Toast.LENGTH_SHORT;
        List<String> mensajes = new ArrayList<>();

        if (nomPastilla.isEmpty()) {
            mensajes.add("Se necesita incluir un nombre.");
        }
        if (num.isEmpty()) {
            mensajes.add("Se necesita incluir una cantidad");
        }

        int numPastilla = Integer.parseInt(num);
        if (!numeroAdecuado(numPastilla)) {
            String mensaje = mensajeNumeroAdecuado(numPastilla);
            mensajes.add(mensaje);
        }

        if (desPastilla.isEmpty()) {
            mensajes.add("Se necesita incluir una descripción.");
        }

        if(!mensajes.isEmpty()){
            mensajes.forEach( x -> Toast.makeText(this,x,len).show());
        }

        setName(nomPastilla);
        setTotal(numPastilla);
        setDescription(desPastilla);
        Pastilla pastilla = (Pastilla) creadorElemento();
        Pastillero listado = getPastillero();
        int numOpcionales = contadorDatosOpcionales();
        if(numOpcionales == 1){
            Toast.makeText(this, "Complete los datos opcionales.", len).show();
        } else{
            if(numOpcionales ==2){
                pastilla.setDayOfWeekList(this.getDayOfWeekList());
                pastilla.setHora(hora);
            }
            boolean hasMensaje = listado.addPastilla(pastilla);
            String mensaje = hasMensaje ?
                    "Se guardó la pastilla: " + nomPastilla + " correctamente." :
                    "La pastilla " + nomPastilla + " ya se encuentra guardada.";
            Toast.makeText(this,mensaje, len).show();
            startActivity(new Intent(getApplicationContext(), ActividadPastillero.class));
        }
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

        return cont + (hora != null ? 1 : 0);
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

    @Override
    public ElementoCalendario creadorElemento() {
        return new Pastilla(getNombre(), getDescripcion(), getTotal(), getTipo());
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setTotal(int total) {
        this.total = total;
    }

    public void setType(TipoPastilla type) {
        this.tipo = type;
    }

    public String getNombre() {
        return name;
    }

    public String getDescripcion() {
        return description;
    }

    public int getTotal() {
        return total;
    }

    public TipoPastilla getTipo() {
        return tipo;
    }

    public boolean[] getDayOfWeekList() {
        return dayOfWeekList;
    }

    public boolean[] getDiaSeleccionados() {
        return diaSeleccionados;
    }

    public void setHora(String hora) {
        this.hora = hora;
    }
}
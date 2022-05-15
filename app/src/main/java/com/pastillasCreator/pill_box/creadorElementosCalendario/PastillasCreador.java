package com.pastillasCreator.pill_box.creadorElementosCalendario;


import static com.pastillasCreator.pill_box.almacenaje.Pastillero.getPastillero;

import android.app.AlertDialog;
import android.app.TimePickerDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;


import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import com.example.pill_box.R;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.pastillasCreator.pill_box.actividades.ActividadMain;
import com.pastillasCreator.pill_box.actividades.ActividadCalendario;
import com.pastillasCreator.pill_box.actividades.ActividadCitero;
import com.pastillasCreator.pill_box.actividades.ActividadPastillero;
import com.pastillasCreator.pill_box.actividades.Ayuda;
import com.pastillasCreator.pill_box.almacenaje.Pastillero;
import com.pastillasCreator.pill_box.elementosCalendario.ElementoCalendario;
import com.pastillasCreator.pill_box.elementosCalendario.Pastilla;
import com.pastillasCreator.pill_box.elementosCalendario.TipoPastilla;

import java.sql.Time;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;

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
    private ArrayList<Integer> listaDia = new ArrayList<>();
    private String[] diaArray = {"Lunes", "Martes", "Miércoles", "Jueves", "Viernes", "Sábado", "Domingo"};

    // Atributos para crear Pastilla
    private String name;
    private String description;
    private int total;
    private TipoPastilla tipo;
    private boolean[] dayOfWeekList;
    private Time[][] hourDayOfWeekList;
    private String hora;

    // Se conecta los campos de texto con el código y el desplegable asigna qué valor tendrá al final el tipo de pastilla
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
        listaSemana= findViewById(R.id.listaSemana);
        textoHora = findViewById(R.id.horaPastillaView);

        BottomNavigationView bottomNavigationView = findViewById(R.id.bottom_navigation);
        bottomNavigationView.setSelectedItemId(R.id.pastillero);

        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                switch (menuItem.getItemId()){

                    case R.id.home:
                        startActivity(new Intent(getApplicationContext(),
                                ActividadMain.class));
                        overridePendingTransition(0,0);
                        return true;
                    case R.id.pastillero:
                        startActivity(new Intent(getApplicationContext(),
                                ActividadPastillero.class));
                        overridePendingTransition(0,0);
                        return true;
                    case R.id.calendario:
                        startActivity(new Intent(getApplicationContext(),
                                ActividadCalendario.class));
                        overridePendingTransition(0,0);
                        return true;
                    case R.id.citas:
                        startActivity(new Intent(getApplicationContext(),
                                ActividadCitero.class));
                        overridePendingTransition(0,0);
                        return true;
                    case  R.id.ayuda:
                        startActivity(new Intent(getApplicationContext(),
                                Ayuda.class));
                        overridePendingTransition(0,0);
                        return true;
                }
                return false;
            }
        });

        configuracionSpinner();

        configuracionListaSemana();
    }

    // Establece los datos que contendrá el desplegable multiseleccionable de lista de la semana
    private void configuracionListaSemana() {
        diaSeleccionados = new boolean[diaArray.length];
        listaSemana.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder builder = new AlertDialog.Builder(PastillasCreador.this);

                builder.setTitle("Seleccionar día");
                builder.setCancelable(true);
                builder.setMultiChoiceItems(diaArray, diaSeleccionados, new DialogInterface.OnMultiChoiceClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which, boolean isChecked) {
                        if (isChecked){
                            listaDia.add(which);
                            Collections.sort(listaDia);
                        }else{
                            listaDia.remove(which);
                        }
                    }
                });
                builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        StringBuilder stringBuilder = new StringBuilder();
                        for(int j=0; j < listaDia.size(); j++){
                            stringBuilder.append(diaArray[listaDia.get(j)]);
                            if (j != listaDia.size()-1){
                                stringBuilder.append(", ");
                            }
                        }
                        listaSemana.setText(stringBuilder.toString());
                        dayOfWeekList = getDiaSeleccionados();
                    }
                });
                builder.setNegativeButton("Cancelar", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                });
                builder.setNeutralButton("Limpiar", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        for (int j=0; j<diaSeleccionados.length; j++){
                            diaSeleccionados[j] = false;
                            listaDia.clear();
                            listaSemana.setText("");
                        }
                    }
                });
                builder.show();
            }
        });
    }

    // Establece los datos que contendrá el desplegable del tipo de pastilla
    private void configuracionSpinner() {
        ArrayList<TipoPastilla> listaPastillas = new ArrayList<>();

        listaPastillas.add(TipoPastilla.valueOf("Pastilla"));
        listaPastillas.add(TipoPastilla.valueOf("Perla"));
        listaPastillas.add(TipoPastilla.valueOf("Capsula"));
        listaPastillas.add(TipoPastilla.valueOf("Efervescente"));

        ArrayAdapter adaptador = new ArrayAdapter(PastillasCreador.this, android.R.layout.simple_spinner_dropdown_item, listaPastillas);

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
        TimePickerDialog tmd = new TimePickerDialog(PastillasCreador.this, new TimePickerDialog.OnTimeSetListener() {
            @Override
            public void onTimeSet(TimePicker timePicker, int hourOfDay, int minute) {
                String hora = hourOfDay + ":" + minute;
                textoHora.setText("Hora" + ":" + hora);
                setHora(hora);
            }
        },hora, min, false); // Contexto, listener
        tmd.show(); // Si no ponemos la función show, no se va a mostrar
    }

    // Comprueba que los datos importantes no estén vacíos y si lo están impide que se guarden
    @RequiresApi(api = Build.VERSION_CODES.O)
    public void guardar (View view){
        String nomPastilla = nombreActividad.getText().toString();
        String num = totalActividad.getText().toString();
        String desPastilla = descripcionActividad.getText().toString();

        if (!nomPastilla.isEmpty()){
            setName(nomPastilla);
            if (!num.isEmpty()) {
                int numPastilla = Integer.parseInt(num);
                if (numeroAdecuado(numPastilla)) {
                    setTotal(numPastilla);
                    if (!desPastilla.isEmpty()) {
                        setDescription(desPastilla);
                        // Crea la pastilla, obtiene el listado de pastillas del sistema y añade la pastilla creada a la misma
                        Pastilla pastilla = (Pastilla) creadorElemento();
                        Pastillero listado = getPastillero();
                        int numOpcionales = contadorDatosOpcionales();
                        boolean correcto = false;
                        switch (numOpcionales) {
                            case 2:
                                pastilla.setDayOfWeekList(this.getDayOfWeekList());
                                pastilla.setHora(hora);
                                correcto = true;
                                break;
                            case 1:
                                Toast.makeText(this, "Complete los datos opcionales.", Toast.LENGTH_SHORT).show();
                                break;
                            case 0:
                                correcto = true;
                                break;
                        }
                        if (correcto) {
                            boolean mensaje = listado.addPastilla(pastilla);
                            if (mensaje) {
                                Toast.makeText(this, "Se guardó la pastilla: " + nomPastilla + " correctamente.", Toast.LENGTH_SHORT).show();
                            } else {
                                Toast.makeText(this, "La pastilla " + nomPastilla + " ya se encuentra guardada.", Toast.LENGTH_SHORT).show();
                            }
                            startActivity(new Intent(getApplicationContext(), ActividadPastillero.class));
                        }
                    } else {
                        Toast.makeText(this, "Se necesita incluir una descripción.", Toast.LENGTH_SHORT).show();
                    }
                } else {
                    String mensaje = mensajeNumeroAdecuado(numPastilla);
                    Toast.makeText(this, mensaje, Toast.LENGTH_SHORT).show();
                }
            }else{
                Toast.makeText(this, "Se necesita incluir una cantidad", Toast.LENGTH_SHORT).show();
            }
        }else{
            Toast.makeText(this, "Se necesita incluir un nombre.", Toast.LENGTH_SHORT).show();
        }

    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    private int contadorDatosOpcionales() {
        boolean diasAnadidos = false;
        int cont = 0;
        int i = 0;
        while(!diasAnadidos && i < diaSeleccionados.length) {
            diasAnadidos = diaSeleccionados[i];
            i++;
        }
        boolean horaAnadida = hora != null;
        if (diasAnadidos) cont++;
        if (horaAnadida) cont++;
        return cont;
    }

    private boolean numeroAdecuado(int numPastilla) {
        return (numPastilla >= 1) && (numPastilla <= 1000);
    }

    private String mensajeNumeroAdecuado(int numPastilla) {
        String mensaje;
        if (numPastilla < 1){
            mensaje = "Se necesita un mínimo de 1 pastilla.";
        }else{
            if (numPastilla > 1000){
                mensaje = "El máximo de pastillas es 1000.";
            }else{
                mensaje = "Número de pastillas incorrecto";
            }
        }
        return mensaje;
    }

    @Override
    public ElementoCalendario creadorElemento() {
        Pastilla pastilla = new Pastilla(getNombre(), getDescripcion(), getTotal(), getTipo());
        return pastilla;
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

    public void setDayOfWeekList(boolean[] dayOfWeekList) {
        this.dayOfWeekList = dayOfWeekList;
    }

    public void setHourDayOfWeekList(Time[][] hourDayOfWeekList) {
        this.hourDayOfWeekList = hourDayOfWeekList;
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

    public Time[][] getHourDayOfWeekList() {
        return hourDayOfWeekList;
    }

    public void setHora(String hora) {
        this.hora = hora;
    }
}
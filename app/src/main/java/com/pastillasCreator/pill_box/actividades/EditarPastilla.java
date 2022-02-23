package com.pastillasCreator.pill_box.actividades;

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
import com.pastillasCreator.pill_box.almacenaje.Pastillero;
import com.pastillasCreator.pill_box.elementosCalendario.Pastilla;
import com.pastillasCreator.pill_box.elementosCalendario.TipoPastilla;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.List;

public class EditarPastilla extends AppCompatActivity {

    private EditText nombr;
    private EditText descrip;
    private EditText tot;
    private TextView hor;
    private TextView tip;
    private TipoPastilla tipo;
    private Spinner spinner;
    int posicion;

    // Atributos para el multiseleccionar día de la semana
    private TextView listaSemana;
    private boolean[] diaSeleccionados;
    private ArrayList<Integer> listaDia = new ArrayList<>();
    private String[] diaArray = {"Lunes", "Martes", "Miércoles", "Jueves", "Viernes", "Sábado", "Domingo"};
    private boolean[] dayOfWeekList;
    private StringBuilder textoLista;

    Pastilla pastilla;
    Pastilla pastillaCopia;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_editar_pastilla);


        nombr = findViewById(R.id.nombrePastillaEdit);
        descrip = findViewById(R.id.DescripcionPastillaEdit);
        tot = findViewById(R.id.TotalPastillaEdit);
        hor = findViewById(R.id.HoraPastillaEdit);
        tip = findViewById(R.id.tipoPastillaEdit);
        spinner = findViewById(R.id.spinnerPastillaEdit);
        listaSemana= findViewById(R.id.listaSemanaPastillaEdit);

        Intent incomingIntent = getIntent();
        posicion = Integer.parseInt(incomingIntent.getStringExtra("posicion"));

        Pastillero pastillero = getPastillero();
        pastilla = pastillero.getPastilla(posicion);
        pastillaCopia = pastillero.getPastilla(posicion);

        nombr.setText(pastilla.getNombre());
        descrip.setText(pastilla.getDescripcion());
        tot.setText(Integer.toString(pastilla.getTotal()));
        hor.setText("Hora" + ":" + pastilla.getHora());
        tip.setText(pastilla.getTipo().toString());
        setTextListaSemana();
        listaSemana.setText(textoLista);

        BottomNavigationView bottomNavigationView = findViewById(R.id.bottom_navigation);
        bottomNavigationView.setSelectedItemId(R.id.pastillero);

        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                Pastillero listado = getPastillero();
                switch (menuItem.getItemId()) {
                    case R.id.home:
                        listado.addPastilla(pastillaCopia);
                        startActivity(new Intent(getApplicationContext(),
                                ActividadMain.class));
                        overridePendingTransition(0, 0);
                        return true;
                    case R.id.pastillero:
                        listado.addPastilla(pastillaCopia);
                        startActivity(new Intent(getApplicationContext(),
                                ActividadPastillero.class));
                        overridePendingTransition(0, 0);
                        return true;
                    case R.id.calendario:
                        listado.addPastilla(pastillaCopia);
                        startActivity(new Intent(getApplicationContext(),
                                ActividadCalendario.class));
                        overridePendingTransition(0, 0);
                        return true;
                    case R.id.citas:
                        listado.addPastilla(pastillaCopia);
                        startActivity(new Intent(getApplicationContext(),
                                ActividadCitero.class));
                        overridePendingTransition(0, 0);
                        return true;
                    case R.id.ayuda:
                        listado.addPastilla(pastillaCopia);
                        startActivity(new Intent(getApplicationContext(),
                                Ayuda.class));
                        overridePendingTransition(0, 0);
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
        if (pastilla.getDayOfWeekList() != null) {
            diaSeleccionados = pastilla.getDayOfWeekList();
        } else {
            diaSeleccionados = new boolean[diaArray.length];
        }
        dayOfWeekList = getDiaSeleccionados();

        listaSemana.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder builder = new AlertDialog.Builder(EditarPastilla.this);

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

        ArrayAdapter adaptador = new ArrayAdapter(EditarPastilla.this, android.R.layout.simple_spinner_dropdown_item, listaPastillas);

        spinner.setAdapter(adaptador);
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                TipoPastilla type = (TipoPastilla) spinner.getAdapter().getItem(position);
                setType(type);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                TipoPastilla type = pastilla.getTipo();
                setType(type);
            }
        });
    }

    public void abrirHora(View view) {
        Calendar c = Calendar.getInstance();
        int hora = c.get(Calendar.HOUR_OF_DAY);
        int min = c.get(Calendar.MINUTE);
        TimePickerDialog tmd = new TimePickerDialog(EditarPastilla.this, new TimePickerDialog.OnTimeSetListener() {
            @Override
            public void onTimeSet(TimePicker timePicker, int hourOfDay, int minute) {
                String hora = hourOfDay + ":" + minute;
                hor.setText("Hora" + ":" + hora);
                pastilla.setHora(hora);
            }
        },hora, min, false); // Contexto, listener
        tmd.show(); // Si no ponemos la función show, no se va a mostrar
    }

    // Comprueba que los datos importantes no estén vacíos y si lo están impide que se guarden
    @RequiresApi(api = Build.VERSION_CODES.O)
    public void guardar (View view){
        String nomPastilla = nombr.getText().toString();
        String num = tot.getText().toString();
        String desPastilla = descrip.getText().toString();

        if (!nomPastilla.isEmpty()){
            pastilla.setNombre(nomPastilla);
            if (!num.isEmpty()) {
                int numPastilla = Integer.parseInt(num);
                if (numeroAdecuado(numPastilla)) {
                    pastilla.setTotal(numPastilla);
                    if (!desPastilla.isEmpty()) {
                        pastilla.setDescripcion(desPastilla);
                        // Edita la pastilla, obtiene el listado de pastillas del sistema y modifica la pastilla creada a la misma siempre y cuando se tenga el número correcto de parámetros opcionales
                        Pastillero listado = getPastillero();
                        int numOpcionales = contadorDatosOpcionales();
                        boolean correcto = false;
                        switch (numOpcionales) {
                            case 2:
                                pastilla.setDayOfWeekList(this.getDayOfWeekList());
                                pastilla.setHora(hor.getText().toString());
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
                            listado.addPastilla(pastilla);
                            Toast.makeText(this, "Se modificó la pastilla: " + nomPastilla + " correctamente.", Toast.LENGTH_SHORT).show();
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
        boolean horaAnadida = pastilla.getHora() != null;
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

    private void setTextListaSemana() {
        int i = 0;
        if (pastilla.getDayOfWeekList() != null) {
            for (Boolean dia : pastilla.getDayOfWeekList()) {
                if (dia) {
                    listaDia.add(i);
                }
                i++;
            }

            StringBuilder stringBuilder = new StringBuilder();
            for (int j = 0; j < listaDia.size(); j++) {
                stringBuilder.append(diaArray[listaDia.get(j)]);
                if (j != listaDia.size() - 1) {
                    stringBuilder.append(", ");
                }
            }
            textoLista = stringBuilder;
        }
    }

    public void setType(TipoPastilla type) {
        this.tipo = type;
    }

    public boolean[] getDiaSeleccionados() {
        return diaSeleccionados;
    }

    public boolean[] getDayOfWeekList() {
        return dayOfWeekList;
    }

    public void borrarPastilla(View view){
        /*Intent i = new Intent(this, ActividadCalendario.class);
        startActivity(i);*/

        Pastillero pastillero = Pastillero.getPastillero();
        List<Pastilla> listaPastillas = pastillero.getListaPastillas();

        listaPastillas.remove(posicion);

        Intent i = new Intent(this, ActividadPastillero.class);
        startActivity(i);
        Toast.makeText(EditarPastilla.this, "Pastilla borrada correctamente", Toast.LENGTH_LONG).show();

    }

}
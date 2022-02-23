package com.pastillasCreator.pill_box.creadorElementosCalendario;

import static com.pastillasCreator.pill_box.almacenaje.Citero.getCitero;
import android.os.Build;

import androidx.annotation.RequiresApi;
import com.pastillasCreator.pill_box.almacenaje.Citero;
import com.pastillasCreator.pill_box.elementosCalendario.Cita;
import com.pastillasCreator.pill_box.elementosCalendario.ElementoCalendario;
import com.pastillasCreator.pill_box.herramientas.ManipuladorFechas;

import java.time.LocalDateTime;

public class CreadorCitas implements CreadorElementosCalendario {
    private final String nombre;
    private final String descripcion;
    private final String hora;
    private final String fecha;

    public CreadorCitas(String nombre, String descripcion, String hora, String fecha) {
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.hora = hora;
        this.fecha = fecha;
    }

    /*
    Si:
    --> cumpleRequisitosMinimos == null (Se han introducido los datos)
        --> Inicia guardarCita.
        --> Devuelve mensaje del resultado de guardarCita.
    --> cumpleRequisitosMinimos != null (No se han introducido los datos)
        --> Devuelve mensaje de error correspondiente.
     */
    @RequiresApi(api = Build.VERSION_CODES.O)
    public boolean crearCita(StringBuilder mensaje) {
        boolean cumplenRequisitos = false;
        mensaje.delete(0, mensaje.length());
        mensaje.append(cumpleRequisitosMinimos());

        // Si StringBuilder recibe un null, este guarda directamente "null"
        if (mensaje.length() == 4) {
            mensaje.delete(0, mensaje.length());
            mensaje.append(guardarCita());
            cumplenRequisitos = true;
        }
        return cumplenRequisitos;
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    public ElementoCalendario creadorElemento() {
        ManipuladorFechas manipuladorFechas = new ManipuladorFechas();
        LocalDateTime fechaConFormato =
                manipuladorFechas.fechaYHoraALocalDateTime(fecha, hora);

        return new Cita(nombre, descripcion, fechaConFormato);
    }

    /*
    Resultados:
    --> null    : Se cumplen los requisitos.
    --> !null   : Se ha dado un valor.
     */
    private String cumpleRequisitosMinimos() {
         String mensaje = null;
         boolean condicion = false;

         if (nombre.isEmpty()) {
             mensaje = "Se necesita incluir un nombre";
             condicion = true;
         }
         if (!condicion && descripcion.isEmpty()) {
             mensaje = "Se necesita incluir una descripción.";
             condicion = true;
         }
         if (!condicion && (hora == null || fecha == null)) {
             mensaje = "Se necesita incluir una hora y una fecha";
         }
         return mensaje;
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    private String guardarCita () {
        String mensaje;

        Cita cita = (Cita) creadorElemento();
        Citero citero = getCitero();

        if (citero.addCita(cita)) mensaje = "Se guardó la cita " + nombre + " correctamente.";
        else mensaje = "La cita " + nombre + " ya se encuentra guardada.";

        return mensaje;
    }
}

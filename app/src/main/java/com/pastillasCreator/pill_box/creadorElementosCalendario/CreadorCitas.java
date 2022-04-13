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

    @RequiresApi(api = Build.VERSION_CODES.O)
    public boolean crearCita(StringBuilder mensaje) {

        mensaje.delete(0, mensaje.length());
        mensaje.append(cumpleRequisitosMinimos());
        if (mensaje.length() == 4) {
            mensaje.delete(0, mensaje.length());
            mensaje.append(guardarCita());
            return true;
        }
        return false;
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    public ElementoCalendario creadorElemento() {
        ManipuladorFechas manipuladorFechas = new ManipuladorFechas();
        LocalDateTime fechaConFormato =
                manipuladorFechas.fechaYHoraALocalDateTime(fecha, hora);

        return new Cita(nombre, descripcion, fechaConFormato);
    }

    @androidx.annotation.RequiresApi(api = Build.VERSION_CODES.GINGERBREAD)
    private String cumpleRequisitosMinimos() {
        String mensaje = null;

        if (nombre.isEmpty()) {
            mensaje = "Se necesita incluir un nombre";
        }else if (descripcion.isEmpty()) {
            mensaje = "Se necesita incluir una descripción.";
        } else if (hora == null || fecha == null) {
            mensaje = "Se necesita incluir una hora y una fecha";
        }
        return mensaje;
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    private String guardarCita () {

        Cita cita = (Cita) creadorElemento();
        Citero citero = getCitero();

        return citero.addCita(cita) ?
                "Se guardó la cita " + nombre + " correctamente." :
                "La cita " + nombre + " ya se encuentra guardada.";
    }
}

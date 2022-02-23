package com.pastillasCreator.pill_box.almacenaje;

import android.os.Build;

import androidx.annotation.RequiresApi;

import com.pastillasCreator.pill_box.elementosCalendario.Cita;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class Citero {
    private static Citero citero;
    private final List<Cita> listaCitas;

    private Citero() {
        listaCitas = new ArrayList<>();
    }

    public static Citero getCitero(){
        if (citero == null) {
            citero = new Citero();
        }
        return citero;
    }

    // Cada vez que se cree una nueva cita debe añadirse aquí

    @RequiresApi(api = Build.VERSION_CODES.O)
    public boolean addCita(Cita cita) {
        if (perteneceCita(cita)) {
            return false;
        } else {
            return listaCitas.add(cita);
        }
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    private boolean perteneceCita(Cita cita) {
        if (listaCitas.isEmpty()) return false;
        else {
            for (Cita citaLista: listaCitas)
                if (cita.getDate().isEqual(citaLista.getDate())) return true;
        }
        return false;
    }

    // Al ser una lista a la que se le introduciran varios nombres de seguido
    // es preferible que sea LinkedList (más fácil de modificar tamaño).
    public List<String> getListaNombreCitas() {
        String ajusteFormato;
        LinkedList<String> listaNombreCitas = new LinkedList<>();

        for (Cita cita: listaCitas) {
            ajusteFormato = cita.getDate().toString().replace("T", " ");
            listaNombreCitas.add(cita.getNombre() + " " + ajusteFormato);
        }

        return listaNombreCitas;
    }

    public Cita getCita(int position) {
        return listaCitas.get(position);
    }

    public List<Cita> getListaCitas() {
        return listaCitas;
    }
}
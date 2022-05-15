package com.pastillasCreator.pill_box.almacenaje;

import com.pastillasCreator.pill_box.elementosCalendario.Pastilla;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class Pastillero {

    private static Pastillero pastillero;
    private final List<Pastilla> listaPastillas;

    private Pastillero() {
        listaPastillas = new ArrayList<>();
    }

    //Creamos una instancia del objeto si todavia no existe ninguna
    public static Pastillero getPastillero(){
        if (pastillero == null) {
            pastillero = new Pastillero();
        }
        return pastillero;
    }
    // Crear clase Pastillero encargada de guardar nuevas Pastillas
    // Cada vez que se cree una nueva pastilla debe añadirse aquí

    public boolean addPastilla(Pastilla pastilla) {
        if (pertenecePastilla(pastilla)) {
            return false;
        } else {
            listaPastillas.add(pastilla);
            return true;
        }
    }

    private boolean pertenecePastilla(Pastilla pastilla) {
        if (listaPastillas.isEmpty()) return false;
        else {
            for (Pastilla pastillaPastillero: listaPastillas)
                if (pastilla.getNombre().equals(pastillaPastillero.getNombre())) return true;
        }
        return false;
    }

    public List<String> getListaNombrePastillas() {

        LinkedList<String> listaNombrePastillas = new LinkedList<>() ;

        for (Pastilla pastilla: listaPastillas) {
            if (pastilla.getCaducidad()==null){
                listaNombrePastillas.add(pastilla.getNombre());
            }else{
            listaNombrePastillas.add(pastilla.getNombre()+ "   Caducidad: " + pastilla.getCaducidad());}
        }

        return listaNombrePastillas;
    }

    public Pastilla getPastilla(int position) {
        return listaPastillas.get(position);
    }

    public List<Pastilla> getListaPastillas() {
        return listaPastillas;
    }
}

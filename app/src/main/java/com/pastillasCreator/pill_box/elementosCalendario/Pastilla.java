package com.pastillasCreator.pill_box.elementosCalendario;

import java.util.Arrays;

public class Pastilla extends ElementoCalendario {

    private int total;
    private TipoPastilla tipo;
    private boolean[] dayOfWeekList;
    private String hora;
    private  String caducidad;

    public Pastilla(String nombre, String description, int total, TipoPastilla tipo,String caducidad) {
        super(nombre, description);
        this.total = total;
        this.tipo = tipo;
        this.caducidad=caducidad;
    }

    public void setDayOfWeekList(boolean[] dayOfWeekList) {
        this.dayOfWeekList = dayOfWeekList;
    }

    public void setTotal(int total) {
        this.total = total;
    }

    public void setTipo(TipoPastilla tipo) {
        this.tipo = tipo;
    }

    public void setHora(String hora) { this.hora = hora; }

    public void setCaducidad(String caducidad) { this.caducidad = caducidad; }

    public int getTotal() {
        return total;
    }

    public boolean[] getDayOfWeekList() {
        return dayOfWeekList;
    }

    public TipoPastilla getTipo() {
        return tipo;
    }

    public String getHora() { return hora; }

    public String getCaducidad() { return caducidad; }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Pastilla pastilla = (Pastilla) o;
        return total == pastilla.total && tipo == pastilla.tipo && Arrays.equals(dayOfWeekList, pastilla.dayOfWeekList) && hora.equals(pastilla.hora);
    }
}


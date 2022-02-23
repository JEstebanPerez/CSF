package com.pastillasCreator.pill_box.elementosCalendario;

import java.time.LocalDateTime;
import java.util.Objects;

public class Cita extends ElementoCalendario {

    private LocalDateTime date;

    public Cita(String nombre, String descripcion, LocalDateTime date) {
        super(nombre, descripcion);
        this.date = date;
    }

    public LocalDateTime getDate() {
        return date;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Cita)) return false;
        Cita cita = (Cita) o;
        return Objects.equals(getDate(), cita.getDate()) && getNombre().equals(cita.getNombre()) &&
                getDescripcion().equals(cita.getDescripcion());
    }

    public void setDate(LocalDateTime date) {
        this.date = date;
    }
}

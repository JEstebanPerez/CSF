package com.pastillasCreator.pill_box.elementosCalendario;

import android.os.Build;

import androidx.annotation.RequiresApi;

import com.pastillasCreator.pill_box.herramientas.ObjectComparator;

import java.time.LocalDateTime;
import java.util.Objects;

public class Appointment extends CalendarElement {

    private LocalDateTime date;

    public Appointment(String nombre, String descripcion, LocalDateTime date) {
        super(nombre, descripcion);
        this.date = date;
    }

    public LocalDateTime getDate() {
        return date;
    }

    public void setDate(LocalDateTime date) {
        this.date = date;
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    public String getHour(){
        return date.getHour() + ":" + date.getMinute();
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    public String getStringStoringDate(){
        return date.getYear() + "-" +
                date.getMonthValue() + "-" +
                date.getDayOfMonth();
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    public String getStringShowingDate(){
        return date.getDayOfMonth()+ "/"+
                date.getMonthValue() + "/" +
                date.getYear();
    }

}

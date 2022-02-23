package com.pastillasCreator.pill_box.herramientas;

import android.os.Build;


import androidx.annotation.RequiresApi;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class ManipuladorFechas {

    public ManipuladorFechas() {
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    public LocalDateTime fechaYHoraALocalDateTime(String dias, String hora) {
        String fecha = dias + " " + hora;
        DateTimeFormatter formato = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");

        return LocalDateTime.parse(fecha, formato);
    }

    public String IntegerAStringFecha(int intMinutos){
        String strMinuto = String.valueOf(intMinutos);
        if (intMinutos < 10) {
            strMinuto = "0" + strMinuto;
        }
        return strMinuto;
    }
}

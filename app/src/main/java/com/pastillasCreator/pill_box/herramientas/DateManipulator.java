package com.pastillasCreator.pill_box.herramientas;

import android.os.Build;


import androidx.annotation.RequiresApi;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class DateManipulator {

    @RequiresApi(api = Build.VERSION_CODES.O)
    public static LocalDateTime dateFromStringToLocalDateTime(String days, String hour) {
        String date = days + " " + hour;
        DateTimeFormatter format = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");

        return LocalDateTime.parse(date, format);
    }

    public static String inTimeToStringValue(int minutes){
        String beautyMinutes = "0".concat(String.valueOf(minutes));
        return beautyMinutes.substring(beautyMinutes.length()-2);
    }
}

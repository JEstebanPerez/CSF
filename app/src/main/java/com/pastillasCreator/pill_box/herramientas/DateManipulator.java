package com.pastillasCreator.pill_box.herramientas;

import android.os.Build;

import androidx.annotation.RequiresApi;

import com.pastillasCreator.pill_box.elementosCalendario.CalendarElement;
import com.pastillasCreator.pill_box.exceptions.CreatorException;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class DateManipulator {

    @RequiresApi(api = Build.VERSION_CODES.O)
    public static LocalDateTime dateFromStringToLocalDateTime(String days, String hour) {
        if(days.isEmpty()){
            throw new CreatorException(CreatorException.EMPTY_VALUE,"days");
        }
        if(hour.isEmpty()){
            throw new CreatorException(CreatorException.EMPTY_VALUE,"hours");
        }
        String date = days + " " + hour;
        String pattern = CalendarElement.LOCAL_DATE_FORMAT + " " + CalendarElement.LOCAL_MINUTE_FORMAT;
        DateTimeFormatter format = DateTimeFormatter.ofPattern(pattern);
        return LocalDateTime.parse(date, format);
    }

    public static String inTimeToStringValue(int time){
        String beautyMinutes = "0".concat(String.valueOf(time));
        return beautyMinutes.substring(beautyMinutes.length()-2);
    }
}

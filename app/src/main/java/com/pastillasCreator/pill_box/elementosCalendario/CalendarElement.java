package com.pastillasCreator.pill_box.elementosCalendario;

import android.os.Build;

import androidx.annotation.RequiresApi;

import com.pastillasCreator.pill_box.herramientas.DateManipulator;
import com.pastillasCreator.pill_box.herramientas.ObjectComparator;

import java.io.Serializable;
import java.time.LocalDateTime;

public abstract class CalendarElement implements Serializable {
    protected String name;
    protected String description;
    protected LocalDateTime date;
    protected final Integer DAYS = 7;
    private static final String DATE_FORMAT="%s/%s/%d";
    private static final String MINUTE_FORMAT="%s:%s";
    public static final String LOCAL_DATE_FORMAT = "dd/MM/yyyy";
    public static final String LOCAL_MINUTE_FORMAT = "HH:mm";

    public static String getDayFormatted(int day, int month, int year){
        String day_value = DateManipulator.inTimeToStringValue(day);
        String month_value = DateManipulator.inTimeToStringValue(month);
        return String.format(DATE_FORMAT,day_value,month_value,year);
    }

    public static String getMinuteFormat(int hour,int minute){
        String hour_value = DateManipulator.inTimeToStringValue(hour);
        String minute_value = DateManipulator.inTimeToStringValue(minute);
        return String.format(MINUTE_FORMAT,hour_value,minute_value);
    }

    public CalendarElement(String name, String description,LocalDateTime date) {
        this.name = name;
        this.description = description;
        this.date = date;
    }

    public String getName() {
        return name;
    }
    public String getDescription() {
        return description;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public boolean equals(Object object) {
        try {
            return ObjectComparator.compareObjects(this, object, Appointment.class);
        } catch (IllegalAccessException e) {
            e.printStackTrace();
            return false;
        }
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    public String getHour(){
        return date.getHour() + ":" + date.getMinute();
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    public String getStringShowingDate(){
        return date.getDayOfMonth()+ "/"+
                date.getMonthValue() + "/" +
                date.getYear();
    }

    public void change(CalendarElement calendarElement){
        name = calendarElement.name;
        description = calendarElement.description;
        date = calendarElement.date;
    }
}

package com.pastillasCreator.pill_box.DATA_FIXTURE;

import com.pastillasCreator.pill_box.elementosCalendario.Appointment;
import com.pastillasCreator.pill_box.elementosCalendario.CalendarElement;


import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;

public class CalendarElementDataFixture {

    public static CalendarElement getRandomCalendarElement(){
        String name = getRandomName();
        String description = getRandomDesceiption();
        LocalDateTime localDateTime = getRandomLocalDateTime();
        return new Appointment(name,description,localDateTime);
    }

    public static CalendarElement getRandomCalendarElement(String name){
        String description = getRandomDesceiption();
        LocalDateTime localDateTime = getRandomLocalDateTime();
        return new Appointment(name,description,localDateTime);
    }

    public static CalendarElement getRandomCalendarElement(LocalDateTime localDateTime){
        String name = getRandomName();
        String description = getRandomDesceiption();
        return new Appointment(name,description,localDateTime);
    }

    public static CalendarElement getRandomCalendarElement(String name,String description){
        LocalDateTime localDateTime = getRandomLocalDateTime();
        return new Appointment(name,description,localDateTime);
    }

    private static String getRandomName(){
        int length = 10;
        return getRandomString(length);
    }

    private static String getRandomDesceiption(){
        int length = 100;
        return getRandomString(length);
    }

    private static LocalDateTime getRandomLocalDateTime(){
        long minDay = LocalDate.of(1970, 1, 1).toEpochDay();
        long maxDay = LocalDate.of(2015, 12, 31).toEpochDay();
        long randomDay = ThreadLocalRandom.current().nextLong(minDay, maxDay);
        LocalDate date = LocalDate.ofEpochDay(randomDay);
        return LocalDateTime.of(date, LocalTime.MIDNIGHT);
    }

    private static String getRandomString(int length) {
        String validChars = "0123456789ABCDEFGHIJKLMNOPQRSTUVWXTZabcdefghiklmnopqrstuvwxyz";
        char[] chars = validChars.toCharArray();
        StringBuilder randomstring = new StringBuilder();
        for (int i = 0; i < length; i++) {
            int random_number = new Random().nextInt(chars.length);
            randomstring.append(chars[random_number]);
        }
        return randomstring.toString();
    }
}

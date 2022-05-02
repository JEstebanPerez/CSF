package com.pastillasCreator.pill_box.DATA_FIXTURE;

import com.pastillasCreator.pill_box.elementosCalendario.CalendarElement;
import com.pastillasCreator.pill_box.elementosCalendario.Pill;
import com.pastillasCreator.pill_box.elementosCalendario.PillType;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Random;

public class PillDataFixture {

    private static Random random = new Random();


    public static Pill getRandomPill(){
        CalendarElement calendarElement = CalendarElementDataFixture.getRandomCalendarElement();
        String name = calendarElement.getName();
        String description = calendarElement.getDescription();
        LocalDateTime date = calendarElement.getDate();
        int total = getRandomTotal();
        PillType pillType = getRandomPillType();
        boolean[] dayOfWeekList = getRandomDayOfWeekList();
        return new Pill(name,description,date,total,pillType,dayOfWeekList);
    }

    public static Pill getRandomPill(String name){
        CalendarElement calendarElement = CalendarElementDataFixture.getRandomCalendarElement(name);
        String description = calendarElement.getDescription();
        LocalDateTime date = calendarElement.getDate();
        int total = getRandomTotal();
        PillType pillType = getRandomPillType();
        boolean[] dayOfWeekList = getRandomDayOfWeekList();
        return new Pill(name,description,date,total,pillType,dayOfWeekList);
    }

    public static Pill getRandomPill(LocalDateTime date){
        CalendarElement calendarElement = CalendarElementDataFixture.getRandomCalendarElement(date);
        String name = calendarElement.getName();
        String description = calendarElement.getDescription();
        int total = getRandomTotal();
        PillType pillType = getRandomPillType();
        boolean[] dayOfWeekList = getRandomDayOfWeekList();
        return new Pill(name,description,date,total,pillType,dayOfWeekList);
    }

    public static Pill getRandomPill(String name, String description){
        CalendarElement calendarElement = CalendarElementDataFixture.getRandomCalendarElement(name, description);
        LocalDateTime date = calendarElement.getDate();
        int total = getRandomTotal();
        PillType pillType = getRandomPillType();
        boolean[] dayOfWeekList = getRandomDayOfWeekList();
        return new Pill(name,description,date,total,pillType,dayOfWeekList);
    }

    public static Pill getRandomPill(String name, String description, LocalDateTime date){
        int total = getRandomTotal();
        PillType pillType = getRandomPillType();
        boolean[] dayOfWeekList = getRandomDayOfWeekList();
        return new Pill(name,description,date,total,pillType,dayOfWeekList);
    }

    public static Pill getRandomPill(String name, String description, LocalDateTime date,int total){
        PillType pillType = getRandomPillType();
        boolean[] dayOfWeekList = getRandomDayOfWeekList();
        return new Pill(name,description,date,total,pillType,dayOfWeekList);
    }

    public static Pill getRandomPill(int total, PillType pillType, boolean[] dayOfWeekList) {
        CalendarElement calendarElement = CalendarElementDataFixture.getRandomCalendarElement();
        String name = calendarElement.getName();
        String description = calendarElement.getDescription();
        LocalDateTime date = calendarElement.getDate();
        return new Pill(name,description,date,total,pillType,dayOfWeekList);

    }

    public static Pill getRandomPill(String name, String description, LocalDateTime date,int total,
                                     PillType pillType){
        boolean[] dayOfWeekList = getRandomDayOfWeekList();
        return new Pill(name,description,date,total,pillType,dayOfWeekList);
    }

    private static PillType getRandomPillType(){
        PillType[] values = PillType.values();
        int number_of_pilltypes = values.length;
        return values[random.nextInt(number_of_pilltypes)];
    }


    private static int getRandomTotal(){
        return random.nextInt(10);
    }

    public static boolean[] getRandomDayOfWeekList(){
        int days = 7;
        boolean[] dayOfWeekList = new boolean[days];
        for(int i=0;i<days;i++){
            dayOfWeekList[i]=random.nextInt()%2 ==0;
        }
        return dayOfWeekList;
    }
}

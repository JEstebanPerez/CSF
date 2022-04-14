package com.pastillasCreator.pill_box.elementosCalendario;

import java.time.LocalDateTime;

public class Pill extends CalendarElement {

    private int total;
    private PillType type;
    private boolean[] dayOfWeekList;

    public Pill(String name, String description, LocalDateTime date, int total, PillType type,boolean[] dayOfWeekList) {
        super(name, description,date);
        this.total = total;
        this.type = type;
        this.dayOfWeekList = dayOfWeekList;
    }

    public int getTotal() {
        return total;
    }

    public PillType getType() {
        return type;
    }

    public boolean[] getDayOfWeekList(){
        return dayOfWeekList;
    }

    public void change(Pill calendarElement){
        super.change(calendarElement);
        total = calendarElement.total;
        type = calendarElement.type;
        dayOfWeekList = calendarElement.dayOfWeekList;
    }
}


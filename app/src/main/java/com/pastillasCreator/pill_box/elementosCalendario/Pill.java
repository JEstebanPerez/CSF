package com.pastillasCreator.pill_box.elementosCalendario;

import com.pastillasCreator.pill_box.herramientas.ObjectComparator;

import java.util.Arrays;

public class Pill extends CalendarElement {

    private int total;
    private PillType type;
    private boolean[] dayOfWeekList;
    private String hour;

    public Pill(String name, String description, int total, PillType type) {
        super(name, description);
        this.total = total;
        this.type = type;
    }

    public void setDayOfWeekList(boolean[] dayOfWeekList) {
        this.dayOfWeekList = dayOfWeekList;
    }

    public void setTotal(int total) {
        this.total = total;
    }

    public void setHour(String hour) { this.hour = hour; }

    public int getTotal() {
        return total;
    }

    public boolean[] getDayOfWeekList() {
        return dayOfWeekList;
    }

    public PillType getType() {
        return type;
    }

    public String getHour() { return hour; }

}


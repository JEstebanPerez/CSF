package com.pastillasCreator.pill_box.almacenaje;

import android.os.Build;

import androidx.annotation.RequiresApi;

import com.pastillasCreator.pill_box.elementosCalendario.CalendarElement;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public abstract class Accumulator<T extends CalendarElement> {

    protected List<T> elements = new ArrayList<>();

    public T getElement(int id){
        return elements.get(id);
    }

    public boolean saveElement(T element){
        if(containsElement(element)) return false;
        elements.add(element);
        return true;
    }

    public boolean containsElement(T element){
        return elements.contains(element);
    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    public List<String> getNameListOfElements(){
        return elements.stream().map(CalendarElement::getName).collect(Collectors.toList());
    }

    public List<T> getAllElements(){
        return elements;
    }

    public void removeElement(int pos){
        elements.remove(pos);
    }
}

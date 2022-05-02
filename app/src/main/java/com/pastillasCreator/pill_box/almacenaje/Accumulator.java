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

    public void saveElement(T element){
        if(containsElement(element)) return;
        elements.add(element);
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

    public void removeElement(T element){
        elements.remove(element);
    }

    public void clear(){
        elements.clear();
    }

}

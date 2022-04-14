package com.pastillasCreator.pill_box.almacenaje;


import com.pastillasCreator.pill_box.elementosCalendario.Pill;

public class PillAccumulator extends Accumulator<Pill> {

    private static PillAccumulator instance = null;

    private PillAccumulator() {}

    public static PillAccumulator getPillAccumulator(){
        if (instance == null) {
            instance = new PillAccumulator();
        }
        return instance;
    }
}

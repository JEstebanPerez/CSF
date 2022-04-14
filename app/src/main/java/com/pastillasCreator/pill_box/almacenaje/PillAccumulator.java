package com.pastillasCreator.pill_box.almacenaje;


import com.pastillasCreator.pill_box.elementosCalendario.Pill;

public class PillAccumulator extends Accumulator<Pill> {

    private static PillAccumulator pillAccumulator;

    private PillAccumulator() {}

    public static PillAccumulator getPillAccumulator(){
        if (pillAccumulator == null) {
            pillAccumulator = new PillAccumulator();
        }
        return pillAccumulator;
    }
}

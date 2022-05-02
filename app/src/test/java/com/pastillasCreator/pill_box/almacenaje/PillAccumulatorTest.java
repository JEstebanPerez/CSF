package com.pastillasCreator.pill_box.almacenaje;

import junit.framework.TestCase;

import java.util.ArrayList;
import java.util.List;

public class PillAccumulatorTest extends TestCase {

    public void testGetPillAccumulator() {
        List<PillAccumulator> appointmentList = new ArrayList<>();
        for(int i=0;i<10;i++){
            appointmentList.add(PillAccumulator.getPillAccumulator());
        }
        assertNotNull(appointmentList.stream().reduce(PillAccumulator.getPillAccumulator(),
                (x,y) -> x.equals(y) ? x : null));
    }
}
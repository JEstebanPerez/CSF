package com.pastillasCreator.pill_box.almacenaje;

import com.pastillasCreator.pill_box.elementosCalendario.Appointment;

import junit.framework.TestCase;

import java.util.ArrayList;
import java.util.List;

public class AppointmentAccumulatorTest extends TestCase {

    public void testGetAccumulator() {
        List<AppointmentAccumulator> appointmentList = new ArrayList<>();
        for(int i=0;i<10;i++){
            appointmentList.add(AppointmentAccumulator.getAccumulator());
        }
        assertNotNull(appointmentList.stream().reduce(AppointmentAccumulator.getAccumulator(),
                (x,y) -> x.equals(y) ? x : null));
    }
}
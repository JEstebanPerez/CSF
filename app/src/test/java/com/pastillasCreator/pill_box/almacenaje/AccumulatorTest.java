package com.pastillasCreator.pill_box.almacenaje;

import com.pastillasCreator.pill_box.DATA_FIXTURE.AppointmentDataFixture;
import com.pastillasCreator.pill_box.elementosCalendario.Appointment;
import com.pastillasCreator.pill_box.elementosCalendario.CalendarElement;

import junit.framework.TestCase;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;


public class AccumulatorTest extends TestCase {

    private final Accumulator<Appointment> accumulator = AppointmentAccumulator.getAccumulator();

    @BeforeEach
    public void setUp() {
        accumulator.clear();
    }

    @Test
    public void testGetElement() {
        ArrayList<Appointment> appointments = new ArrayList<>();
        assertEquals(Collections.emptyList(),accumulator.getAllElements());
        for(int i=0;i<10;i++){
            Appointment appointment = AppointmentDataFixture.getRandomAppointment();
            appointments.add(appointment);
            accumulator.saveElement(appointment);
        }
        for(int i=0;i<10;i++){
            assertEquals(appointments.get(i),accumulator.getElement(i));
        }

    }

    @Test
    public void testSaveElement() {
        Appointment appointment = AppointmentDataFixture.getRandomAppointment();
        accumulator.saveElement(appointment);
        accumulator.saveElement(appointment);
        accumulator.saveElement(appointment);
        accumulator.saveElement(appointment);
        Appointment aux = accumulator.getElement(0);
        assertEquals(aux,appointment);
        assertEquals(1,accumulator.getAllElements().size());
    }

    @Test
    public void testContainsElement() {
        List<Appointment> list = new ArrayList<>();
        for(int i=0;i<20;i++){
            Appointment appointment = AppointmentDataFixture.getRandomAppointment();
            if(i<10){
                accumulator.saveElement(appointment);
            }
            list.add(appointment);
        }
        for(int i=0;i<20;i++){
            Appointment appointment = list.get(i);
            if(i<10){
                assertTrue(accumulator.containsElement(appointment));
            }else{
                assertFalse(accumulator.containsElement(appointment));
            }
        }

    }

    @Test
    public void testGetNameListOfElements() {
        List<Appointment> list = new ArrayList<>();
        for(int i=0;i<10;i++){
            Appointment appointment = AppointmentDataFixture.getRandomAppointment();
            accumulator.saveElement(appointment);
            list.add(appointment);
        }
        List<String> names = list.stream().map(CalendarElement::getName).collect(Collectors.toList());
        assertEquals(names,accumulator.getNameListOfElements());
    }

    @Test
    public void testGetAllElements() {
        List<Appointment> list = new ArrayList<>();
        for(int i=0;i<10;i++){
            Appointment appointment = AppointmentDataFixture.getRandomAppointment();
            accumulator.saveElement(appointment);
            list.add(appointment);
        }
        assertEquals(list,accumulator.getAllElements());
    }

    @Test
    public void testRemoveElementByPosition() {
        Appointment appointment = AppointmentDataFixture.getRandomAppointment();
        accumulator.saveElement(appointment);
        accumulator.removeElement(0);
        assertEquals(accumulator.getAllElements(),Collections.emptyList());
    }

    @Test
    public void testRemoveElementByElement() {
        Appointment appointment = AppointmentDataFixture.getRandomAppointment();
        accumulator.saveElement(appointment);
        accumulator.removeElement(appointment);
        assertEquals(accumulator.getAllElements(),Collections.emptyList());

    }

    /*

    public void removeElement(int pos){
        elements.remove(pos);
    }

    public void removeElement(T element){
        elements.remove(element);
    }*/
}
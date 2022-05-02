package com.pastillasCreator.pill_box.herramientas;

import junit.framework.TestCase;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class DateManipulatorTest extends TestCase {


    public void testDateFromStringToLocalDateTime() {
        String days = "07/02/2022";
        String hours = "23:45";
        LocalDateTime date = DateManipulator.dateFromStringToLocalDateTime(days,hours);
        assertEquals(7,date.getDayOfMonth());
        assertEquals(2,date.getMonthValue());
        assertEquals(2022,date.getYear());
        assertEquals(23,date.getHour());
        assertEquals(45,date.getMinute());
        assertEquals(0,date.getSecond());
    }

    public void testInTimeToStringValue() {
        List<String> values = IntStream.rangeClosed(0,99).mapToObj(DateManipulator::inTimeToStringValue)
                .collect(Collectors.toList());
        long distint_int_values = values.stream().map(Integer::parseInt).distinct().count();
        long count = values.stream().filter(x -> x.length()==2).count();
        assertEquals(100, count);
        assertEquals(100,distint_int_values);
    }
}
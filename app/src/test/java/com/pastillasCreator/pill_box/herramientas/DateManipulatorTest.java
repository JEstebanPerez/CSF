package com.pastillasCreator.pill_box.herramientas;

import android.os.Build;

import androidx.annotation.RequiresApi;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class DateManipulatorTest {


    @RequiresApi(api = Build.VERSION_CODES.N)
    @Test
    public void testInTimeToStringValue() {
        List<String> validStrings = IntStream.rangeClosed(0,9)
                .mapToObj(x -> "0"+x).collect(Collectors.toList());
        List<String> validStringsHigherThan10 = IntStream.rangeClosed(10,99)
                .mapToObj(x -> ""+x).collect(Collectors.toList());

        validStrings.addAll(validStringsHigherThan10);

        IntStream.rangeClosed(0,99).forEach(x ->{
            String transform = DateManipulator.inTimeToStringValue(x);
            String valid = validStrings.get(x);
            Assert.assertEquals(transform,valid);
        });
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    @Test
    public void testDateFromStringToLocalDateTime() {
        LocalDateTime now = LocalDateTime.now();
        String day = now.getYear()+"-"+now.getMonthValue()+"-"+now.getDayOfMonth();
        String hour = now.getHour()+":"+now.getMinute();
        LocalDateTime otherNow = DateManipulator.dateFromStringToLocalDateTime(day,hour);

        Assert.assertEquals(now.getYear(), otherNow.getYear());
        Assert.assertEquals(now.getMonthValue(), otherNow.getMonthValue());
        Assert.assertEquals(now.getDayOfMonth(), otherNow.getDayOfMonth());
        Assert.assertEquals(now.getHour(), otherNow.getHour());
        Assert.assertEquals(now.getMinute(), otherNow.getMinute());

    }


}
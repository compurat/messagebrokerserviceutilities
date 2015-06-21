package com.purat;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.runners.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class DateTimeFormatterTest {
    private static final String TIME = "1434814393432";
    private static final String DATE_TIME_FORMAT = "dd-MM-YYYY hh:mm:ss";
    private static final String FORMATTED_DATE_TIME = "20-06-2015 05:33:13";
    @InjectMocks
    DateTimeFormatter dateTimeFormatter;

    @Test
    public void dateTimeFormatterTest() {

        String formattedDateTime = dateTimeFormatter.format(TIME, DATE_TIME_FORMAT);
        Assert.assertEquals(FORMATTED_DATE_TIME, formattedDateTime);
    }
}
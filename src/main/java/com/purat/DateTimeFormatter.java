package com.purat;

import org.springframework.stereotype.Component;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * Created by compurat on 21-6-15.
 */
@Component
public class DateTimeFormatter {



    public String format(final String dateTime, String dateTimeFormat) {
        Long dateTimeNumber = Long.parseLong(dateTime);
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(dateTimeFormat);
        String formattedTime = simpleDateFormat.format(dateTimeNumber);
        return formattedTime;
    }
}

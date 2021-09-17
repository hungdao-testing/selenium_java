package com.aspire.loan.datagenerator.builder;

import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;


public class TimeBuilder {

    public static Map<String, String> getDob(){
        Map<String, String> dob = new HashMap<>();
        Date dateFaker =  BuilderSetup.faker.date().birthday(18, 22);
        Calendar cal = Calendar.getInstance(BuilderSetup.defaultTimeZone);
        cal.setTime(dateFaker);
        int year = cal.get(Calendar.YEAR);
        int month = cal.get(Calendar.MONTH);
        int day = cal.get(Calendar.DATE);

        if(cal.get(Calendar.MONTH) == Calendar.JANUARY){
            month += 1;
        }

        dob.put("year", String.valueOf(year));
        dob.put("month", String.valueOf(month));
        dob.put("day", String.valueOf(day));
        return dob;
    }

}

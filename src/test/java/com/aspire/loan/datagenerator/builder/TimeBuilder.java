package com.aspire.loan.datagenerator.builder;

import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;


public class TimeBuilder {

    public static Map<String, String> getDob(){
        Map<String, String> dob = new HashMap<>();
        Date dateFaker =  BuilderSetup.faker.date().birthday(18, 65);
        Calendar cal = Calendar.getInstance(BuilderSetup.defaultTimeZone);
        cal.setTime(dateFaker);

        dob.put("year", String.valueOf(cal.get(Calendar.YEAR)));
        dob.put("month", String.valueOf(cal.get(Calendar.MONTH)));
        dob.put("day", String.valueOf(cal.get(Calendar.DATE)));
        return dob;
    }

}

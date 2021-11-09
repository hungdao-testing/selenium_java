package com.aspire.loan.datagenerator.builder;

import com.aspire.loan.helpers.web_element.ElementConstants;

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

        dob.put(ElementConstants.CalendarElement.YEAR.getName(), String.valueOf(year));
        dob.put(ElementConstants.CalendarElement.MONTH.getName(), String.valueOf(month));
        dob.put(ElementConstants.CalendarElement.DAY.getName(), String.valueOf(day));
        return dob;
    }

}

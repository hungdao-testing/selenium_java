package com.aspire.loan.ui.utils;

import java.time.Month;
import java.time.MonthDay;
import java.time.Year;
import java.time.format.TextStyle;
import java.util.Locale;

public class DateHelper {

    public static String convertToShortMonthFormat(String month, Locale locale){
        return Month.of(Integer.valueOf(month)).getDisplayName(TextStyle.SHORT_STANDALONE, locale);
    }
    public static String convertToFullMonthFormat(String month, Locale locale){
        return Month.of(Integer.valueOf(month)).getDisplayName(TextStyle.FULL_STANDALONE, locale);
    }

    public static int getCurrentYear(){
        return Year.now().getValue();
    }
    public static int getCurrentMonth(){
        return MonthDay.now().getMonth().getValue();
    }
    public static int getCurrentDay(){
        return MonthDay.now().getDayOfMonth();
    }

}

package com.aspire.loan.data;

public class DataManagement {

    public static String getHearAboutUs(){
       return HearAboutUsDataBuilder.fetchHearAboutUsByApi();
    }

    public static Personal getPersonal() {
        return new PersonalDataBuilder().generateData();
    }

}


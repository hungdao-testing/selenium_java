package com.aspire.loan.data;

public class DataManagement {

    public static String getHearAboutUs(){
       return new HearAboutUsDataBuilder().fetchHearAboutUsByApi();
    }

    public static Personal getPersonal() {
        return new PersonalDataBuilder().generateData();
    }

    public static String getOtp(){
        return new OtpData().getOtp();
    }

}


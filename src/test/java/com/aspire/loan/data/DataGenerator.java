package com.aspire.loan.data;

import com.aspire.loan.data.handler.HearAboutUsData;
import com.aspire.loan.data.handler.OtpData;

public class DataGenerator {

    public static String getHearAboutUs(){
       return new HearAboutUsData().fetchHearAboutUsByApi();
    }

    public static PersonalInfo getPersonalInfo() {
        return new PersonalDataBuilder().generateData();
    }

    public static String getOtp(){
        return new OtpData().getOtp();
    }

}


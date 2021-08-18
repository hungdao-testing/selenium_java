package com.aspire.loan.data;

import com.aspire.loan.data.handler.HearAboutUsData;
import com.aspire.loan.data.handler.OtpData;

public class DataGenerator {

    public static String getHearAboutUs(){
       return new HearAboutUsData().fetchHearAboutUsByApi();
    }

    public static PersonalInfo getValidPersonalInfo() {
        return new PersonalDataBuilder().generateValidData();
    }

    public static String getOtp(){
        return new OtpData().getOtp();
    }

    public static PersonalInfo getInvalidPersonalInfoWith(String name, String email, String phone) {
        return new PersonalDataBuilder().generateInvalidData(name, email, phone);
    }
}


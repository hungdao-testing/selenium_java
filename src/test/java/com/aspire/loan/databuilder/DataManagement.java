package com.aspire.loan.databuilder;
import com.aspire.loan.data.Personal;

public class DataManagement {

    public static String getHearAboutUs(){
       return HearAboutUsDataBuilder.fetchHearAboutUsByApi();
    }

    public static Personal getPersonal() {
        return new PersonalDataBuilder().generateData();
    }

}


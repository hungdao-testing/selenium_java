package com.aspire.loan.databuilder;
import com.aspire.loan.data.Personal;

public class DataManagement {

    public static String getHearAboutUs(){
       return HearAboutUsBuilder.fetchHearAboutUsByApi();
    }

    public static Personal getPersonal() {
        return new PersonalBuilder().generateData();
    }

}


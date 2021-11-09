package com.aspire.loan.models.uidata.configtype;

public enum BusinessRegistrationMethodType {

    MY_INFO("Register via Myinfo Business"),
    STANDARD("Standard Registration");

    private final String businessMethodDesc;

    BusinessRegistrationMethodType(String businessMethodDesc) {
        this.businessMethodDesc = businessMethodDesc;
    }

    public String getBusinessMethodDesc(){
        return businessMethodDesc;
    }
}

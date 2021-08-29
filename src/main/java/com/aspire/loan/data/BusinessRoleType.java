package com.aspire.loan.data;

public enum BusinessRoleType {

    DIRECTOR("I am a registered director of the company"),
    EMPLOYEE("I am a non-registered director or an employee"),
    FREELANCER("I am a freelancer"),
    ENTREPRENEUR("I am an entrepreneur and I want to incorporate in Singapore");

    private final String roleDesc;

    BusinessRoleType(String roleDesc) {
        this.roleDesc = roleDesc;
    }

    public String getRoleDesc(){
        return roleDesc;
    }

}

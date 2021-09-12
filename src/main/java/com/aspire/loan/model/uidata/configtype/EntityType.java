package com.aspire.loan.model.uidata.configtype;

public enum EntityType {

    LIMITED_PARTNER("Limited partnership"),
    PRIVATE_LIMITED("Private Limited Company (PTE LTD)"),
    LIMITED_LIABILITY_PARTNER("Limited Liability Partnership"),
    LIMITED_LIABILITY_COMP("Limited Liability Company (LLC)"),
    PUBLIC_LIMITED_COMP("Public Limited Company (PLC)");

    private final String entityDesc;

    EntityType(String entityDesc) {
        this.entityDesc = entityDesc;
    }

    public String getEntityDesc(){
        return entityDesc;
    }

}

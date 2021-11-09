package com.aspire.loan.datagenerator.builder.rest_service;

public class CountryResponseModel {
    private String uuid;
    private String code;
    private String name;
    private String dial_code;
    private Object currency;
    private Boolean is_active;

    public String getUuid() {
        return uuid;
    }

    public String getCode() {
        return code;
    }

    public String getName() {
        return name;
    }

    public String getDialCode() {
        return dial_code;
    }

    public Object getCurrency() {
        return currency;
    }

    public Boolean getActive() {
        return is_active;
    }

    @Override
    public String toString() {
        return "CountryResponseSchema{" +
                "uuid='" + uuid + '\'' +
                ", code='" + code + '\'' +
                ", name='" + name + '\'' +
                ", dial_code='" + dial_code + '\'' +
                ", currency=" + currency +
                ", is_active=" + is_active +
                '}';
    }
}

package com.aspire.loan.databuilder;

import com.aspire.loan.config.AppConfig;
import kong.unirest.GenericType;
import kong.unirest.Unirest;

import java.util.List;

public class CountryService {
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
        return "CountryService{" +
                "uuid='" + uuid + '\'' +
                ", code='" + code + '\'' +
                ", name='" + name + '\'' +
                ", dialCode='" + dial_code + '\'' +
                ", currency=" + currency +
                ", isActive=" + is_active +
                '}';
    }

    public static CountryService getCountryBy(String countryName){
        List<CountryService> body = Unirest
                .get(AppConfig.getApiUrl() + "/v1/countries/all")
                .asObject(new GenericType<List<CountryService>>() {
                })
                .getBody();

        return body
                .stream()
                .filter(c -> c.getActive() && c.getName().equalsIgnoreCase(countryName))
                .findFirst()
                .get();
    }
}

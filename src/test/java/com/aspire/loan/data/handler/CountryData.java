package com.aspire.loan.data.handler;

import com.aspire.loan.config.AppConfig;
import com.aspire.loan.data.CountryResponseSchema;
import kong.unirest.GenericType;
import kong.unirest.Unirest;

import java.util.List;

public class CountryData {

    public CountryResponseSchema fetchCountryByApi(String countryName){
        List<CountryResponseSchema> body = Unirest
                .get(AppConfig.getApiUrl() + "/v1/countries/all")
                .asObject(new GenericType<List<CountryResponseSchema>>() {
                })
                .getBody();

        return body
                .stream()
                .filter(c -> c.getActive() && c.getName().equalsIgnoreCase(countryName))
                .findFirst()
                .get();
    }
}

package com.aspire.loan.data.handler;

import com.aspire.loan.config.AppConfig;
import com.aspire.loan.data.CountryResponseSchema;
import kong.unirest.GenericType;
import kong.unirest.Unirest;

import java.util.List;
import java.util.stream.Collectors;

public class CountryData {

    public List<CountryResponseSchema> fetchActiveCountryByApi(){
        return Unirest
                .get(AppConfig.getApiUrl() + "/v1/countries/all")
                .asObject(new GenericType<List<CountryResponseSchema>>() {
                })
                .getBody()
                .stream()
                .filter(c -> c.getActive())
                .collect(Collectors.toList());
    }
}

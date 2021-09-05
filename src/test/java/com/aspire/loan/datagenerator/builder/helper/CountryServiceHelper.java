package com.aspire.loan.datagenerator.builder.helper;

import com.aspire.loan.config.AppConfig;
import kong.unirest.GenericType;
import kong.unirest.Unirest;

import java.util.List;
import java.util.stream.Collectors;

public class CountryServiceHelper {

    public List<CountryResponse> fetchActiveCountryByApi(){
        return Unirest
                .get(AppConfig.getApiUrl() + "/v1/countries/all")
                .asObject(new GenericType<List<CountryResponse>>() {
                })
                .getBody()
                .stream()
                .filter(c -> c.getActive())
                .collect(Collectors.toList());
    }
}

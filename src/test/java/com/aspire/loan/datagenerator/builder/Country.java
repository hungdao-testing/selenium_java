package com.aspire.loan.datagenerator.builder;

import com.aspire.loan.config.AppConfig;
import com.aspire.loan.model.apidata.CountryResponse;
import kong.unirest.GenericType;
import kong.unirest.Unirest;

import java.util.List;
import java.util.stream.Collectors;

public class Country {

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

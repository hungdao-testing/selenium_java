package com.aspire.loan.datagenerator.builder.rest_service;

import com.aspire.loan.config.AppConfig;
import kong.unirest.GenericType;
import kong.unirest.Unirest;

import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.stream.Collectors;

public class CountryService {

    public List<CountryResponseModel> fetchActiveCountryByApi(){
        return Unirest
                .get(AppConfig.getApiUrl() + "/v1/countries/all")
                .asObject(new GenericType<List<CountryResponseModel>>() {
                })
                .getBody()
                .stream()
                .filter(c -> c.getActive())
                .collect(Collectors.toList());
    }

    public String getCountryCodeByName(String countryName){
        Map<String, String> countries = new HashMap<>();
        for (String iso : Locale.getISOCountries()) {
            Locale l = new Locale("", iso);
            countries.put(l.getDisplayCountry(), iso);
        }
        return countries.get(countryName);
    }
}

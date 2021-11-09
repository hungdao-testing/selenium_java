package com.aspire.loan.datagenerator.builder.rest_service;

import com.aspire.loan.config.AppConfig;
import com.aspire.loan.datagenerator.builder.BuilderSetup;
import com.aspire.loan.helpers.service.RestServiceHelper;
import kong.unirest.json.JSONArray;

import java.util.Map;
import java.util.stream.Stream;

public class TurnoverBandService {

    private JSONArray fetchAnnualRevenue(String country){
        return RestServiceHelper
                .getArrayResponseData( AppConfig.getApiUrl() + "/v1/turnover-bands", Map.of("country", country));

    }

    public String getRandomAnnualRevenueByCountryCode(String country){

        JSONArray revenues = fetchAnnualRevenue(country);
        return Stream.of(fetchAnnualRevenue(country))
                .map(e -> e.getJSONObject(BuilderSetup.randomNumber(0, revenues.length())).get("range"))
                .findFirst()
                .get()
                .toString();

    }
}

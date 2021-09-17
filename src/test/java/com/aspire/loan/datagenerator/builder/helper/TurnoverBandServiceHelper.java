package com.aspire.loan.datagenerator.builder.helper;

import com.aspire.loan.config.AppConfig;
import com.aspire.loan.datagenerator.builder.BuilderSetup;
import kong.unirest.Unirest;
import kong.unirest.json.JSONArray;
import java.util.stream.Stream;

public class TurnoverBandServiceHelper {

    private static final String url = AppConfig.getApiUrl() + "/v1/turnover-bands?country=%s";

    private JSONArray fetchAnnualRevenue(String country){
        return Unirest.get(String.format(url, country))
                .header("x-aspire-application", "CNSING")
                .asJson()
                .getBody()
                .getArray();
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

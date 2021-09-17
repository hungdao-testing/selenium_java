package com.aspire.loan.datagenerator.builder.helper;

import com.aspire.loan.config.AppConfig;
import com.aspire.loan.datagenerator.builder.BuilderSetup;
import kong.unirest.Unirest;
import kong.unirest.json.JSONArray;

import java.util.stream.Stream;

public class HearAboutUsServiceHelper {

    private static final String url = AppConfig.getApiUrl() + "/v1/options?type=heard_about";

    private JSONArray fetchHearAboutUsByApi() {

        return Unirest.get(url)
                .header("x-aspire-application", "CNSING")
                .asJson()
                .getBody()
                .getArray();


    }

    public String getRandomHearAboutUsOption(){
        JSONArray hearAboutUsResponse = fetchHearAboutUsByApi();
        return Stream.of(hearAboutUsResponse)
                .map(e -> e.getJSONObject(BuilderSetup.randomNumber(0, hearAboutUsResponse.length())))
                .map(e -> e.get("name"))
                .findFirst()
                .get()
                .toString();
    }
}

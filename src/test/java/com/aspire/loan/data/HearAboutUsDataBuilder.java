package com.aspire.loan.data;

import com.aspire.loan.config.AppConfig;
import kong.unirest.Unirest;
import kong.unirest.json.JSONArray;

import java.util.stream.Stream;

public class HearAboutUsDataBuilder{

    private static final String url = AppConfig.getApiUrl() + "/v1/options?type=heard_about";


    protected static String fetchHearAboutUsByApi() {

        JSONArray hearAboutUsResponse = Unirest.get(url)
                .header("x-aspire-application", "CNSING")
                .asJson()
                .getBody()
                .getArray();

        return Stream.of(hearAboutUsResponse)
                .map(e -> e.getJSONObject(BuilderConfig.faker.number().numberBetween(0, hearAboutUsResponse.length())))
                .map(e -> e.get("name"))
                .findFirst()
                .get()
                .toString();
    }
}

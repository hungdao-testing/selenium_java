package com.aspire.loan.data.handler;

import com.aspire.loan.config.AppConfig;
import com.github.javafaker.Faker;
import kong.unirest.Unirest;
import kong.unirest.json.JSONArray;

import java.util.Locale;
import java.util.stream.Stream;

public class HearAboutUsData {

    private static final String url = AppConfig.getApiUrl() + "/v1/options?type=heard_about";
    private static final Faker faker = new Faker(new Locale("en_SG"));

    public String fetchHearAboutUsByApi() {

        JSONArray hearAboutUsResponse = Unirest.get(url)
                .header("x-aspire-application", "CNSING")
                .asJson()
                .getBody()
                .getArray();

        return Stream.of(hearAboutUsResponse)
                .map(e -> e.getJSONObject(faker.number().numberBetween(0, hearAboutUsResponse.length())))
                .map(e -> e.get("name"))
                .findFirst()
                .get()
                .toString();
    }
}

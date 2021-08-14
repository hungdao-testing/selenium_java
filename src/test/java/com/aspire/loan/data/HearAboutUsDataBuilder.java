package com.aspire.loan.data.databuilder;

import com.aspire.loan.config.AppConfig;
import kong.unirest.Unirest;
import kong.unirest.json.JSONArray;

import java.util.stream.Stream;

public class HearAboutUsDataBuilder {

    private static final String url = AppConfig.getApiUrl() + "/v1/options?type=heard_about";

    public String fetchHearAboutUsByApi() {
        JSONArray hearAboutUsResponse = Unirest.get(url)
                .header("x-aspire-application", "CNSING")
                .asJson()
                .getBody()
                .getArray();

        return Stream.of(hearAboutUsResponse)
                .map(e -> e.getJSONObject(DataBuilderConfig.faker.number().numberBetween(0, hearAboutUsResponse.length())))
                .map(e -> e.get("name"))
                .findFirst()
                .get()
                .toString();
    }
}

package com.aspire.loan.datagenerator.builder.helper;

import com.aspire.loan.config.AppConfig;
import com.aspire.loan.datagenerator.builder.BuilderSetup;
import kong.unirest.Unirest;
import kong.unirest.json.JSONArray;

import java.util.stream.Stream;

public class EntityCategoryServiceHelper {

    private static final String url = AppConfig.getApiUrl() + "/v1/options?type=registration_type&group=1";

    private JSONArray fetchEntityCategoryByApi() {

        return Unirest.get(url)
                .header("x-aspire-application", "CNSING")
                .asJson()
                .getBody()
                .getArray();
    }

    public String getRandomEntityCategory(){
        JSONArray entityCategoryResponse = fetchEntityCategoryByApi();
        return Stream.of(entityCategoryResponse)
                .map(e -> e.getJSONObject(BuilderSetup.randomNumber(0, entityCategoryResponse.length())))
                .map(e -> e.get("name"))
                .findFirst()
                .get()
                .toString();
    }
}

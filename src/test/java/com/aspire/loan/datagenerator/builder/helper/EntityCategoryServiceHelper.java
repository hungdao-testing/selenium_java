package com.aspire.loan.datagenerator.builder.helper;

import com.aspire.loan.config.AppConfig;
import com.aspire.loan.datagenerator.builder.BuilderSetup;
import kong.unirest.Unirest;
import kong.unirest.json.JSONArray;
import kong.unirest.json.JSONObject;

import java.util.HashMap;
import java.util.Map;
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

    public Map<String, String> getRandomEntityCategory(){
        Map<String, String> entityMap = new HashMap<>();
        JSONArray entityCategoryByApi = fetchEntityCategoryByApi();

        JSONObject entityJson = Stream.of(entityCategoryByApi)
                .map(e -> e.getJSONObject(BuilderSetup.randomNumber(0, entityCategoryByApi.length())))
                .findAny().get();

        String entityType = Stream.of(entityJson.getJSONArray("children"))
                .map(e -> e.getJSONObject(BuilderSetup.randomNumber(0, e.length())))
                .map(e -> e.get("name"))
                .findFirst()
                .get()
                .toString();


        entityMap.put("entityCategory", entityJson.get("name").toString());
        entityMap.put("entityType", entityType);
        return entityMap;
    }

}

package com.aspire.loan.datagenerator.builder.rest_service;

import com.aspire.loan.config.AppConfig;
import com.aspire.loan.datagenerator.builder.BuilderSetup;
import com.aspire.loan.helpers.service.RestServiceHelper;
import kong.unirest.json.JSONArray;
import kong.unirest.json.JSONObject;

import java.util.HashMap;
import java.util.Map;
import java.util.stream.Stream;

public class IndustryService {

    private JSONArray fetchIndustryTypesByCountry(String country){
        return RestServiceHelper
                .getArrayResponseData(AppConfig.getApiUrl() + "/v1/industry-types", Map.of("country", country));
    }

    public Map<String, String> getRandomIndustryTypeByCountryCode(String country){
        Map<String, String> industryMap = new HashMap<>();
        JSONArray industryType = fetchIndustryTypesByCountry(country);

        JSONObject industryJson = Stream.of(industryType)
                .map(e -> e.getJSONObject(BuilderSetup.randomNumber(0, industryType.length())))
                .findAny().get();

        String subIndustry = Stream.of(industryJson.getJSONArray("sub_industry_types"))
                                    .map(e -> e.getJSONObject(BuilderSetup.randomNumber(0, e.length())))
                                    .map(e -> e.get("type"))
                                    .findFirst()
                                    .get()
                                    .toString();


        industryMap.put("industry", industryJson.get("type").toString());
        industryMap.put("subIndustry", subIndustry);
        return industryMap;
    }

}

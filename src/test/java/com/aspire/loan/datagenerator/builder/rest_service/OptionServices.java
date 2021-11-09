package com.aspire.loan.datagenerator.builder.rest_service;

import com.aspire.loan.config.AppConfig;
import com.aspire.loan.datagenerator.builder.BuilderSetup;
import com.aspire.loan.helpers.service.RestServiceHelper;
import kong.unirest.json.JSONArray;
import kong.unirest.json.JSONObject;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class OptionServices {

    private JSONArray fetchOption(Map<String, Object> optionParams){
        return RestServiceHelper
                .getArrayResponseData(AppConfig.getApiUrl() + "/v1/options", optionParams);

    }

    public List<String> getRandomSolutionOptions(){
        JSONArray solutions = fetchOption(Map.of("type", "purpose_person"));
        return IntStream.of(1, 3)
                .mapToObj(solutions::getJSONObject)
                .map(e -> e.get("name").toString())
                .collect(Collectors.toList());

    }

    public String getRandomHearAboutUsOption(){
        JSONArray hearAboutUsResponse = fetchOption(Map.of("type", "heard_about"));
        return Stream.of(hearAboutUsResponse)
                .map(e -> e.getJSONObject(BuilderSetup.randomNumber(0, hearAboutUsResponse.length())))
                .map(e -> e.get("name"))
                .findFirst()
                .get()
                .toString();
    }

    public String getRandomNumberEmployee(){
        JSONArray numberEmployees = fetchOption(Map.of("type","employee_segment"));
        return Stream.of(numberEmployees)
                .map(e -> e.getJSONObject(BuilderSetup.randomNumber(0, numberEmployees.length())))
                .map(e -> e.get("name"))
                .findFirst()
                .get()
                .toString();
    }

    public Map<String, String> getRandomEntityCategory(){
        Map<String, String> entityMap = new HashMap<>();
        JSONArray entityCategoryByApi = fetchOption(Map.of("type", "registration_type", "group", 1));

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

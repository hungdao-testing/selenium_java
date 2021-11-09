package com.aspire.loan.datagenerator.builder.rest_service;

import com.aspire.loan.config.AppConfig;
import com.aspire.loan.datagenerator.builder.BuilderSetup;
import kong.unirest.Unirest;
import kong.unirest.json.JSONArray;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class OptionServices {

    private JSONArray fetchOptionForType(String type){
        return Unirest.get(AppConfig.getApiUrl() + "/v1/options")
                .queryString("type", type)
                .header("x-aspire-application", "CNSING")
                .asJson()
                .getBody()
                .getArray();
    }

    public List<String> getRandomSolutionOptions(){
        Map<String, String> industryMap = new HashMap<>();
        return IntStream.of(1, 3)
                .mapToObj(i -> fetchOptionForType("purpose_person").getJSONObject(i)).map(e -> e.get("name").toString())
                .collect(Collectors.toList());

    }

    public String getRandomHearAboutUsOption(){
        JSONArray hearAboutUsResponse = fetchOptionForType("heard_about");
        return Stream.of(hearAboutUsResponse)
                .map(e -> e.getJSONObject(BuilderSetup.randomNumber(0, hearAboutUsResponse.length())))
                .map(e -> e.get("name"))
                .findFirst()
                .get()
                .toString();
    }

    public String getRandomNumberEmployee(){
        JSONArray numberEmployees = fetchOptionForType("employee_segment");
        return Stream.of(numberEmployees)
                .map(e -> e.getJSONObject(BuilderSetup.randomNumber(0, numberEmployees.length())))
                .map(e -> e.get("name"))
                .findFirst()
                .get()
                .toString();
    }


}

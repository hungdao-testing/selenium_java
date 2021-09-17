package com.aspire.loan.datagenerator.builder.helper;

import com.aspire.loan.config.AppConfig;
import kong.unirest.Unirest;
import kong.unirest.json.JSONArray;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class OptionServiceHelper {

    private static final String url = AppConfig.getApiUrl() + "/v1/options?type=purpose_person";

    private JSONArray fetchOptionPurposePerson(){
        return Unirest.get(url)
                .header("x-aspire-application", "CNSING")
                .asJson()
                .getBody()
                .getArray();
    }

    public List<String> getRandomSolutionOptions(){
        Map<String, String> industryMap = new HashMap<>();
        return IntStream.of(1, 3)
                .mapToObj(i -> fetchOptionPurposePerson().getJSONObject(i)).map(e -> e.get("name").toString())
                .collect(Collectors.toList());

    }

}

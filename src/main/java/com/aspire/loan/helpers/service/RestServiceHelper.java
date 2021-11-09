package com.aspire.loan.helpers.service;

import com.aspire.loan.config.AppConfig;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import kong.unirest.Unirest;
import kong.unirest.json.JSONArray;
import kong.unirest.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.util.Map;

public class RestServiceHelper {

    protected Logger LOGGER = LoggerFactory.getLogger(this.getClass().getSimpleName());
    public Gson gson = new GsonBuilder().setPrettyPrinting().create();

    public static int getResponseStatus(String url, Map<String, String> headers){
        return Unirest
                .get(url)
                .headers(headers)
                .asJson()
                .getStatus();
    }

    public static JSONArray getArrayResponseData(String url, Map<String, Object> queryParams){
        return Unirest.get(AppConfig.getApiUrl() + "/v1/options")
                .queryString(queryParams)
                .header("x-aspire-application", "CNSING")
                .asJson()
                .getBody()
                .getArray();
    }

    public static JSONObject sendPost(String url, String payload){
        return Unirest.post(url)
                .headers(Map.of("Content-Type", "application/json", "x-aspire-application", "CNSING"))
                .body(payload)
                .asJson()
                .getBody()
                .getObject();
    }
}

package com.aspire.loan.helpers;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Map;

public class ServiceHelper {

    private static String parseResponseData(HttpURLConnection huc) throws IOException {
        InputStream inputStream;
        if (200 <= huc.getResponseCode() && huc.getResponseCode() <= 299) {
            inputStream = huc.getInputStream();
        } else {
            inputStream = huc.getErrorStream();
        }

        BufferedReader in = new BufferedReader(new InputStreamReader(inputStream));
        StringBuilder response = new StringBuilder();
        String currentLine;
        while ((currentLine = in.readLine()) != null)
            response.append(currentLine);

        in.close();

        return response.toString();
    }

    public static int getResponseCode(String urlString) throws IOException {
        URL url = new URL(urlString);
        HttpURLConnection huc = (HttpURLConnection)url.openConnection();
        huc.setRequestMethod("GET");
        huc.connect();
        return huc.getResponseCode();
    }

    public static String sendGetRequestWithParams(String urlString, String parameters) throws IOException {
        InputStream inputStream;
        JsonObject params = new JsonParser().parse(parameters).getAsJsonObject();
        URL url = new URL(urlString);
        HttpURLConnection huc = (HttpURLConnection)url.openConnection();
        huc.setRequestMethod("GET");
        for(Map.Entry<String,JsonElement> entry : params.entrySet()){
            huc.setRequestProperty(entry.getKey(), entry.getValue().toString());
        }
        huc.connect();

        return parseResponseData(huc);
    }

    public static String sendGetRequestWithoutParams(String urlString) throws IOException {
        InputStream inputStream;
        URL url = new URL(urlString);
        HttpURLConnection huc = (HttpURLConnection)url.openConnection();
        huc.setRequestMethod("GET");
        huc.connect();
        return parseResponseData(huc);
    }


}

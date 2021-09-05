package com.aspire.loan.ui.common.authentication;

import com.aspire.loan.config.AppConfig;
import com.aspire.loan.model.uidata.RegistrationInfo;
import com.aspire.loan.model.apidata.RegistrationPayload;
import com.aspire.loan.service.AbstractBaseService;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import kong.unirest.Unirest;
import kong.unirest.json.JSONObject;
import org.testng.Assert;

import java.util.Map;

public class ApiRegistration extends AbstractBaseService implements IRegistration{

    @Override
    public void create(RegistrationInfo registrationInfo) {
        LOGGER.info("=== Create Account By API ==== ");

        RegistrationPayload payload = new RegistrationPayload(
                registrationInfo.getPersonalInfo().getFullName(),
                registrationInfo.getPersonalInfo().getEmail(),
                registrationInfo.getPersonalInfo().getDialCode() + registrationInfo.getPersonalInfo().getPhone(),
                true,
                registrationInfo.getHearAboutUs()
        );
        Gson gson = new GsonBuilder().setPrettyPrinting().create();

        JSONObject resp = Unirest
                .post(AppConfig.getApiUrl() + "/v1/auth/register")
                .headers(Map.of("Content-Type", "application/json", "x-aspire-application", "CNSING"))
                .body(gson.toJson(payload))
                .asJson()
                .getBody()
                .getObject();

        Assert.assertTrue(resp.has("access_token") && !resp.get("access_token").toString().isEmpty());

        //Make sure the created user could log-in by API successfully.
        String accessToken = resp.get("access_token").toString();
        int statusCode = Unirest
                            .get(AppConfig.getApiUrl() + "/v1/auth")
                            .headers(Map.of("authorization", String.format("Bearer %s", accessToken), "x-aspire-application", "CNSING"))
                            .asJson()
                            .getStatus();
        Assert.assertEquals(200, statusCode);
        LOGGER.info(" User {} is created successfully ", registrationInfo.toString());
    }
}

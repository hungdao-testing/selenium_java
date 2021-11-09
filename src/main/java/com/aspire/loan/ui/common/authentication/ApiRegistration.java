package com.aspire.loan.ui.common.authentication;

import com.aspire.loan.config.AppConfig;
import com.aspire.loan.models.apidata.RegistrationPayload;
import com.aspire.loan.helpers.service.RestServiceHelper;
import kong.unirest.json.JSONObject;
import org.testng.Assert;

import java.util.Map;

public class ApiRegistration extends RestServiceHelper implements IRegistration{



    @Override
    public void create(RegistrationInfo registrationInfo) {

        RegistrationPayload payload = new RegistrationPayload(
                registrationInfo.getPersonalInfo().getFullName(),
                registrationInfo.getPersonalInfo().getEmail(),
                registrationInfo.getPersonalInfo().getDialCode() + registrationInfo.getPersonalInfo().getPhone(),
                true,
                registrationInfo.getHearAboutUs()
        );

        JSONObject resp = sendPost(AppConfig.getApiUrl() + "/v1/auth/register", gson.toJson(payload));

        //Make sure the created user could log-in by API successfully.
        String accessToken = resp.get("access_token").toString();
        Map<String, String> headers = Map.of(
                "authorization", String.format("Bearer %s", accessToken),
                "x-aspire-application", "CNSING"
        );
        int statusCode = RestServiceHelper.getResponseStatus(AppConfig.getApiUrl() + "/v1/auth", headers);

        Assert.assertEquals(200, statusCode);
        LOGGER.info(" User {} is created successfully ", registrationInfo.toString());
    }
}

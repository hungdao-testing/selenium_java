package com.aspire.loan.databuilder;

import com.aspire.loan.config.AppConfig;
import com.aspire.loan.services.CountryService;
import com.aspire.loan.data.Personal;
import kong.unirest.Unirest;
import kong.unirest.json.JSONException;
import kong.unirest.json.JSONObject;
import java.util.Map;


public class PersonalDataBuilder extends DataBuilderConfig {

    private static final String checkNewUserUrl = AppConfig.getApiUrl() + "/v1/auth/check-new-person";

    private Personal.PersonalBuilder aUser() {
        CountryService defaultCountry = CountryService.getCountryBy("Singapore");
        return Personal.aUser()
                .withCountry(defaultCountry.getName())
                .withDialCode(defaultCountry.getDialCode());
    }

    public Personal generateData() {
        while (true) {
            String fullName = faker.funnyName().name();
            String email = faker.internet().emailAddress();
            String phone = faker.phoneNumber().cellPhone().replaceAll("[^0-9]+", "");
            String formattedPhone = CountryService.getCountryBy("Singapore").getDialCode() + "" + phone;

            String payload = String.format(
                    "{\"full_name\": \"%s\",\"email\": \"%s\",\"phone\": \"%s\"}", fullName, email, formattedPhone);

            JSONObject resp = Unirest.post(checkNewUserUrl)
                    .headers(Map.of("Content-Type", "application/json", "x-aspire-application", "CNSING"))
                    .body(payload)
                    .asJson()
                    .getBody()
                    .getObject();


            if (resp.has("check") && !Boolean.parseBoolean(resp.get("check").toString())) {
                return new PersonalDataBuilder()
                        .aUser()
                        .withFullName(fullName)
                        .withPhone(phone)
                        .withEmail(email)
                        .build();
            } else if (!resp.has("check")) {
                throw new JSONException("Missing key 'check'");
            }
        }

    }


}



package com.aspire.loan.databuilder;

import com.aspire.loan.config.AppConfig;
import com.aspire.loan.data.Personal;
import com.github.javafaker.Faker;
import kong.unirest.Unirest;
import kong.unirest.json.JSONException;
import kong.unirest.json.JSONObject;

import java.util.Locale;
import java.util.Map;


public class PersonalBuilder {
    static Faker faker = new Faker(new Locale("en_SG"));

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

            JSONObject resp = Unirest.post(AppConfig.getApiUrl() + "/v1/auth/check-new-person")
                    .headers(Map.of("Content-Type", "application/json", "x-aspire-application", "CNSING"))
                    .body(payload)
                    .asJson()
                    .getBody()
                    .getObject();


            if (resp.has("check") && !Boolean.parseBoolean(resp.get("check").toString())) {
                return new PersonalBuilder()
                        .aUser()
                        .withFullName(fullName)
                        .withPhone(formattedPhone)
                        .withEmail(email)
                        .build();
            } else if (!resp.has("check")) {
                throw new JSONException("Missing key 'check'");
            }
        }

    }


}




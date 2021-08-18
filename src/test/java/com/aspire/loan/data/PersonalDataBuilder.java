package com.aspire.loan.data;

import com.aspire.loan.config.AppConfig;
import com.aspire.loan.data.handler.CountryData;
import com.github.javafaker.Faker;
import kong.unirest.Unirest;
import kong.unirest.json.JSONException;
import kong.unirest.json.JSONObject;

import java.util.Map;


public class PersonalDataBuilder {

    protected static Faker faker = new Faker();

    private CountryResponseSchema getDefaultCountry() {
        return new CountryData()
                .fetchActiveCountryByApi()
                .stream()
                .filter(c -> c.getName().equalsIgnoreCase("Singapore"))
                .findFirst()
                .get();
    };

    private PersonalInfo.PersonalInfoBuilder aUser() {
        return PersonalInfo.aUser()
                .withCountry(getDefaultCountry().getName())
                .withDialCode(getDefaultCountry().getDialCode());
    };

    protected PersonalInfo generateValidData() {
        String checkNewUserUrl = AppConfig.getApiUrl() + "/v1/auth/check-new-person";
        while (true) {
            String fullName = faker.funnyName().name();
            String email = faker.internet().emailAddress();
            String phone = faker.phoneNumber().cellPhone().replaceAll("[^0-9]+", "");
            String formattedPhone = getDefaultCountry().getDialCode() + "" + phone;

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

    protected PersonalInfo generateInvalidData(String name, String email, String phone) {
        CountryResponseSchema country= new CountryData()
                .fetchActiveCountryByApi()
                .stream()
                .findAny()
                .get();

        return new PersonalDataBuilder().aUser()
                .withCountry(country.getName())
                .withDialCode(country.getDialCode())
                .withPhone(phone)
                .withEmail(email)
                .withFullName(name)
                .build();
    }

}




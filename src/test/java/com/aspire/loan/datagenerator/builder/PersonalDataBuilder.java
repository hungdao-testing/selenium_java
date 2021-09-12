package com.aspire.loan.datagenerator.builder;

import com.aspire.loan.config.AppConfig;
import com.aspire.loan.datagenerator.builder.helper.CountryResponse;
import com.aspire.loan.datagenerator.builder.helper.CountryServiceHelper;
import com.aspire.loan.model.uidata.PersonalInfo;
import kong.unirest.Unirest;
import kong.unirest.json.JSONException;
import kong.unirest.json.JSONObject;

import java.util.Map;


public class PersonalDataBuilder {

    private CountryResponse getDefaultCountry() {
        return new CountryServiceHelper()
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

    public PersonalInfo generateValidData() {
        String checkNewUserUrl = AppConfig.getApiUrl() + "/v1/auth/check-new-person";
        while (true) {
            String fullName = BuilderSetup.faker.name().fullName();
            String email = BuilderSetup.faker.internet().emailAddress();
            String phone = BuilderSetup.faker.phoneNumber().cellPhone().replaceAll("[^0-9]+", "");
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
                        .withDob(TimeBuilder.getDob())
                        .withGender(BuilderSetup.faker.demographic().sex())
                        .build();
            } else if (!resp.has("check")) {
                throw new JSONException("Missing key 'check'");
            }
        }

    }

    public PersonalInfo generatePersonalDataWith(String name, String email, String phone) {
        CountryResponse country= new CountryServiceHelper()
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
                .withDob(TimeBuilder.getDob())
                .withGender(BuilderSetup.faker.demographic().sex())
                .build();
    }

}




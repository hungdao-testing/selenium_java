package com.aspire.loan.models.uidata;

import lombok.Builder;
import lombok.Data;

import java.util.Map;

@Data
@Builder(builderMethodName = "aUser", setterPrefix = "with", toBuilder = true)
public class PersonalModel {

    private String fullName;
    private String email;
    private String phone;
    private String country;
    private String dialCode;
    private Map<String, String> dob;
    private String gender;

}

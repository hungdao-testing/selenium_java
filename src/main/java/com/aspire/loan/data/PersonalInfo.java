package com.aspire.loan.data;

import lombok.Builder;
import lombok.Data;

@Data
@Builder(builderMethodName = "aUser", setterPrefix = "with", toBuilder = true)
public class PersonalInfo {

    private String fullName;
    private String email;
    private String phone;
    private String country;
    private String dialCode;

}

package com.aspire.loan.data;

import lombok.Builder;
import lombok.Data;

@Data
@Builder(builderMethodName = "registrationData", setterPrefix = "with", toBuilder = true)
public class RegistrationInformation {

    private PersonalInfo personalInfo;
    private String hearAboutUs;
    private String promoCode;
}

package com.aspire.loan.model.uidata;

import lombok.Builder;
import lombok.Data;

@Data
@Builder(builderMethodName = "registrationData", setterPrefix = "with", toBuilder = true)
public class RegistrationInfo {

    private PersonalInfo personalInfo;
    private String hearAboutUs;
    private String promoCode;
}

package com.aspire.loan.ui.common.authentication;

import com.aspire.loan.models.uidata.PersonalModel;
import lombok.Builder;
import lombok.Data;

@Data
@Builder(builderMethodName = "registrationData", setterPrefix = "with", toBuilder = true)
public class RegistrationInfo {

    private PersonalModel personalInfo;
    private String hearAboutUs;
    private String promoCode;
}

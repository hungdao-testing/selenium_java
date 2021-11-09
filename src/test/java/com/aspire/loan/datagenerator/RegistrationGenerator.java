package com.aspire.loan.datagenerator;

import com.aspire.loan.datagenerator.builder.PersonalInfoBuilder;
import com.aspire.loan.datagenerator.builder.rest_service.OptionServices;
import com.aspire.loan.models.uidata.PersonalModel;
import com.aspire.loan.ui.common.authentication.RegistrationInfo;
import com.aspire.loan.helpers.service.OtpService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class RegistrationGenerator {

    protected static Logger LOGGER = LoggerFactory.getLogger(RegistrationGenerator.class.getSimpleName());

    private static String getHearAboutUs() {
        return new OptionServices().getRandomHearAboutUsOption();
    }

    protected static String generatePromoCode(){
        return "";
    }

    public static String getOtp() {
        return new OtpService().getOtp();
    }

    public static RegistrationInfo generateRegistrationDataWith(String name, String email, String phone){
        PersonalModel user = new PersonalInfoBuilder().generatePersonalDataWith(name, email, phone);
        return RegistrationInfo
                .registrationData()
                .withPersonalInfo(user)
                .withHearAboutUs(getHearAboutUs())
                .withPromoCode(generatePromoCode())
                .build();
    }

    public static RegistrationInfo generateValidRegistrationData(){
        RegistrationInfo registrationData = RegistrationInfo
                .registrationData()
                .withPersonalInfo(new PersonalInfoBuilder().generateValidData())
                .withHearAboutUs(getHearAboutUs())
                .withPromoCode(generatePromoCode())
                .build();
        LOGGER.info("Registration data: {} ", registrationData);
        return registrationData;
    }
}


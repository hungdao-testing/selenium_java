package com.aspire.loan.datagenerator;

import com.aspire.loan.datagenerator.builder.PersonalDataBuilder;
import com.aspire.loan.model.uidata.PersonalInfo;
import com.aspire.loan.model.uidata.RegistrationInfo;
import com.aspire.loan.datagenerator.builder.helper.HearAboutUsServiceHelper;
import com.aspire.loan.service.OtpService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class RegistrationDataGenerator {

    protected static Logger LOGGER = LoggerFactory.getLogger(RegistrationDataGenerator.class.getSimpleName());

    protected static String getHearAboutUs() {
        String aboutUs = new HearAboutUsServiceHelper().fetchHearAboutUsByApi();
        LOGGER.info("Getting About Us options by API: {}", aboutUs);
        return aboutUs;
    }

    protected static String generatePromoCode(){
        return "";
    }

    public static String getOtp() {
        LOGGER.info("Getting OTP code");
        return new OtpService().getOtp();
    }

    public static RegistrationInfo generateRegistrationDataWith(String name, String email, String phone){
        PersonalInfo user = new PersonalDataBuilder().generatePersonalDataWith(name, email, phone);
        return RegistrationInfo
                .registrationData()
                .withPersonalInfo(user)
                .withHearAboutUs(getHearAboutUs())
                .withPromoCode(generatePromoCode())
                .build();
    }
    public static RegistrationInfo generateValidRegistrationData(){
        return RegistrationInfo
                .registrationData()
                .withPersonalInfo(new PersonalDataBuilder().generateValidData())
                .withHearAboutUs(new HearAboutUsServiceHelper().fetchHearAboutUsByApi())
                .withPromoCode(generatePromoCode())
                .build();
    }
}


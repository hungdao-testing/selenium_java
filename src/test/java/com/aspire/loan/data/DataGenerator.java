package com.aspire.loan.data;

import com.aspire.loan.data.handler.HearAboutUsData;
import com.aspire.loan.service.OtpService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class DataGenerator {

    protected static Logger LOGGER = LoggerFactory.getLogger(DataGenerator.class.getSimpleName());

    protected static String getHearAboutUs() {
        String aboutUs = new HearAboutUsData().fetchHearAboutUsByApi();
        LOGGER.info("Getting About Us options by API: {}", aboutUs);
        return aboutUs;
    }

    protected static PersonalInfo getValidPersonalInfo() {
        LOGGER.info("Generating Valid data for Personal");
        return new PersonalDataBuilder().generateValidData();
    }

    protected static PersonalInfo getInvalidPersonalInfoWith(String name, String email, String phone) {
        LOGGER.info("Generating Invalid Personal Data with name={}, email={}, phone={}", name, email, phone);
        return new PersonalDataBuilder().generateInvalidData(name, email, phone);
    }

    protected static String generatePromoCode(){
        return "";
    }

    public static String getOtp() {
        LOGGER.info("Getting OTP code");
        return new OtpService().getOtp();
    }

    public static RegistrationInformation generateInvalidRegistrationData(String name, String email, String phone){
        return RegistrationInformation
                .registrationData()
                .withPersonalInfo(getInvalidPersonalInfoWith(name, email, phone))
                .withHearAboutUs(getHearAboutUs())
                .withPromoCode(generatePromoCode())
                .build();
    }
    public static RegistrationInformation generateValidRegistrationData(){
        return RegistrationInformation
                .registrationData()
                .withPersonalInfo(getValidPersonalInfo())
                .withHearAboutUs(getHearAboutUs())
                .withPromoCode(generatePromoCode())
                .build();
    }
}


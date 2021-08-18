package com.aspire.loan.data;

import com.aspire.loan.data.handler.HearAboutUsData;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class DataGenerator {

    protected static Logger LOGGER = LoggerFactory.getLogger(DataGenerator.class.getSimpleName());

    public static String getHearAboutUs() {
        String aboutUs = new HearAboutUsData().fetchHearAboutUsByApi();
        LOGGER.info("Getting About Us options by API: {}", aboutUs);
        return aboutUs;
    }

    public static PersonalInfo getValidPersonalInfo() {
        LOGGER.info("Generating Valid data for Personal");
        return new PersonalDataBuilder().generateValidData();
    }

    public static String getOtp() {
        LOGGER.info("Getting OTP code");
        return new OtpData().getOtp();
    }

    public static PersonalInfo getInvalidPersonalInfoWith(String name, String email, String phone) {
        LOGGER.info("Generating Invalid Personal Data with name={}, email={}, phone={}", name, email, phone);
        return new PersonalDataBuilder().generateInvalidData(name, email, phone);
    }
}


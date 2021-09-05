package com.aspire.loan.config;


import org.aeonbits.owner.ConfigFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Objects;
import java.util.Properties;


public class AppConfig {

    protected static Logger LOGGER = LoggerFactory.getLogger(AppConfig.class.getSimpleName());

    private static Environment setUpEnv() {
        Properties props = new Properties();
        String envProp = Objects.isNull(System.getProperty("env")) ? "test" : System.getProperty("env");
        String locale = Objects.isNull(System.getProperty("locale")) ? "sg" : System.getProperty("locale");
        props.setProperty("app.locale", locale);
        ConfigFactory.setProperty("env", envProp);
        return ConfigFactory.create(Environment.class, props);
    }


    public static String getBaseUrl() {
        LOGGER.info("BASE URL: {} ", setUpEnv().webUrl());
        return setUpEnv().webUrl();

    }

    public static String getLocaleFile(){
        return setUpEnv().localeFile();
    }

    public static String getApiUrl() {
        return setUpEnv().apiUrl();
    }



}

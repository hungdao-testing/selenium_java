package com.aspire.loan.config;


import org.openqa.selenium.By;

public class GlobalConstants {
    public static final int EXPLICIT_WAIT_TIMEOUT = 15;
    public static final int IMPLICIT_WAIT_TIMEOUT = 30;
    public static final int PAGE_LOAD_TIMEOUT = 120;
    public static final String propertyFilePath= "src/test/resources/test.env.properties";
    public static final String SUCCESSFUL_REGISTRATION_INFORMATION = "You have successfully verified your mobile number. You’re on to a great start!";
    public static final String RECEIVED_INCORPORATION = "We have received your incorporation details. Please check your email to complete the guided setup with our incorporation partners";
    public static final By DROPDOWN_MENU_CSS_SELECTOR = By.cssSelector(".q-menu");
    public static final By DROPDOWN_ITEM_CSS_SELECTOR = By.cssSelector(".q-item__label");
}

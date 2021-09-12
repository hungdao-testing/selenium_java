package com.aspire.loan.specs.onboarding;


import com.aspire.loan.model.uidata.configtype.BusinessRegistrationMethodType;
import com.aspire.loan.specs.BaseTestNG;
import com.aspire.loan.ui.pages.authentication.LoginPage;
import com.aspire.loan.ui.pages.businessregistration.BusinessRegistrationMethodPage;
import com.aspire.loan.ui.pages.onboarding.PersonEditPage;
import com.google.common.util.concurrent.Uninterruptibles;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.concurrent.TimeUnit;

public class BusinessRegistrationMethodTest extends BaseTestNG {

    BusinessRegistrationMethodPage businessRegistrationPage;
    LoginPage loginPage;
    PersonEditPage personEditPage;

    @BeforeClass
    public void setUpPage() {
        this.businessRegistrationPage = new BusinessRegistrationMethodPage(driver);
        this.loginPage = new LoginPage(driver);
        this.personEditPage = new PersonEditPage(driver);
    }

    @BeforeMethod
    public void login(){
        this.loginPage.goTo().isAt();
        this.loginPage.loginByEmail("learning.seven@yopmail.com").waitForOtpSectionLoaded().inputOtp("1234");
    }

    @Test
    public void selectStandardBusiness(){
        this.businessRegistrationPage.isAt();
        this.businessRegistrationPage.selectMethod(BusinessRegistrationMethodType.STANDARD);
        this.personEditPage.isAt();
        this.personEditPage.setDateOfBirth("1", "7", "2003");
        this.personEditPage.setNationality("Singapore").setGender("Male").clickSubmit();
        Uninterruptibles.sleepUninterruptibly(2, TimeUnit.SECONDS);

    }

}

package com.aspire.loan.tests;

import com.aspire.loan.pages.authentication.UserAccountCreationPage;
import com.aspire.loan.service.CountryService;
import com.aspire.loan.utilfunctions.OtpHandlePage;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.io.IOException;


public class RegisterTest extends BaseTest{

    private UserAccountCreationPage userAccountCreationPage;

    @BeforeTest
    public void setUp(){
        this.userAccountCreationPage = new UserAccountCreationPage(driver);
    }

    @Test
    public void test_open_register_page() throws IOException {

        this.userAccountCreationPage.goTo().isAt();
        this.userAccountCreationPage
                .inputFullName("hallua")
                .inputEmail("hallua@yopmail.com")
                .inputPhoneNumber("Cambodia (+855)", "0987234343")
                .inputAboutUs("Brochure")
                .checkPrivacyBox()
                .clickSubmitBtn();
        OtpHandlePage otpHandlePage = new OtpHandlePage(driver, "0987234343");
        otpHandlePage.isAtOtpScreen().inputOtp("1234");


    }
}

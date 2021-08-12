package com.aspire.loan.tests;

import com.aspire.loan.helpers.ServiceHelper;
import com.aspire.loan.pages.authentication.UserAccountCreationPage;
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
                .inputFullName("halluaOne")
                .inputEmail("hallua1@yopmail.com")
                .inputPhoneNumber("Singapore (+65)", "0987234342")
                .inputAboutUs("Brochure")
                .checkPrivacyBox()
                .clickSubmitBtn();
        OtpHandlePage otpHandlePage = new OtpHandlePage(driver, "0987234342");
        otpHandlePage.isAtOtpScreen().inputOtp("1234");


    }
}

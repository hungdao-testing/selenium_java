package com.aspire.loan.tests;

import com.aspire.loan.databuilder.DataManagement;
import com.aspire.loan.pages.authentication.RegistrationPage;
import com.aspire.loan.utilfunctions.OtpHandlePage;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.io.IOException;


public class RegisterTest extends BaseTest{

    private RegistrationPage signUpPage;

    @BeforeTest
    public void setUp(){
        this.signUpPage = new RegistrationPage(driver);
    }

    @Test
    public void test_open_register_page() throws IOException {

        this.signUpPage.goTo().isAt();
        this.signUpPage
                .createUserAccount(DataManagement.getPersonal(), DataManagement.getHearAboutUs());

        OtpHandlePage otpHandlePage = new OtpHandlePage(driver, DataManagement.getPersonal().getPhone());
        otpHandlePage.isAtOtpScreen().inputOtp("1234");


    }
}

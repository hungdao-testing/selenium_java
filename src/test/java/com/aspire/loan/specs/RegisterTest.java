package com.aspire.loan.specs;

import com.aspire.loan.databuilder.DataManagement;
import com.aspire.loan.pages.authentication.RegistrationPage;
import com.aspire.loan.utilfunctions.OtpHandlePage;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;


public class RegisterTest extends BaseTest{

    private RegistrationPage signUpPage;

    @BeforeTest
    public void setUp(){
        this.signUpPage = new RegistrationPage(driver);
    }

    @Test
    public void verify_client_could_register_a_new_account_with_valid_data(){

        this.signUpPage.goTo().isAt();
        this.signUpPage
                .createUserAccount(DataManagement.getPersonal(), DataManagement.getHearAboutUs());

        OtpHandlePage otpHandlePage = new OtpHandlePage(driver, DataManagement.getPersonal().getPhone());
        otpHandlePage.isAtOtpScreen().inputOtp("1234");

    }
}

package com.aspire.loan.specs;

import com.aspire.loan.data.DataManagement;
import com.aspire.loan.ui.pages.authentication.RegistrationPage;
import com.aspire.loan.ui.components.OtpHandle;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;


public class RegisterAccountTest extends BaseTest{

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

        OtpHandle otpHandle = new OtpHandle(driver, DataManagement.getPersonal().getPhone());
        otpHandle.isAtOtpScreen().inputOtp("1234");

    }
}

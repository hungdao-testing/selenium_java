package com.aspire.loan.specs.useraccount;

import com.aspire.loan.data.DataGenerator;
import com.aspire.loan.specs.BaseTest;
import com.aspire.loan.ui.pages.authentication.RegisteredCompletionPage;
import com.aspire.loan.ui.pages.authentication.SignUpPage;
import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;


public class RegisterAccountTest extends BaseTest {

    private SignUpPage signUpPage;
    private RegisteredCompletionPage registeredCompletionPage;

    @BeforeTest
    public void setUp(){
        this.signUpPage = new SignUpPage(driver);
        this.registeredCompletionPage = new RegisteredCompletionPage(driver);
    }

    @Test
    public void verify_client_could_register_a_new_account_with_valid_data(){
        this.signUpPage.goTo().isAt();
        this.signUpPage
                .submitUserInformation(DataGenerator.getPersonalInfo(), DataGenerator.getHearAboutUs())
                .waitForOtpScreenLoaded()
                .inputOtp(DataGenerator.getOtp());

        Assert.assertTrue(registeredCompletionPage.isSuccessfulMessageDisplayed());

    }
}

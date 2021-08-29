package com.aspire.loan.specs.useraccount;

import com.aspire.loan.data.DataGenerator;
import com.aspire.loan.data.RegistrationInformation;
import com.aspire.loan.specs.AbstractBaseTestNG;
import com.aspire.loan.ui.components.OtpHandle;
import com.aspire.loan.ui.pages.authentication.RegisteredCompletionPage;
import com.aspire.loan.ui.pages.authentication.SignUpPage;
import org.testng.Assert;
import org.testng.annotations.*;

import java.util.Arrays;
import java.util.List;


public class RegisterAccountTest extends AbstractBaseTestNG {

    private SignUpPage signUpPage;
    private RegisteredCompletionPage registeredCompletionPage;

    @BeforeTest
    public void setUpPage(){
        LOGGER.info("Setting up page");
        this.signUpPage = new SignUpPage(driver);
        this.registeredCompletionPage = new RegisteredCompletionPage(driver);
    }

    @BeforeMethod
    public void goToRegisterPage(){
        this.signUpPage.goTo().isAt();
    }

    @AfterMethod
    public void tearDown(){
        this.signUpPage.clearLocalStorage();
    }

    @Test
    public void verify_client_could_register_a_new_account_with_valid_data(){
        RegistrationInformation validPersonalData = DataGenerator.generateValidRegistrationData();


        this.signUpPage
                .fillForm(validPersonalData)
                .checkPrivacyBox()
                .clickSubmitBtn();

        OtpHandle otpPage = new OtpHandle(driver, validPersonalData.getPersonalInfo().getPhone());
        otpPage
                .waitForOtpSectionLoaded()
                .inputOtp(DataGenerator.getOtp());

        Assert.assertTrue(registeredCompletionPage.isSuccessfulMessageDisplayed());

    }

    @Test
    public void verify_error_message_before_submitting(){
        List<String> errorTexts = Arrays.asList(
                "Full Name as per ID is required.",
                "Email address must be a valid email address.");

        this.signUpPage.fillForm(DataGenerator
                .generateInvalidRegistrationData(" ", "invalid@", "1234"));
        Assert.assertEquals(this.signUpPage.getErrorMessage(), errorTexts);
    }

    @Test
    public void verify_error_message_after_submitting(){
        List<String> errorTexts = Arrays.asList("Incorrect phone format for phone., The phone format is invalid.");
        this.signUpPage
                .fillForm(
                        DataGenerator.generateInvalidRegistrationData("Dave", "test@yopmail.com", "1234"))
                .checkPrivacyBox()
                .clickSubmitBtn();

        Assert.assertEquals(this.signUpPage.getNotificationErrorMessage(), "The given data was invalid.");
        Assert.assertEquals(this.signUpPage.getErrorMessage(), errorTexts);
    }
}

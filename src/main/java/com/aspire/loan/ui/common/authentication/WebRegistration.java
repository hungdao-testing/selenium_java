package com.aspire.loan.ui.common.authentication;

import com.aspire.loan.helpers.service.OtpService;
import com.aspire.loan.ui.BasePage;
import com.aspire.loan.ui.components.OtpHandle;
import com.aspire.loan.ui.pages.authentication.RegisteredCompletionPage;
import com.aspire.loan.ui.pages.authentication.SignUpPage;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;

public class WebRegistration extends BasePage implements IRegistration{

    public WebRegistration(WebDriver driver) {
        super(driver);
    }

    @Override
    public void create(RegistrationInfo registrationInfo) {
        LOGGER.info(" === Create an account by UI === ");
        SignUpPage signUpPage = new SignUpPage(driver);
        signUpPage.goTo().isAt();
        signUpPage
                .fillForm(registrationInfo)
                .checkPrivacyBox()
                .clickSubmitBtn();

        OtpHandle otpHandle = new OtpHandle(driver, registrationInfo.getPersonalInfo().getPhone());
        otpHandle.waitForOtpSectionLoaded().inputOtp(new OtpService().getOtp());

        RegisteredCompletionPage registeredCompletionPage = new RegisteredCompletionPage(driver);
        Assert.assertTrue(registeredCompletionPage.isSuccessfulMessageDisplayed());
        registeredCompletionPage.clickContinueButton();
    }
}

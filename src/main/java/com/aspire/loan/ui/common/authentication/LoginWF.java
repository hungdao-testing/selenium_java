package com.aspire.loan.ui.common.authentication;

import com.aspire.loan.helpers.service.OtpService;
import com.aspire.loan.ui.BasePage;
import com.aspire.loan.ui.pages.authentication.LoginPage;
import org.openqa.selenium.WebDriver;

public class LoginWF extends BasePage {

    private LoginPage loginPage;
    private OtpService otpService;

    public LoginWF(WebDriver driver) {
        super(driver);
        this.loginPage = new LoginPage(driver);
        this.otpService = new OtpService();

    }

    public void loginWith(String email){
        this.loginPage.goTo().isAt();
        this.loginPage.loginByEmail(email).waitForOtpSectionLoaded().inputOtp(this.otpService.getOtp());
    };
}

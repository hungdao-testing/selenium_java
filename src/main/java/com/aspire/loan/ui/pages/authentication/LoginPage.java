package com.aspire.loan.ui.pages.authentication;

import com.aspire.loan.ui.components.OtpHandle;
import com.aspire.loan.ui.components.SideBar;
import com.aspire.loan.config.AppConfig;
import com.aspire.loan.elementhelper.IDropdown;
import com.aspire.loan.ui.BasePage;
import com.aspire.loan.ui.utils.WaitHelper;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class LoginPage extends BasePage implements IDropdown {

    @FindBy(css = "input[placeholder='Enter your email or phone']")
    private WebElement emailOrPhoneInput;

    @FindBy(css = "button[type='submit']")
    private WebElement nextButton;

    private SideBar sideBar;


    public LoginPage(WebDriver driver) {
        super(driver);
        PageFactory.initElements(driver, this);
        this.sideBar = PageFactory.initElements(driver, SideBar.class);
    }


    public void isAt() {
        super.isAt();
        LOGGER.info("Wait for Login Page is loaded");
        this.wait.until(ExpectedConditions.visibilityOfAllElements(emailOrPhoneInput));
        this.wait.until(ExpectedConditions.textToBePresentInElement(this.sideBar.getTitleComp(), "Login to Aspire"));
        WaitHelper.waitUntilNoInnerLoadingInFields(driver, wait);
    }

    public LoginPage goTo() {
        LOGGER.info("Open Login Page");
        this.driver.get(AppConfig.getBaseUrl() + "/login");
        return this;
    }

    public OtpHandle loginByEmail(String email){
        LOGGER.info("Input email address: '{}'", email);
        this.wait.until(d -> emailOrPhoneInput.isDisplayed());
        emailOrPhoneInput.sendKeys(email);

        LOGGER.info("Click On Next button");
        this.wait.until(d -> nextButton.isDisplayed());
        nextButton.click();
        return new OtpHandle(driver, email);
    };

}

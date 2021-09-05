package com.aspire.loan.ui.pages.authentication;

import com.aspire.loan.ui.components.OtpHandle;
import com.aspire.loan.ui.components.SideBar;
import com.aspire.loan.config.AppConfig;
import com.aspire.loan.elementhelper.IDropdown;
import com.aspire.loan.ui.AbstractBasePage;
import com.aspire.loan.ui.utils.WaitHelper;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class LoginPage extends AbstractBasePage implements IDropdown {


    @FindBy(css = ".auth-form__card > div:nth-child(3) div")
    private WebElement loginEmailSelection;

    @FindBy(css = "input[name='email']")
    private WebElement emailField;

    @FindBy(css = "div[data-cy='register-person-phone'] input")
    private WebElement phoneInput;

    @FindBy(css = "button[type='submit']")
    private WebElement loginButton;

    private SideBar sideBar;


    public LoginPage(WebDriver driver) {
        super(driver);
        PageFactory.initElements(driver, this);
        this.sideBar = PageFactory.initElements(driver, SideBar.class);
    }


    public void isAt() {
        super.isAt();
        LOGGER.info("Wait for Login Page is loaded");
        this.wait.until(ExpectedConditions.visibilityOfAllElements(phoneInput, loginEmailSelection));
        this.wait.until(ExpectedConditions.textToBePresentInElement(this.sideBar.getTitleComp(), "Login to Aspire"));
        WaitHelper.waitUntilNoInnerLoadingInFields(driver, wait);
    }

    public LoginPage goTo() {
        LOGGER.info("Open Login Page");
        this.driver.get(AppConfig.getBaseUrl() + "/login");
        return this;
    }

    public OtpHandle loginByEmail(String email){
        LOGGER.info("Attempt to click on 'Login Email' button");
        loginEmailSelection.click();

        LOGGER.info("Input email address");
        this.wait.until(d -> emailField.isDisplayed());
        emailField.sendKeys(email);

        LOGGER.info("Click On Login button");
        this.wait.until(d -> loginButton.isDisplayed());
        loginButton.click();
        return new OtpHandle(driver, email);
    };

}

package com.aspire.loan.pages.authentication;

import com.aspire.loan.components.SideBar;
import com.aspire.loan.config.AppConfig;
import com.aspire.loan.controlhelper.IDropdown;
import com.aspire.loan.core.AbstractBasePage;
import com.aspire.loan.data.Personal;
import org.openqa.selenium.*;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;


public class RegistrationPage extends AbstractBasePage implements IDropdown {

    @FindBy(css = "input[data-cy='register-person-name']")
    private WebElement fullName;

    @FindBy(css = "input[data-cy='register-person-email']")
    private WebElement email;

    @FindBy(css = "[data-cy='register-person-phone']")
    private WebElement mobileSection;

    @FindBy(css = "[data-cy='register-person-phone'] label:nth-child(2) div[class*='q-field__prefix']")
    private WebElement dialCode;

    @FindBy(css = "[data-cy='register-person-phone'] label:nth-child(2) input")
    private WebElement phoneNumberTxt;

    @FindBy(css = "[data-cy='register-person-phone'] label:nth-child(1)")
    private WebElement countryDropdown;

    @FindBy(css = "[data-cy='register-person-heard-about'] input")
    private WebElement hearAboutUs;

    @FindBy(css = "input[data-cy='register-person-referral-code']")
    private WebElement promoCode;

    @FindBy(css = "div[data-cy='register-person-privacy']")
    private WebElement privacyCheckbox;

    @FindBy(css = "button[role='button']")
    private WebElement continueBtn;

    private SideBar sideBar;

    public RegistrationPage(WebDriver driver) {
        super(driver);
        PageFactory.initElements(driver, this);
        this.sideBar = PageFactory.initElements(driver, SideBar.class);
    }

    public RegistrationPage goTo() {
        this.driver.get(AppConfig.getBaseUrl() + "/register");
        return this;
    }

    @Override
    public void isAt() {
        super.isAt();
        this.wait.until(ExpectedConditions.visibilityOfAllElements(this.mobileSection, this.email, this.sideBar.getTitleComp()));
    }

    protected RegistrationPage inputFullName(String fullName) {
        LOGGER.info("Input full name");
        this.inputTextToVisibleField(this.fullName, fullName);
        return this;
    }

    protected RegistrationPage inputEmail(String email) {
        LOGGER.info("Input email");
        this.inputTextToVisibleField(this.email, email);
        return this;
    }

    protected RegistrationPage inputPhoneNumber(String countryPhoneCode, String phoneNumber) {
        LOGGER.info("select country {}", countryPhoneCode);
        phoneNumberTxt.sendKeys(phoneNumber);
        this.wait.until(ExpectedConditions.elementToBeClickable(countryDropdown));
        countryDropdown.click();
        scrollAndSelectOption(driver, wait, countryPhoneCode);

        String phoneNationalPattern = countryPhoneCode
                .split(" ")[1]
                .replace("(", "").replace(")", "");

        this.wait.until(d -> dialCode.getText().equalsIgnoreCase(phoneNationalPattern));
        return this;
    }

    protected RegistrationPage inputAboutUs(String input) {
        LOGGER.info("select about us options {}", input);
        this.wait.until(d -> hearAboutUs.isDisplayed());
        hearAboutUs.click();
        selectOptionFromShortList(driver, wait, input);
        return this;
    }

    protected RegistrationPage inputPromoCode(String promoCode) {
        LOGGER.info("input promo code");
        this.inputTextToVisibleField(this.promoCode, promoCode);
        return this;
    }

    protected RegistrationPage checkPrivacyBox() {
        LOGGER.info("Tick to privacy box");
        if (this.privacyCheckbox.isSelected()) {
            return this;
        } else {
            this.privacyCheckbox.click();
        }
        return this;
    }

    protected void clickSubmitBtn() {
        LOGGER.info("Wait for Submit button to be enable and click");
        this.wait.until(d -> continueBtn.isDisplayed());
        this.continueBtn.click();
    }

    public void createUserAccount(Personal personal, String aboutUs){
        LOGGER.info("Start creating a new user account with personal info {}", personal.toString());
        String phoneCode = personal.getCountry() + " " + "("+  personal.getDialCode()+ ")";
        inputFullName(personal.getFullName());
        inputEmail(personal.getEmail());
        inputAboutUs(aboutUs);
        inputPhoneNumber(phoneCode, personal.getPhone());
        checkPrivacyBox();
        clickSubmitBtn();

    }

}

package com.aspire.loan.ui.pages.authentication;

import com.aspire.loan.ui.common.authentication.RegistrationInfo;
import com.aspire.loan.ui.components.SideBar;
import com.aspire.loan.config.AppConfig;
import com.aspire.loan.helpers.web_element.IDropdown;
import com.aspire.loan.ui.BasePage;
import org.openqa.selenium.*;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;

import java.util.List;
import java.util.stream.Collectors;


public class SignUpPage extends BasePage implements IDropdown {

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

    @FindBy(css = ".aspire-label__text.text-negative")
    private List<WebElement> errors;

    @FindBy(css = ".q-notification__message.col")
    private WebElement notificationErrorMessage;

    private SideBar sideBar;

    public SignUpPage(WebDriver driver) {
        super(driver);
        PageFactory.initElements(driver, this);
        this.sideBar = PageFactory.initElements(driver, SideBar.class);
    }

    public SignUpPage goTo() {
        LOGGER.info("Go to SignUp Page");
        this.driver.get(AppConfig.getBaseUrl() + "/register");
        return this;
    }

    @Override
    public void isAt() {
        super.isAt();
        LOGGER.info("Waiting for SignUp page loaded");
        this.wait.until(ExpectedConditions.visibilityOfAllElements(this.mobileSection, this.email, this.sideBar.getTitleComp()));
    }

    protected SignUpPage inputFullName(String fullName) {
        LOGGER.info("Start inputting full_name field");
        this.inputTextToVisibleField(this.fullName, fullName);
        return this;
    }

    protected SignUpPage inputEmail(String email) {
        LOGGER.info("Start inputting email field");
        this.inputTextToVisibleField(this.email, email);
        return this;
    }

    protected SignUpPage inputPhoneNumber(String countryPhoneCode, String phoneNumber) {
        LOGGER.info("select country: '{}'", countryPhoneCode);
        phoneNumberTxt.sendKeys(phoneNumber);
        this.wait.until(ExpectedConditions.elementToBeClickable(countryDropdown));
        countryDropdown.click();
        scrollDropdownAndSelectValue(driver, wait, countryPhoneCode);

        String phoneNationalPattern = countryPhoneCode
                .split(" ")[1]
                .replace("(", "").replace(")", "");

        this.wait.until(d -> dialCode.getText().equalsIgnoreCase(phoneNationalPattern));
        return this;
    }

    protected SignUpPage inputAboutUs(String input) {
        LOGGER.info("select about_us option: '{}'", input);
        this.wait.until(d -> hearAboutUs.isDisplayed());
        hearAboutUs.click();
        selectOptionFromShortList(driver, wait, input);
        return this;
    }

    protected SignUpPage inputPromoCode(String promoCode) {
        LOGGER.info("input promo code");
        this.inputTextToVisibleField(this.promoCode, promoCode);
        return this;
    }

    public SignUpPage checkPrivacyBox() {
        LOGGER.info("Tick to privacy box");
        if (this.privacyCheckbox.isSelected()) {
            return this;
        } else {
            this.privacyCheckbox.click();
        }
        return this;
    }

    public void clickSubmitBtn() {
        LOGGER.info("Wait for Submit button to be enable and click");
        this.wait.until(d -> continueBtn.isDisplayed());
        this.continueBtn.click();
    }

    public SignUpPage fillForm(RegistrationInfo data){
        LOGGER.info("Start creating a new user account with personal info {}", data.getPersonalInfo().toString());
        String phoneCode = data.getPersonalInfo().getCountry() + " " + "("+  data.getPersonalInfo().getDialCode()+ ")";
        inputFullName(data.getPersonalInfo().getFullName());
        inputEmail(data.getPersonalInfo().getEmail());
        inputPromoCode(data.getPromoCode());
        inputAboutUs(data.getHearAboutUs());
        inputPhoneNumber(phoneCode, data.getPersonalInfo().getPhone());
        return this;
    }

    public List<String> getErrorMessage(){
        LOGGER.info("Get Inline Error Messages");
        return errors.stream()
                .map(WebElement::getText)
                .map(String::trim)
                .collect(Collectors.toList());
    }

    public String getNotificationErrorMessage(){
        this.isAt();
        LOGGER.info("Wait for notification error message loaded and get text");
        this.wait.until(d -> notificationErrorMessage.isDisplayed());
        return notificationErrorMessage.getText();
    }

    public List<WebElement> getErrorEls() {
        return errors;
    }

    public WebElement getNotificationErrorEl(){
        return notificationErrorMessage;
    }
}

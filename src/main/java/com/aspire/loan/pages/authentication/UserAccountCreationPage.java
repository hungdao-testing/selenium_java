package com.aspire.loan.pages.authentication;

import com.aspire.loan.components.SideBar;
import com.aspire.loan.controlhelper.IDropdown;
import com.aspire.loan.core.AbstractBasePage;
import org.openqa.selenium.*;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;


public class UserAccountCreationPage extends AbstractBasePage implements IDropdown {

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

    public UserAccountCreationPage(WebDriver driver) {
        super(driver);
        PageFactory.initElements(driver, this);
        this.sideBar = PageFactory.initElements(driver, SideBar.class);
    }

    public UserAccountCreationPage goTo() {
        this.driver.get(getBaseUrl() + "/register");
        return this;
    }

    @Override
    public void isAt() {
        super.isAt();
        this.wait.until(ExpectedConditions.visibilityOfAllElements(this.mobileSection, this.email, this.sideBar.getTitleComp()));
    }

    public UserAccountCreationPage inputFullName(String fullName) {
        this.inputTextToVisibleField(this.fullName, fullName);
        return this;
    }

    public UserAccountCreationPage inputEmail(String email) {
        this.inputTextToVisibleField(this.email, email);
        return this;
    }

    public UserAccountCreationPage inputPhoneNumber(String countryPhoneCode, String phoneNumber) {
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

    public UserAccountCreationPage inputAboutUs(String input) {
        this.wait.until(d -> hearAboutUs.isDisplayed());
        hearAboutUs.click();
        selectOptionFromShortList(driver, wait, input);
        return this;
    }

    public UserAccountCreationPage inputPromoCode(String promoCode) {
        this.inputTextToVisibleField(this.promoCode, promoCode);
        return this;
    }

    public UserAccountCreationPage checkPrivacyBox() {
        if (this.privacyCheckbox.isSelected()) {
            return this;
        } else {
            this.privacyCheckbox.click();
        }
        return this;
    }

    public void clickSubmitBtn() {
        this.wait.until(d -> continueBtn.isDisplayed());
        this.continueBtn.click();
    }

}

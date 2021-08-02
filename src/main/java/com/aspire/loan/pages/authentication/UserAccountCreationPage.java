package com.aspire.loan.pages.authentication;

import com.aspire.loan.components.SideBar;
import com.aspire.loan.core.AbstractBasePage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import java.util.List;


public class UserAccountCreationPage extends AbstractBasePage {

    @FindBy(css = "input[data-cy='register-person-name']")
    private WebElement fullName;

    @FindBy(css = "input[data-cy='register-person-email']")
    private WebElement email;

    @FindBy(css = "input[data-cy='register-person-phone']")
    private WebElement mobile;

    @FindBy(xpath = "//input[@data-cy='register-person-phone']/preceding-sibling::div")
    private WebElement countryPhoneCodeEl;

    @FindBy(css = "i[role='presentation']")
    private WebElement countryDropdownIcon;

    @FindBy(css = "[data-cy='register-person-heard-about']")
    private WebElement hearAboutUs;

    @FindBy(css = "div[tabindex='-1'].q-menu")
    private WebElement dropDownMenu;

    @FindBy(css = ".q-item__label")
    private List<WebElement> dropDownOption;

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

    public UserAccountCreationPage isAt() {
        this.wait.until(ExpectedConditions.visibilityOfAllElements(this.mobile, this.email, this.sideBar.getTitleComp()));
        this.wait.until(d -> noActiveAjaxRequest());
        return this;
    }

    public UserAccountCreationPage inputFullName(String fullName){
        this.fullName.clear();
        this.fullName.sendKeys(fullName);
        return this;
    }

    public UserAccountCreationPage inputEmail(String email){
        this.email.clear();
        this.email.sendKeys(email);
        return this;
    }

    public UserAccountCreationPage inputPhoneNumber(String countryPhoneCode, String phoneNumber){
        selectValueFromDropDown(countryDropdownIcon, countryPhoneCode);
        String phoneNationalPattern = countryPhoneCode
                                            .split(" ")[1]
                                            .replace("(", "").replace(")", "");
        ExpectedConditions.textToBePresentInElementValue(countryPhoneCodeEl, phoneNationalPattern);
        this.wait.until(ExpectedConditions.elementToBeClickable(mobile));
        this.mobile.clear();
        this.mobile.sendKeys(phoneNumber);
        return this;
    }

    public UserAccountCreationPage inputAboutUs(String input){
        selectValueFromDropDown(hearAboutUs, input);
        return this;
    }

    public UserAccountCreationPage inputPromoCode(String promoCode){
        this.promoCode.clear();
        this.promoCode.sendKeys(promoCode);
        return this;
    }

    public UserAccountCreationPage checkPrivacyBox(){
        if(this.privacyCheckbox.isSelected()){
            return this;
        }else{
            this.privacyCheckbox.click();
        }
        return this;
    }

    public void clickSubmitBtn(){
        this.wait.until(d -> continueBtn.isDisplayed());
        this.continueBtn.click();
    }

    private void selectValueFromDropDown(WebElement dropdownField, String value){
        new Actions(driver).moveToElement(dropdownField).click().build().perform();
        this.wait.until(ExpectedConditions.visibilityOf(dropDownMenu));
        dropDownOption.stream()
                .filter(e -> e.getText().equalsIgnoreCase(value))
                .findFirst()
                .get()
                .click();
    }


}

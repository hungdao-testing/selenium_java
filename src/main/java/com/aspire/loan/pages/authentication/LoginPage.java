package com.aspire.loan.pages.authentication;

import com.aspire.loan.components.SideBar;
import com.aspire.loan.controlhelper.IDropdown;
import com.aspire.loan.core.AbstractBasePage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class LoginPage extends AbstractBasePage implements IDropdown {


    @FindBy(css = "input[data-cy='register-person-phone']")
    private WebElement mobile;

    @FindBy(xpath = "//input[@data-cy='register-person-phone']/preceding-sibling::div")
    private WebElement countryPhoneCodeEl;

    @FindBy(css = "i[role='presentation']")
    private WebElement countryDropdownIcon;

    @FindBy(css = "button[type='submit']")
    private WebElement loginButton;

    private SideBar sideBar;


    public LoginPage(WebDriver driver) {
        super(driver);
        PageFactory.initElements(driver, this);
        this.sideBar = PageFactory.initElements(driver, SideBar.class);
    }


    public void isAt() {
        //this.wait.until(noActiveAjaxRequest());
        this.wait.until(d -> this.mobile.isDisplayed());
        this.wait.until(ExpectedConditions.textToBePresentInElement(this.sideBar.getTitleComp(), "Login to Aspire"));
    }

    public LoginPage goTo() {
        this.driver.get(getBaseUrl() + "/login");
        return this;
    }

    public void loginByMobilePhone(String country, String nationPhoneCodeWithPlusSymbol, String phoneNumber){
        this.wait.until(ExpectedConditions.elementToBeClickable(this.countryDropdownIcon));
        scrollAndSelectOption(driver, wait, country);
        this.wait.until(ExpectedConditions.textToBePresentInElement(countryPhoneCodeEl, nationPhoneCodeWithPlusSymbol));
        this.wait.until(ExpectedConditions.elementToBeClickable(mobile));
        mobile.sendKeys(phoneNumber);
        this.wait.until(d -> loginButton.isDisplayed());
        loginButton.click();
    }
}

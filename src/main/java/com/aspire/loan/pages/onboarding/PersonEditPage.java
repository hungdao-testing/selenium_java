package com.aspire.loan.pages.onboarding;

import com.aspire.loan.components.SideBar;
import com.aspire.loan.controlhelper.ICalendar;
import com.aspire.loan.controlhelper.IDropdown;
import com.aspire.loan.core.AbstractBasePage;
import com.google.common.util.concurrent.Uninterruptibles;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;

import java.time.Month;
import java.time.format.TextStyle;
import java.util.List;
import java.util.Locale;
import java.util.concurrent.TimeUnit;

public class PersonEditPage extends AbstractBasePage implements IDropdown, ICalendar {

    @FindBy(css = "div[data-cy='person-edit-email']")
    private WebElement emailField;

    @FindBy(css = "[label='Date of birth'] label")
    private WebElement dateOfBirthField;

    @FindBy(css = "div[placeholder-field='Nationality'] label")
    private WebElement nationalityField;

    @FindBy(css = "input[data-cy='kyc-gender']")
    private WebElement genderField;

    @FindBy(css = "div[class='auth-form__buttons'] button")
    private WebElement submitButton;


    private SideBar sideBar;

    public PersonEditPage(WebDriver driver) {
        super(driver);
        PageFactory.initElements(driver, this);
        this.sideBar = PageFactory.initElements(driver, SideBar.class);
    }

    public PersonEditPage isAt(){
        this.wait.until(ExpectedConditions.textToBePresentInElement(this.sideBar.getTitleComp(), "Personal Details"));
        this.wait.until(noActiveAjaxRequest());
        return this;
    }

    public void goTo(){
        this.driver.get(getBaseUrl() + "/onboarding/person-edit");
    }

    public PersonEditPage setEmail(String inputEmailField){
        this.wait.until(d -> emailField.isDisplayed());
        if(!emailField.getAttribute("value").equalsIgnoreCase(inputEmailField)){
            while(emailField.getAttribute("value").length() > 0){
                this.emailField.findElement(By.tagName("input")).sendKeys(Keys.BACK_SPACE);
            }
            this.emailField.findElement(By.tagName("input")).sendKeys(inputEmailField);
        }
        return this;
    }

    public PersonEditPage setDateOfBirth(int day, int month, int year ){

        this.wait.until(ExpectedConditions.elementToBeClickable(dateOfBirthField));
        dateOfBirthField.click();
        setYear(driver, year);
        setMonth(driver, month);
        setDate(driver, day);
        return this;
    }

    public PersonEditPage setNationality(String nationality){
        this.wait.until(noActiveAjaxRequest());
        this.wait.until(ExpectedConditions.elementToBeClickable(nationalityField));
        nationalityField.click();
        nationalityField.sendKeys(nationality);
        List<WebElement> items = driver.findElements(By.cssSelector(".q-item__label"));
        for(WebElement item : items){
            if(item.getText().equalsIgnoreCase(nationality)){
                item.click();
                break;
            }
        }
        return this;
    }

    public PersonEditPage setGender(String gender){
        this.wait.until(ExpectedConditions.elementToBeClickable(genderField));
        selectOptionFromDropdown(driver, genderField, gender);
        return this;
    }

    public void clickSubmit(){
        this.wait.until(d -> submitButton.isDisplayed());
        submitButton.click();
        this.wait.until(noActiveAjaxRequest());
    }


}

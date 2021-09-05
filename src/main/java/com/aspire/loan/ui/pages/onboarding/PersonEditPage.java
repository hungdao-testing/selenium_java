package com.aspire.loan.ui.pages.onboarding;

import com.aspire.loan.ui.components.SideBar;
import com.aspire.loan.config.AppConfig;
import com.aspire.loan.elementhelper.ICalendar;
import com.aspire.loan.elementhelper.IDropdown;
import com.aspire.loan.ui.AbstractBasePage;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;

import java.util.List;

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

    @Override
    public void isAt() {
        super.isAt();
    }

    public void goTo(){
        this.driver.get(AppConfig.getBaseUrl() + "/onboarding/person-edit");
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
        scrollAndSelectOption(driver,wait, gender);
        return this;
    }

    public void clickSubmit(){
        this.wait.until(d -> submitButton.isDisplayed());
        submitButton.click();
    }


}

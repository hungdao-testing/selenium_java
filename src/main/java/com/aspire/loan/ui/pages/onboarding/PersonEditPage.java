package com.aspire.loan.ui.pages.onboarding;

import com.aspire.loan.elementhelper.Calendar;
import com.aspire.loan.ui.components.SideBar;
import com.aspire.loan.config.AppConfig;
import com.aspire.loan.elementhelper.IDropdown;
import com.aspire.loan.ui.BasePage;
import com.aspire.loan.ui.utils.DateHelper;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;

import java.util.Locale;

public class PersonEditPage extends BasePage implements IDropdown {

    @FindBy(css = "div[data-cy='person-edit-phone']")
    private WebElement phoneField;

    @FindBy(css = "[label='Date of birth'] input")
    private WebElement dateOfBirthField;

    @FindBy(css = "div[placeholder-field='Nationality'] input")
    private WebElement nationalityField;

    @FindBy(css = "input[data-cy='kyc-gender']")
    private WebElement genderField;

    @FindBy(css = "div[class='auth-form__buttons'] button")
    private WebElement submitButton;

    private Calendar calendar;
    private SideBar sideBar;

    public PersonEditPage(WebDriver driver) {
        super(driver);
        PageFactory.initElements(driver, this);
        this.sideBar = PageFactory.initElements(driver, SideBar.class);
        this.calendar = PageFactory.initElements(driver, Calendar.class);
    }

    @Override
    public void isAt() {
        super.isAt();
        this.wait.until(d -> phoneField.isDisplayed());
        this.wait.until(d -> ExpectedConditions.textToBePresentInElement(this.sideBar.getTitleComp(), "Personal Details"));
    }

    public void goTo(){
        this.driver.get(AppConfig.getBaseUrl() + "/onboarding/person-edit");
    }

    public PersonEditPage setEmail(String inputEmailField){
        LOGGER.info("Attempt to input email '{}'", inputEmailField);
        this.wait.until(d -> phoneField.isDisplayed());
        if(!phoneField.getAttribute("value").equalsIgnoreCase(inputEmailField)){
            while(phoneField.getAttribute("value").length() > 0){
                this.phoneField.findElement(By.tagName("input")).sendKeys(Keys.BACK_SPACE);
            }
            this.phoneField.findElement(By.tagName("input")).sendKeys(inputEmailField);
        }
        return this;
    }

    public PersonEditPage setDateOfBirth(String day, String month, String year ){
        LOGGER.info("Attempt to set DOB - day: '{}', month: '{}', year: '{}'", day, month, year);
        String formatDate = String
                .format("%s %s, %s", DateHelper.convertToShortMonthFormat(month, Locale.UK), day, year);
        clickOnVisibleElement(dateOfBirthField);
        calendar.setDateOfBirth(year, month, day);
        this.wait.until(d -> dateOfBirthField.getAttribute("value").equalsIgnoreCase(formatDate));
        return this;
    }

    public PersonEditPage setNationality(String nationality){
        LOGGER.info("Attempt to set nationality: '{}'", nationality);
        searchAndSelectTextInDropdownField(nationalityField, nationality);
        return this;
    }

    public PersonEditPage setGender(String gender){
        LOGGER.info("Attempt to set gender: '{}'", gender);
        this.wait.until(ExpectedConditions.elementToBeClickable(genderField));
        clickOnVisibleElement(genderField);
        scrollAndSelectOption(driver,wait, gender);
        return this;
    }

    public void clickSubmit(){
        LOGGER.info("Click Submit button");
        this.wait.until(d -> submitButton.isDisplayed());
        submitButton.click();
    }


}

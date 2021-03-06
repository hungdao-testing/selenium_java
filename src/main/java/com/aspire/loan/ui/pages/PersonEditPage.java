package com.aspire.loan.ui.pages;

import com.aspire.loan.helpers.web_element.Calendar;
import com.aspire.loan.models.uidata.PersonalModel;
import com.aspire.loan.helpers.service.OtpService;
import com.aspire.loan.ui.components.OtpHandle;
import com.aspire.loan.ui.components.SideBar;
import com.aspire.loan.config.AppConfig;
import com.aspire.loan.helpers.web_element.IDropdown;
import com.aspire.loan.ui.BasePage;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;


public class PersonEditPage extends BasePage implements IDropdown {

    @FindBy(css = "div[data-cy='person-edit-phone'] input")
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

    private void isPhoneLoadedAsSetup(String phone) {
        if(!phoneField.getAttribute("value").equalsIgnoreCase(phone)){
            while(phoneField.getAttribute("value").length() > 0){
                this.phoneField.findElement(By.tagName("input")).sendKeys(Keys.BACK_SPACE);
            }
            inputTextToVisibleField(this.phoneField.findElement(By.tagName("input")), phone);
        }
    }

    private void setPhone(String phone){
        LOGGER.info("Attempt to input phone '{}'", phone);
        this.wait.until(d -> phoneField.isDisplayed());
        isPhoneLoadedAsSetup(phone);
    }

    private void setDateOfBirth(String day, String month, String year ){
        this.wait.until(d -> dateOfBirthField.isDisplayed());
        LOGGER.info("Attempt to set DOB - day: '{}', month: '{}', year: '{}'", day, month, year);
        calendar.setDateForField(dateOfBirthField, year, month, day);
    }

    private void setNationality(String nationality){
        LOGGER.info("Attempt to set nationality: '{}'", nationality);
        searchAndSelectTextInDropdownField(nationalityField, nationality);
    }

    private void setGender(String gender){
        LOGGER.info("Attempt to set gender: '{}'", gender);
        clickOnVisibleElement(genderField);
        scrollDropdownAndSelectValue(driver,wait, gender);
    }

    private void clickSubmit(){
        LOGGER.info("Click Submit button");
        clickOnVisibleElement(submitButton);
    }

    public void submitPersonalAndVerifyOtp(PersonalModel data){
        setPhone(data.getPhone());
        setDateOfBirth(data.getDob().get("day"), data.getDob().get("month"), data.getDob().get("year"));
        setNationality(data.getCountry());
        setGender(data.getGender());
        clickSubmit();
        new OtpHandle(driver, data.getPhone()).waitForOtpSectionLoaded().inputOtp(new OtpService().getOtp());
    }


}

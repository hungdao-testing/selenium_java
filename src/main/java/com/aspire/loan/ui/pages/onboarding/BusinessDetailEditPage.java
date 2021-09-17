package com.aspire.loan.ui.pages.onboarding;

import com.aspire.loan.model.uidata.BusinessInfo;
import com.aspire.loan.ui.components.SideBar;
import com.aspire.loan.config.AppConfig;
import com.aspire.loan.elementhelper.IDropdown;
import com.aspire.loan.ui.BasePage;
import org.junit.Assert;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class BusinessDetailEditPage extends BasePage implements IDropdown {

    @FindBy(css = "[data-cy='register-business-registration-business-model']:nth-child(1) input")
    private WebElement businessActivityField;

    @FindBy(css = "[data-cy='register-business-registration-business-model']:nth-child(2) input")
    private WebElement businessDetailActivityField;

    @FindBy(css = "input[data-cy='register-business-registration-website-url']")
    private WebElement liveWebsiteField;

    @FindBy(css = "div[label='Number of employees'] label")
    private WebElement numberOfEmployeeField;

    @FindBy(css = "div[label='Annual turnover'] label")
    private WebElement annualTurnOverField;

    @FindBy(css = "div[class='auth-form__buttons'] button")
    private WebElement continueButton;

    private SideBar sideBar;

    public BusinessDetailEditPage(WebDriver driver) {
        super(driver);
        PageFactory.initElements(driver, this);
        this.sideBar = PageFactory.initElements(driver, SideBar.class);
    }

    public BusinessDetailEditPage goTo(){
        this.driver.get(AppConfig.getBaseUrl() + "/onboarding/business-details-edit");
        return this;
    }

    @Override
    public void isAt() {
        super.isAt();
    }

    public BusinessDetailEditPage setBusinessActivity(String activity){
        Assert.assertTrue("Activity must be at lease 40 chars, please check again!!",
                activity.length() >= 40);
        this.wait.until(ExpectedConditions.elementToBeClickable(businessActivityField));
        this.businessActivityField.sendKeys(activity);
        return this;
    }

    public BusinessDetailEditPage setBusinessDetailActivity(String detailActivity){
        Assert.assertTrue("Detail Activity must be at lease 40 chars, please check again!!",
                detailActivity.length() >= 40);
        this.wait.until(ExpectedConditions.elementToBeClickable(businessDetailActivityField));
        this.businessDetailActivityField.sendKeys(detailActivity);
        return this;
    }

    public BusinessDetailEditPage setLiveWebSite(String websiteUrl){
        this.wait.until(ExpectedConditions.elementToBeClickable(liveWebsiteField));
        this.liveWebsiteField.sendKeys(websiteUrl);
        return this;
    }

    public BusinessDetailEditPage setNumberOfEmployees(String numberOfEmployees){
        this.wait.until(ExpectedConditions.elementToBeClickable(numberOfEmployeeField));
        scrollDropdownAndSelectValue(driver, wait, numberOfEmployees);
        return this;
    }

    public BusinessDetailEditPage setAnnualTurnOverField(String annualTurnOverRange){
        this.wait.until(ExpectedConditions.elementToBeClickable(annualTurnOverField));
        scrollDropdownAndSelectValue(driver, wait, annualTurnOverRange);
        return this;
    }

    public void clickSubmit(){
        this.wait.until(d -> this.continueButton.isDisplayed());
        this.continueButton.click();
    }

//    public void submitEditBusinessDetail(BusinessInfo businessInfo){
//        setBusinessActivity(businessInfo)
//    }
    
}

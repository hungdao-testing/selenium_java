package com.aspire.loan.ui.pages.onboarding;

import com.aspire.loan.ui.components.SideBar;
import com.aspire.loan.config.AppConfig;
import com.aspire.loan.elementhelper.IDropdown;
import com.aspire.loan.ui.BasePage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;

import java.util.List;

public class BusinessEditPage extends BasePage implements IDropdown {

    @FindBy(css = "[data-cy='register-business-name'] input")
    private WebElement businessLegalNameField;

    @FindBy(css = "[data-cy='register-business-registration-type'] label input")
    private WebElement entityCategoryField;

    @FindBy(css = "[data-cy='register-business-registration-number'] label input")
    private WebElement businessRegistrationNumber;

    @FindBy(css = "[data-cy='register-business-sub-registration-type'] label")
    private WebElement businessEntityTypeField;

    @FindBy(css = "label [data-cy='register-business-industry']")
    private WebElement industryField;

    @FindBy(css = "[data-cy='register-business-sub-industry'] input")
    private WebElement subIndustryField;

    @FindBy(css = "div[class='auth-form__buttons'] button")
    private WebElement continueButton;

    private SideBar sideBar;

    public BusinessEditPage(WebDriver driver) {
        super(driver);
        PageFactory.initElements(driver, this);
        this.sideBar = PageFactory.initElements(driver, SideBar.class);
    }

    public BusinessEditPage goTo(){
        this.driver.get(AppConfig.getBaseUrl() + "/onboarding/business-edit");
        return this;
    }

//    public BusinessEditPage isAt(){
//        this.wait.until(ExpectedConditions.textToBePresentInElement(this.sideBar.getTitleComp(), "Business Details"));
//        this.wait.until(d -> businessLegalNameField.isDisplayed() && entityCategoryField.isDisplayed());
//        this.wait.until(noActiveAjaxRequest());
//        return this;
//    }

    public BusinessEditPage setBusinessName(String name){
        this.wait.until(ExpectedConditions.elementToBeClickable(businessLegalNameField));
        this.businessLegalNameField.sendKeys(name);
        return this;
    }

    public BusinessEditPage setEntityCategory(String entityCategory){
        this.wait.until(d -> entityCategoryField.isDisplayed());
        scrollAndSelectOption(driver, wait, entityCategory);
        return this;
    }

    public BusinessEditPage setBusinessRegistrationNumber(String businessNumber){
        this.wait.until(ExpectedConditions.elementToBeClickable(businessRegistrationNumber));
        businessRegistrationNumber.click();
        businessRegistrationNumber.clear();
        businessRegistrationNumber.sendKeys(businessNumber);
        return this;
    }

    public BusinessEditPage setBusinessEntityType(String businessEntityType){
      //  this.wait.until(noActiveAjaxRequest());
        this.wait.until(ExpectedConditions.elementToBeClickable(businessEntityTypeField));
        scrollAndSelectOption(driver, wait, businessEntityType);
        return this;
    }

    public BusinessEditPage setIndustry(String industry){
       // this.wait.until(noActiveAjaxRequest());
        this.wait.until(ExpectedConditions.elementToBeClickable(industryField));
        scrollAndSelectOption(driver, wait, industry);
        return this;
    }

    public BusinessEditPage setSubIndustry(String subIndustry){
        this.wait.until(ExpectedConditions.elementToBeClickable(subIndustryField));
        subIndustryField.clear();
        subIndustryField.sendKeys(subIndustry);
        List<WebElement> items = driver.findElements(By.cssSelector(".q-item__label"));
        for(WebElement item : items){
            if(item.getText().equalsIgnoreCase(subIndustry)){
                item.click();
                break;
            }
        }
        return this;
    }

    public void clickContinueButton(){
        this.wait.until(d -> this.continueButton.isDisplayed());
        this.continueButton.click();
    }

}

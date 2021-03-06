package com.aspire.loan.ui.pages;

import com.aspire.loan.models.uidata.BusinessModel;
import com.aspire.loan.ui.components.SideBar;
import com.aspire.loan.config.AppConfig;
import com.aspire.loan.helpers.web_element.IDropdown;
import com.aspire.loan.ui.BasePage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;

import java.util.regex.Pattern;

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

    private static final String businessRegistrationNumberPattern = "^([0-9]{8,9}[a-zA-Z]{1})$";

    public BusinessEditPage(WebDriver driver) {
        super(driver);
        PageFactory.initElements(driver, this);
        this.sideBar = PageFactory.initElements(driver, SideBar.class);
    }

    @Override
    public void isAt() {
        super.isAt();
        this.wait.until(ExpectedConditions.textToBePresentInElement(this.sideBar.getTitleComp(), "Business Details"));
    }

    public BusinessEditPage goTo(){
        this.driver.get(AppConfig.getBaseUrl() + "/onboarding/business-edit");
        return this;
    }

    public BusinessEditPage setBusinessName(String name){
        this.wait.until(ExpectedConditions.elementToBeClickable(businessLegalNameField));
        this.businessLegalNameField.sendKeys(name);
        return this;
    }

    public BusinessEditPage setEntityCategory(String entityCategory){
        clickOnVisibleElement(entityCategoryField);
        scrollDropdownAndSelectValue(driver, wait, entityCategory);
        return this;
    }

    public BusinessEditPage setBusinessRegistrationNumber(String businessNumber){
        if(!Pattern.matches(businessRegistrationNumberPattern, businessNumber)) return null;
        inputTextToVisibleField(businessRegistrationNumber, businessNumber);
        return this;
    }

    public BusinessEditPage setEntityType(String entityType){
        clickOnVisibleElement(businessEntityTypeField);
        scrollDropdownAndSelectValue(driver, wait, entityType);
        return this;
    }

    public BusinessEditPage setIndustry(String industry){
        clickOnVisibleElement(industryField);
        scrollDropdownAndSelectValue(driver, wait, industry);
        return this;
    }

    public BusinessEditPage setSubIndustry(String subIndustry){
        searchAndSelectTextInDropdownField(subIndustryField, subIndustry);
        return this;
    }

    public void clickContinueButton(){
        clickOnVisibleElement(continueButton);
    }

    public void submitEditBusinessInfo(BusinessModel data){
        setBusinessName(data.getBusinessLegalName());
        setEntityCategory(data.getEntityCategory());
        setEntityType(data.getEntityType());
        setBusinessRegistrationNumber(data.getBusinessRegistrationNumber());
        setIndustry(data.getIndustry());
        setSubIndustry(data.getSubIndustry());
        clickContinueButton();
    }
}

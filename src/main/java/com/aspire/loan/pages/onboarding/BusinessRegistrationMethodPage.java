package com.aspire.loan.pages.onboarding;

import com.aspire.loan.components.SideBar;
import com.aspire.loan.core.AbstractBasePage;
import com.aspire.loan.core.GlobalConstants;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;


public class BusinessRegistrationMethodPage extends AbstractBasePage {


    @FindBy(xpath = "//div[contains(text(),'Myinfo')]/parent::div/following-sibling::button")
    private WebElement getGetStartedForMyInfoMethod;

    @FindBy(xpath = "//div[contains(text(),'Standard Registration')]/parent::div/following-sibling::button")
    private WebElement getStartedForStandardMethod;

    private SideBar sideBar;

    public BusinessRegistrationMethodPage(WebDriver driver) {
        super(driver);
        PageFactory.initElements(driver, this);
        this.sideBar = PageFactory.initElements(driver, SideBar.class);
    }

    public BusinessRegistrationMethodPage isAt(){
        this.wait.until(d -> noActiveAjaxRequest());
        this.wait.until(ExpectedConditions.textToBePresentInElement(this.sideBar.getTitleComp(), "Choose a registration method"));
        this.wait.until(ExpectedConditions.urlContains("/onboarding/register-select-method"));
        return this;
    }

    public void selectBusinessRegistrationMethod(String method){
        if(method.toLowerCase().contains("standard")){
            this.wait.until(ExpectedConditions.elementToBeClickable(getStartedForStandardMethod));
            getStartedForStandardMethod.click();
        }else if(method.toLowerCase().contains("myinfo")){
            this.wait.until(ExpectedConditions.elementToBeClickable(getGetStartedForMyInfoMethod));
            getGetStartedForMyInfoMethod.click();
        }
    }


}



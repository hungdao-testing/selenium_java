package com.aspire.loan.ui.pages.businessregistration;

import com.aspire.loan.config.AppConfig;
import com.aspire.loan.model.uidata.configtype.BusinessRegistrationMethodType;
import com.aspire.loan.ui.components.SideBar;
import com.aspire.loan.ui.BasePage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;

import java.util.List;


public class BusinessRegistrationMethodPage extends BasePage {


    @FindBy(xpath = "//div[contains(text(),'Myinfo')]/parent::div/following-sibling::button")
    private WebElement getGetStartedForMyInfoMethod;

    @FindBy(xpath = "//div[contains(text(),'Standard Registration')]/parent::div/following-sibling::button")
    private WebElement getStartedForStandardMethod;

    @FindBy(css = ".auth-form__card > div .onboarding-step-register-select-method__column-content")
    private List<WebElement> businessMethods;

    private SideBar sideBar;

    public BusinessRegistrationMethodPage(WebDriver driver) {
        super(driver);
        PageFactory.initElements(driver, this);
        this.sideBar = PageFactory.initElements(driver, SideBar.class);
    }

    @Override
    public void isAt() {
        super.isAt();
        this.wait.until(ExpectedConditions.textToBePresentInElement(this.sideBar.getTitleComp(), "Choose a registration method"));
        this.wait.until(d -> businessMethods.size() > 0);
        this.wait.until(d -> ExpectedConditions.urlContains("/onboarding/register-select-method"));
    }

    public void goTo(){
        this.driver.get(AppConfig.getBaseUrl() + "/onboarding/register-select-method");
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

    public void selectMethod(BusinessRegistrationMethodType type){
        LOGGER.info("Attempt to click on business_card: '{}'", type.getBusinessMethodDesc());
        businessMethods
                .stream()
                .filter(e -> e.getText().contains(type.getBusinessMethodDesc()))
                .map(e -> e.findElement(By.tagName("button")))
                .findFirst()
                .get()
                .click();
    }


}



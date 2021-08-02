package com.aspire.loan.pages.onboarding;

import com.aspire.loan.components.SideBar;
import com.aspire.loan.core.AbstractBasePage;
import com.aspire.loan.pages.onboarding.kyc.IdentityFidoStrategy;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;

import java.util.Map;

public class IdentityOnFidoPage extends AbstractBasePage {

    @FindBy(css = ".auth-form__card")
    private WebElement documentCardOptions;

    @FindBy(css = ".auth-form button")
    private WebElement beginVerificationButton;


    private SideBar sideBar;
    private IdentityFidoStrategy identityFidoStrategy;


    public IdentityOnFidoPage(WebDriver driver) {
        super(driver);
        PageFactory.initElements(driver, this);
        this.sideBar = PageFactory.initElements(driver, SideBar.class);
    }

    public IdentityOnFidoPage goTo() {
        driver.get(getBaseUrl() + "/onboarding/identify-onfido");
        return this;
    }

    public IdentityOnFidoPage isAt() {
        this.wait.until(noActiveAjaxRequest());
        this.wait.until(ExpectedConditions.textToBePresentInElement(sideBar.getTitleComp(), "Account Verification"));
        this.wait.until(d -> documentCardOptions.isDisplayed());
        return this;
    }

    public IdentityOnFidoPage clickBeginVerificationButton() {
        this.wait.until(d -> beginVerificationButton.isDisplayed());
        this.beginVerificationButton.click();
        return this;
    }

    public void setIdentityMethod(IdentityFidoStrategy identityMethod){
        this.identityFidoStrategy = identityMethod;
        PageFactory.initElements(driver, this.identityFidoStrategy);
    }

    public IdentityFidoStrategy getIdentityFidoStrategy() {
        return identityFidoStrategy;
    }

    public void processIdentity(Map<String,String> identityData){
        getIdentityFidoStrategy().selectMethod(identityData.get("identityMethodName"));
        getIdentityFidoStrategy().doUploadImages(identityData.get("imagePath"));
        getIdentityFidoStrategy().takeSelfie(identityData.get("photoPath"));
    }

}


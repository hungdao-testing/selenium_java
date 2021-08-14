package com.aspire.loan.ui.pages.onboarding;

import com.aspire.loan.ui.components.SideBar;
import com.aspire.loan.config.AppConfig;
import com.aspire.loan.ui.AbstractBasePage;
import com.aspire.loan.ui.pages.onboarding.kyc.IdentityFidoStrategy;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

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
        driver.get(AppConfig.getBaseUrl() + "/onboarding/identify-onfido");
        return this;
    }

    @Override
    public void isAt() {
        super.isAt();
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


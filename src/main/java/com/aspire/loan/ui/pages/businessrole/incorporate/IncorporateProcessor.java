package com.aspire.loan.ui.pages.businessrole.incorporate;

import com.aspire.loan.ui.AbstractBasePage;
import com.aspire.loan.ui.pages.businessrole.BusinessRoleProcessor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;

import java.util.Map;

public class IncorporateProcessor extends AbstractBasePage implements BusinessRoleProcessor {

    @FindBy(xpath = "//div[@class='aspire-cta-screen__content']/div[normalize-space()='Incorporate your business in Singapore with Aspire Kickstart']")
    private WebElement mainContent;

    @FindBy(xpath = "//button[.='How it works +']")
    private WebElement howItWorkButton;

    @FindBy(xpath = "//button[.='Get Started']")
    private WebElement getStartedButton;

    private IncorporateYourCompanyPage incorporateYourCompanyPage;
    private IncorporateCompletionPage incorporateCompletionPage;

    @Override
    public void process(Map<String, String> additionalDetails) {
        isAt();
        incorporateCompany(additionalDetails);
    }

    @Override
    public void isAt() {
        super.isAt();
        LOGGER.info("Wait for Incorporated Overview loaded");
        this.wait.until(ExpectedConditions.urlContains("/incorporated/overview"));
        this.wait.until(d -> mainContent.isDisplayed() && howItWorkButton.isDisplayed() && getStartedButton.isDisplayed());
    }

    public IncorporateProcessor(WebDriver driver) {
        super(driver);
        PageFactory.initElements(driver, this);
        this.incorporateYourCompanyPage = PageFactory.initElements(driver, IncorporateYourCompanyPage.class);
        this.incorporateCompletionPage = PageFactory.initElements(driver, IncorporateCompletionPage.class);
    }

    public void incorporateCompany(Map<String,String> data){
        getStartedButton.click();
        this.incorporateYourCompanyPage.isAt();
        this.incorporateYourCompanyPage.configureIncorporate(data);
        this.incorporateCompletionPage.isAt();
        this.incorporateCompletionPage.waitForReceivedIncorporationTextLoaded();
    }
}

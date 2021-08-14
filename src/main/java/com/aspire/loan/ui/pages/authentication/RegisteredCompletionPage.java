package com.aspire.loan.ui.pages.authentication;

import com.aspire.loan.ui.AbstractBasePage;
import com.aspire.loan.config.GlobalConstants;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class RegisteredCompletionPage extends AbstractBasePage {

    @FindBy(css = "div[class='aspire-cta-screen__content'] p")
    private WebElement successfulRegistrationInformationText;

    @FindBy(xpath = "//button")
    private WebElement continueButton;

    public RegisteredCompletionPage(WebDriver driver) {
        super(driver);
        PageFactory.initElements(driver, this);
    }

    @Override
    public void isAt(){
        super.isAt();
        this.wait.until(ExpectedConditions.urlContains("/register/completed"));
        this.wait.until(ExpectedConditions.textToBePresentInElement
                (successfulRegistrationInformationText, GlobalConstants.SUCCESSFUL_REGISTRATION_INFORMATION));
    }

    public void clickContinueButton(){
        this.wait.until(d -> continueButton.isDisplayed());
        this.continueButton.click();
    }
}

package com.aspire.loan.ui.pages.authentication;

import com.aspire.loan.config.AppMessage;
import com.aspire.loan.ui.BasePage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class RegisteredCompletionPage extends BasePage {

    @FindBy(css = "div[class='aspire-cta-screen__content'] p")
    private WebElement successfulRegistrationInformationText;

    @FindBy(xpath = "//button")
    private WebElement continueButton;

    public RegisteredCompletionPage(WebDriver driver) {
        super(driver);
        PageFactory.initElements(driver, this);
    }

    public boolean isSuccessfulMessageDisplayed(){
        super.isAt();
        LOGGER.info("Waiting for the successful registration message loaded");
        this.wait.until(ExpectedConditions.urlContains("/register/completed"));
        this.wait.until(ExpectedConditions.textToBePresentInElement
                (successfulRegistrationInformationText, AppMessage.SUCCESSFUL_REGISTRATION_INFORMATION));
        return true;
    }

    public void clickContinueButton(){
        clickOnVisibleElement(continueButton);
        LOGGER.info("Finish registration");
    }
}

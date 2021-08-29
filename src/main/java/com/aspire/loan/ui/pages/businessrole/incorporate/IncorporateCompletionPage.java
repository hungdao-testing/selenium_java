package com.aspire.loan.ui.pages.businessrole.incorporate;

import com.aspire.loan.config.AppMessage;
import com.aspire.loan.config.GlobalConstants;
import com.aspire.loan.ui.AbstractBasePage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class IncorporateCompletionPage extends AbstractBasePage {

    @FindBy(xpath = "//button[contains(.,'Log Out')]")
    private WebElement logOutButton;

    @FindBy(xpath = "//button[contains(.,'Visit our blog')]")
    private WebElement visitBlog;

    @FindBy(xpath = "//div[@class='aspire-cta-screen__content']")
    private WebElement mainContent;

    public IncorporateCompletionPage(WebDriver driver) {
        super(driver);
    }

    @Override
    public void isAt() {
        super.isAt();
        LOGGER.info("Waiting for the Completion page loaded");
        this.wait.until(ExpectedConditions.urlContains("/incorporated/completed"));
        this.wait.until(d -> logOutButton.isDisplayed() && logOutButton.isDisplayed());
    }

    public void waitForReceivedIncorporationTextLoaded(){
        this.wait.until(
                d -> mainContent.findElement(By.xpath("div[1]")).getText().contains("Hurray!"));
        this.wait.until(
                d -> mainContent.findElement(By.xpath("div[2]")).getText().contains(AppMessage.RECEIVED_INCORPORATION));
    }
}

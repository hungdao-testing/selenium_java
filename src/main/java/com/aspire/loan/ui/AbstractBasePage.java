package com.aspire.loan.ui;

import com.aspire.loan.config.GlobalConstants;
import com.aspire.loan.ui.utils.WaitHelper;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public abstract class AbstractBasePage {

    protected WebDriver driver;
    protected WebDriverWait wait;
    protected JavascriptExecutor js;
    protected Logger LOGGER = LoggerFactory.getLogger(this.getClass().getSimpleName());


    public AbstractBasePage(WebDriver driver) {
        this.driver = driver;
        wait = new WebDriverWait(driver, GlobalConstants.EXPLICIT_WAIT_TIMEOUT);
        js = (JavascriptExecutor) driver;
    }

    public void isAt() {
        waitForPageIsReady();
    }

    protected void inputTextToVisibleField(WebElement element, String text) {
        LOGGER.info("Wait for a text_field to be visible, then input '{}'", text);
        this.wait.until(d -> element.isDisplayed() && element.isEnabled());
        element.click();
        element.sendKeys(text);
    }

    protected void clickOnVisibleElement(WebElement element) {
        LOGGER.info("Wait for an element to be clickable, then click on it");
        this.wait.until(d -> element.isDisplayed() && element.isEnabled());
        element.click();
    }

    public void waitForPageIsReady() {
        LOGGER.info("Wait for page is ready to take action");
        WaitHelper.waitUntilLoadingBarInvisible(driver);
        WaitHelper.waitUntilNoSpinnerDisplayed(driver, wait);
        wait.until(WaitHelper.waitUntilNoActiveAjaxCalled(driver, wait));
    }


}

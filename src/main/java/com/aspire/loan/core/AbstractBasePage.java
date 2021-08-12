package com.aspire.loan.core;

import com.aspire.loan.helpers.WaitHelper;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public abstract class AbstractBasePage {

    protected WebDriver driver;
    protected WebDriverWait wait;
    protected Properties prop;
    protected JavascriptExecutor js;


    public AbstractBasePage(WebDriver driver) {
        this.driver = driver;
        wait = new WebDriverWait(driver, GlobalConstants.EXPLICIT_WAIT_TIMEOUT);
        js = (JavascriptExecutor) driver;
    }

    public void isAt() {
        waitForPageIsReady();
    };

    protected void inputTextToVisibleField(WebElement element, String text) {
        this.wait.until(d -> element.isDisplayed() && element.isEnabled());
        element.click();
        element.sendKeys(text);
    }

    protected void clickOnVisibleElement(WebElement element){
        this.wait.until(d -> element.isDisplayed() && element.isEnabled());
        element.click();
    }

    public void waitForPageIsReady() {
        WaitHelper.waitUntilLoadingBarInvisible(driver);
        WaitHelper.waitUntilNoSpinnerDisplayed(driver, wait);
        wait.until(WaitHelper.waitUntilNoActiveAjaxCalled(driver, wait));
    }


}

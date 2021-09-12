package com.aspire.loan.ui;

import com.aspire.loan.config.GlobalConstants;
import com.aspire.loan.elementhelper.ElementConstants;
import com.aspire.loan.ui.utils.WaitHelper;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

public class BasePage {

    protected WebDriver driver;
    protected WebDriverWait wait;
    protected Logger LOGGER = LoggerFactory.getLogger(this.getClass().getSimpleName());
    protected JavascriptExecutor js;


    public BasePage(WebDriver driver) {
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
        LOGGER.info("Wait for an element to be clickable");
        this.wait.until(d -> element.isDisplayed() && element.isEnabled());
        element.click();
    }

    protected void searchAndSelectTextInDropdownField(WebElement searchDropdown, String text) {
        LOGGER.info("Wait for an element to be clickable");
        this.wait.until(d -> searchDropdown.isDisplayed() && searchDropdown.isEnabled());
        try{
            searchDropdown.click();
            this.wait.until(d -> driver.findElement(ElementConstants.DROPDOWN_MENU_CSS_SELECTOR).isDisplayed());
            searchDropdown.sendKeys(text);
            driver.findElements(ElementConstants.DROPDOWN_ITEM_CSS_SELECTOR)
                    .stream()
                    .filter(e -> e.getText().equalsIgnoreCase(text))
                    .findFirst()
                    .get()
                    .click();
        } catch (StaleElementReferenceException e) {
            System.out.println("Ignore Unharmful 'Stale Element' exception");
        }
    }

    public void waitForPageIsReady() {
        LOGGER.info("Wait for page is ready to take action");
        WaitHelper.waitUntilLoadingBarInvisible(driver);
        WaitHelper.waitUntilNoSpinnerDisplayed(driver, wait);
        WaitHelper.waitUntilNoInnerLoadingInFields(driver, wait);
        wait.until(WaitHelper.waitUntilNoActiveAjaxCalled(driver, wait));
    }

    public  String getHiddenTextOnInputField(String cssLocator) {
        return (String) js.executeScript("return document.querySelector(\"" + cssLocator + "\").value;");
    }

}

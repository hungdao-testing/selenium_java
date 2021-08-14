package com.aspire.loan.controlhelpers;

import com.google.common.util.concurrent.Uninterruptibles;
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.slf4j.LoggerFactory;

import java.util.List;
import java.util.concurrent.TimeUnit;


public interface IDropdown {

    default void scrollAndSelectOption(WebDriver driver, WebDriverWait wait, String value){
        By dropdownMenu = By.cssSelector(".q-menu");
        By dropdownItems = By.cssSelector(".q-item__label");
        wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(dropdownMenu));
        wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(dropdownItems));
        LoggerFactory.getLogger(this.getClass().getInterfaces()[0].getSimpleName())
                .info("Start scrolling dropdown to find value '{}' to select", value);
        boolean isContinueScrolled = false;
        while (!isContinueScrolled) {
            List<WebElement> dropDownOptions = driver.findElements(dropdownItems);
            for (WebElement e : dropDownOptions) {
                try {
                    ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", e);
                    if (e.getText().equalsIgnoreCase(value) && e.isDisplayed()) {
                        e.click();
                        isContinueScrolled = true;
                        break;
                    }
                } catch (StaleElementReferenceException ex) {
                    dropDownOptions = driver.findElements(dropdownItems);
                } finally {
                    Uninterruptibles.sleepUninterruptibly(150, TimeUnit.MILLISECONDS);
                }
            }
        }
    }

    default void selectOptionFromShortList(WebDriver driver, WebDriverWait wait, String value){
        By dropdownMenu = By.cssSelector(".q-menu");
        By dropdownItems = By.cssSelector(".q-item__label");
        wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(dropdownMenu));
        wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(dropdownItems));

        LoggerFactory.getLogger(this.getClass().getInterfaces()[0].getSimpleName())
                .info("Start scrolling dropdown to find value '{}' to select", value);

        List<WebElement> dropDownOptions = driver.findElements(dropdownItems);
        for (WebElement e : dropDownOptions) {
            if(e.getText().equalsIgnoreCase(value)){
                if(e.isDisplayed()){
                    e.click();
                }else{
                    ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", e);
                    Uninterruptibles.sleepUninterruptibly(150, TimeUnit.MILLISECONDS);
                    ((JavascriptExecutor) driver).executeScript("arguments[0].click();", e);
                }
                break;
            }
        }
    }

}

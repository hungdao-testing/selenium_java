package com.aspire.loan.controlhelper;

import com.aspire.loan.core.GlobalConstants;
import com.google.common.util.concurrent.Uninterruptibles;
import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.util.List;
import java.util.concurrent.TimeUnit;


public interface IDropdown {

    default void selectOptionFromDropdown(WebDriver driver, WebElement dropdownField, String value) {
        WebDriverWait wait = new WebDriverWait(driver, GlobalConstants.EXPLICIT_WAIT_TIMEOUT);
        try{
            Uninterruptibles.sleepUninterruptibly(500, TimeUnit.MILLISECONDS);
            new Actions(driver).moveToElement(dropdownField).click().build().perform();
        }catch (StaleElementReferenceException e){
            wait.until(d -> dropdownField.isDisplayed());
            dropdownField.click();
        }

        boolean isContinueScrolled = false;
        while (!isContinueScrolled) {
            WebElement dropDownMenu = driver.findElement(By.cssSelector("div[tabindex='-1'].q-menu"));
            List<WebElement> dropDownOptions = driver.findElements(By.cssSelector(".q-item__label"));
            for (WebElement e : dropDownOptions) {
                wait.until(ExpectedConditions.visibilityOf(dropDownMenu));
                wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.cssSelector(".q-item__label")));
                try {
                    ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", e);
                    if (e.getText().equalsIgnoreCase(value)) {
                        e.click();
                        isContinueScrolled = true;
                        break;
                    }
                } catch (StaleElementReferenceException ex) {
                    System.out.println("DomElement is changing:: " + ex.toString());
                } finally {
                    Uninterruptibles.sleepUninterruptibly(200, TimeUnit.MILLISECONDS);
                }
            }
        }

    }

    default void selectCheckboxFromDropdownList(WebDriver driver, String value) {
        WebDriverWait wait = new WebDriverWait(driver, GlobalConstants.EXPLICIT_WAIT_TIMEOUT);
        Uninterruptibles.sleepUninterruptibly(250, TimeUnit.MILLISECONDS);
        boolean isContinueScrolled = false;
        while (!isContinueScrolled) {
            WebElement dropDownMenu = driver.findElement(By.cssSelector("div[tabindex='-1'].q-menu"));
            List<WebElement> dropDownOptions = driver.findElements(By.cssSelector(".q-item__label"));
            wait.until(ExpectedConditions.refreshed(ExpectedConditions.visibilityOf(dropDownMenu)));
            for (WebElement e : dropDownOptions) {
                try {
                    ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", e);
                    if (e.getText().equalsIgnoreCase(value)) {
                        e.click();
                        isContinueScrolled = true;
                        break;
                    }
                } catch (StaleElementReferenceException ex) {
                    System.out.println("DomElement is changing:: " + ex.toString());
                } finally {
                    Uninterruptibles.sleepUninterruptibly(200, TimeUnit.MILLISECONDS);
                }
            }
        }

    }
}

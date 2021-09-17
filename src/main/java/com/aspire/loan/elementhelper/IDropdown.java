package com.aspire.loan.elementhelper;

import com.aspire.loan.config.GlobalConstants;
import com.google.common.util.concurrent.Uninterruptibles;
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.slf4j.LoggerFactory;

import java.util.List;
import java.util.concurrent.TimeUnit;


public interface IDropdown {

    default void waitForDropDownLoaded(WebDriverWait wait, By dropdownMenu, By dropdownItems){
        wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(dropdownMenu));
        wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(dropdownItems));
    }

    default void scrollDropdownAndSelectValue(WebDriver driver, WebDriverWait wait, String value){
        JavascriptExecutor js = (JavascriptExecutor) driver;
        boolean isContinueScrolled = false;

        waitForDropDownLoaded(wait,
                ElementConstants.DROPDOWN_MENU_CSS_SELECTOR, ElementConstants.DROPDOWN_ITEM_CSS_SELECTOR);

        LoggerFactory.getLogger(this.getClass().getInterfaces()[0].getSimpleName())
                .info("Start scrolling dropdown to find value '{}' to select", value);

        while (!isContinueScrolled) {
            List<WebElement> dropDownOptions = driver.findElements(ElementConstants.DROPDOWN_ITEM_CSS_SELECTOR);
            for (WebElement e : dropDownOptions) {
                try {
                    js.executeScript("arguments[0].scrollIntoView(true);", e);
                    if (e.getText().equalsIgnoreCase(value) && e.isDisplayed()) {
                        e.click();
                        isContinueScrolled = true;
                        break;
                    }
                } catch (StaleElementReferenceException ex) {
                    System.out.println("Ignore Unharmful 'Stale Element' exception" + ex.toString());
                } finally {
                    Uninterruptibles.sleepUninterruptibly(GlobalConstants.HARD_WAIT_TIMEOUT, TimeUnit.MILLISECONDS);
                }
            }
        }
    }

    default void selectOptionFromShortList(WebDriver driver, WebDriverWait wait, String value){
        JavascriptExecutor js = (JavascriptExecutor) driver;
        waitForDropDownLoaded(wait,
                ElementConstants.DROPDOWN_MENU_CSS_SELECTOR, ElementConstants.DROPDOWN_ITEM_CSS_SELECTOR);

        LoggerFactory.getLogger(this.getClass().getInterfaces()[0].getSimpleName())
                .info("Start scrolling dropdown to find value '{}' to select", value);

        List<WebElement> dropDownOptions = driver.findElements(ElementConstants.DROPDOWN_ITEM_CSS_SELECTOR);
        for (WebElement e : dropDownOptions) {
            if(e.getText().equalsIgnoreCase(value)){
                if(e.isDisplayed()){
                    e.click();
                }else{
                    js.executeScript("arguments[0].scrollIntoView(true);", e);
                    Uninterruptibles.sleepUninterruptibly(GlobalConstants.HARD_WAIT_TIMEOUT, TimeUnit.MILLISECONDS);
                    js.executeScript("arguments[0].click();", e);
                }
                break;
            }
        }
    }

}

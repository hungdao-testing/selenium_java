package com.aspire.loan.helpers;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;


public class WaitHelper {

    private static By spinnerElementByCss = By.cssSelector(".q-spinner");
    private static By loadingProgressBar = By.cssSelector(".q-loading-bar");

    public static void waitUntilNoSpinnerDisplayed(WebDriver driver, WebDriverWait wait) {
        wait.until(d -> ExpectedConditions.invisibilityOf(driver.findElement(spinnerElementByCss)));
    }

    public static boolean waitUntilLoadingBarInvisible(WebDriver driver) {
        Boolean loaded = true;
        try {
            String loadingBarAttr = driver.findElement(loadingProgressBar).getAttribute("style");
            while (loadingBarAttr.contains("opacity: 1;")) {
                loadingBarAttr = driver.findElement(loadingProgressBar).getAttribute("style");
                loaded = false;
            }
        } catch (Exception var6) {
            loaded = false;
        }
        return loaded;
    }

    public static ExpectedCondition<Boolean> waitUntilNoActiveAjaxCalled(WebDriver driver, WebDriverWait wait) {
        return new ExpectedCondition<Boolean>() {
            private static final String SCRIPT = "if (typeof jQuery === 'undefined') return true;\nif (jQuery.active != 0) return false;\nreturn true;";

            public Boolean apply(WebDriver driver) {
                Boolean result;
                try {
                    result = (Boolean) ((JavascriptExecutor) driver).executeScript("if (typeof jQuery === 'undefined') return true;\nif (jQuery.active != 0) return false;\nreturn true;", new Object[0]);
                } catch (Exception var4) {
                    result = Boolean.FALSE;
                }

                return result;
            }

            public String toString() {
                return "no active Ajax requests";
            }
        };
    }

}

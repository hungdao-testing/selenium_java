package com.aspire.loan.core;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedCondition;
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

    public String getBaseUrl() {
        try(FileInputStream fis = new FileInputStream(GlobalConstants.propertyFilePath)){
            this.prop = new Properties();
            this.prop.load(fis);
            return  this.prop.getProperty("baseUrl");
        } catch (IOException e) {
            e.printStackTrace();
        }
        return "There is no property baseUrl";
    }

    public static final ExpectedCondition<Boolean> noActiveAjaxRequest() {
        return new ExpectedCondition<Boolean>() {
            private static final String SCRIPT = "if (typeof jQuery === 'undefined') return true;\nif (jQuery.active != 0) return false;\nreturn true;";

            public Boolean apply(WebDriver driver) {
                Boolean result;
                try {
                    result = (Boolean)((JavascriptExecutor)driver).executeScript("if (typeof jQuery === 'undefined') return true;\nif (jQuery.active != 0) return false;\nreturn true;", new Object[0]);
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

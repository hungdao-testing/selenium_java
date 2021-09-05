package com.aspire.loan.specs.thread;

import com.aspire.loan.config.DriverFactory;
import com.aspire.loan.config.GlobalConstants;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.annotations.*;

import java.util.concurrent.TimeUnit;

public abstract class AbstractThreadBaseTestNG {
    public WebDriver driver;
    protected Logger LOGGER = LoggerFactory.getLogger(this.getClass().getSimpleName());
    protected JavascriptExecutor js;
//    private ThreadLocal<WebDriver> webdriver = new ThreadLocal<WebDriver>();

    @Parameters("BrowserType")
    @BeforeMethod
    public void setUpBrowser(@Optional("chrome") String browser)  {
        LOGGER.info("BeforeTest: Setting up browser");
//        driver = new DriverThreadFactory().getDriver(browser);
        DriverThreadFactory webDriverFactory = new DriverThreadFactory();
        webDriverFactory.setDriver();
//        webdriver.set(webDriverFactory.getDriver());
        driver = webDriverFactory.getDriver();
        driver.manage().window().maximize();
        driver.manage().timeouts().pageLoadTimeout(GlobalConstants.PAGE_LOAD_TIMEOUT, TimeUnit.SECONDS);
        js = (JavascriptExecutor) driver;
    }


    @AfterMethod
    public void clearLocalStorage(){
        LOGGER.info("Clear local storage");
        this.js.executeScript("window.localStorage.clear()");
        if(driver!= null){
            driver.quit();
        }
    }

//    @AfterClass
//    public void tearDown() {
//        if(driver != null){
//            LOGGER.info("After Test: Close browser");
//            driver.quit();
//        }
//    }

}

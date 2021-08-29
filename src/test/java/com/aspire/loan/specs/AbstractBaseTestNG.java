package com.aspire.loan.specs;

import com.aspire.loan.config.DriverFactory;
import com.aspire.loan.config.GlobalConstants;
import org.openqa.selenium.WebDriver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.annotations.*;

import java.util.concurrent.TimeUnit;

public abstract class AbstractBaseTestNG {
    public WebDriver driver;
    protected Logger LOGGER = LoggerFactory.getLogger(this.getClass().getSimpleName());

    @Parameters("browser")
    @BeforeTest
    public void setUpBrowser(@Optional("chrome") String browser)  {
        LOGGER.info("BeforeClass: Setting up browser");
        driver = DriverFactory.getDriver(browser);
        driver.manage().window().maximize();
        driver.manage().timeouts().pageLoadTimeout(GlobalConstants.PAGE_LOAD_TIMEOUT, TimeUnit.SECONDS);
    }


    @AfterTest
    public void tearDown() {
        LOGGER.info("After Test: Close browser");
        this.driver.quit();
    }
}

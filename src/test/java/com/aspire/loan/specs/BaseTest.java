package com.aspire.loan.specs;

import com.aspire.loan.config.DriverFactory;
import com.aspire.loan.config.GlobalConstants;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.*;

import java.util.concurrent.TimeUnit;


public class BaseTest {
    public WebDriver driver;

    @Parameters("browser")
    @BeforeTest
    public void setUpBrowser(@Optional("chrome") String browser)  {
        driver = DriverFactory.getDriver(browser);
        driver.manage().window().maximize();
        driver.manage().timeouts().pageLoadTimeout(GlobalConstants.PAGE_LOAD_TIMEOUT, TimeUnit.SECONDS);
    }

    @AfterTest
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }
}

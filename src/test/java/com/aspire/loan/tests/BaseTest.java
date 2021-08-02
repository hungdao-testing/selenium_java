package com.aspire.loan.tests;

import com.aspire.loan.core.GlobalConstants;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeTest;
import java.util.concurrent.TimeUnit;


public class BaseTest {
    public WebDriver driver;

    @BeforeTest
    public void setupClass()  {
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.manage().timeouts().pageLoadTimeout(GlobalConstants.PAGE_LOAD_TIMEOUT, TimeUnit.SECONDS);
        driver.manage().timeouts().implicitlyWait(GlobalConstants.IMPLICIT_WAIT_TIMEOUT, TimeUnit.SECONDS);
    }

    @AfterClass
    public void teardownClass() {
        if (driver != null) {
            driver.close();
            driver.quit();
        }
    }
}

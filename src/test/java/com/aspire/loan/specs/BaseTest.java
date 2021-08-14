package com.aspire.loan.specs;

import com.aspire.loan.config.GlobalConstants;
import com.aspire.loan.data.DataManagement;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.PageLoadStrategy;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeTest;
import java.util.concurrent.TimeUnit;


public class BaseTest {
    public WebDriver driver;

    @BeforeTest
    public void setupClass()  {
        ChromeOptions chromeOptions = new ChromeOptions();

        /*
        * https://www.selenium.dev/documentation/en/webdriver/page_loading_strategy/
        * */
        chromeOptions.setPageLoadStrategy(PageLoadStrategy.NORMAL);
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver(chromeOptions);
        driver.manage().window().maximize();
        driver.manage().timeouts().pageLoadTimeout(GlobalConstants.PAGE_LOAD_TIMEOUT, TimeUnit.SECONDS);
    }

    @AfterClass
    public void teardownClass() {
        if (driver != null) {
            driver.close();
            driver.quit();
        }
    }
}

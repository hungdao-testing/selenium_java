package com.aspire.loan.specs;

import com.aspire.loan.config.DriverFactory;
import com.aspire.loan.config.GlobalConstants;
import com.aspire.loan.datagenerator.RegistrationGenerator;
import com.aspire.loan.datagenerator.builder.BusinessInfoBuilder;
import com.aspire.loan.models.uidata.BusinessModel;
import com.aspire.loan.models.uidata.PersonalModel;
import com.aspire.loan.ui.common.authentication.RegistrationInfo;
import com.aspire.loan.ui.common.authentication.ApiRegistration;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.*;

import java.util.concurrent.TimeUnit;

public abstract class BaseTestNG {
    public WebDriver driver;
    protected JavascriptExecutor js;


    @Parameters("BrowserType")
    @BeforeClass
    public void setUpBrowser(@Optional("chrome") String browser)  {
        driver = DriverFactory.getDriver(browser);
        driver.manage().window().maximize();
        driver.manage().timeouts().pageLoadTimeout(GlobalConstants.PAGE_LOAD_TIMEOUT, TimeUnit.SECONDS);
        js = (JavascriptExecutor) driver;
    }


    @AfterMethod
    public void clearLocalStorage(){
        this.js.executeScript("window.localStorage.clear()");
    }

    @AfterClass
    public void tearDown() {
        if(driver != null){
            driver.quit();
        }
    }

}

package com.aspire.loan.specs;

import com.aspire.loan.config.DriverFactory;
import com.aspire.loan.config.GlobalConstants;
import com.aspire.loan.datagenerator.RegistrationDataGenerator;
import com.aspire.loan.datagenerator.builder.BusinessDataBuilder;
import com.aspire.loan.model.uidata.BusinessInfo;
import com.aspire.loan.model.uidata.PersonalInfo;
import com.aspire.loan.model.uidata.RegistrationInfo;
import com.aspire.loan.ui.common.authentication.ApiRegistration;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.annotations.*;

import java.util.concurrent.TimeUnit;

public abstract class BaseTestNG {
    public WebDriver driver;
    protected Logger LOGGER = LoggerFactory.getLogger(this.getClass().getSimpleName());
    protected JavascriptExecutor js;
    protected BusinessInfo businessInfo;
    protected PersonalInfo personalInfo;

    @BeforeSuite
    public void setUpData(){
        RegistrationInfo validAccount = RegistrationDataGenerator.generateValidRegistrationData();
        new ApiRegistration().create(validAccount);
        this.personalInfo = validAccount.getPersonalInfo();
        this.businessInfo = new BusinessDataBuilder().generateStandardCorporateBusiness();
    }

    @Parameters("BrowserType")
    @BeforeClass
    public void setUpBrowser(@Optional("chrome") String browser)  {
        LOGGER.info("BeforeTest: Setting up browser");
        driver = DriverFactory.getDriver(browser);
        driver.manage().window().maximize();
        driver.manage().timeouts().pageLoadTimeout(GlobalConstants.PAGE_LOAD_TIMEOUT, TimeUnit.SECONDS);
        js = (JavascriptExecutor) driver;
    }


    @AfterMethod
    public void clearLocalStorage(){
        LOGGER.info("Clear local storage");
        this.js.executeScript("window.localStorage.clear()");
    }

    @AfterClass
    public void tearDown() {
        if(driver != null){
            LOGGER.info("After Test: Close browser");
            driver.quit();
        }
    }

}

package com.aspire.loan.specs.thread;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import java.util.HashMap;
import java.util.Map;
import java.util.function.Supplier;

public class DriverThreadFactory {
    //http://makeseleniumeasy.com/2020/05/27/threadlocal-static-webdriver-for-parallel-execution/

    //    private ThreadLocal<WebDriver> driverThreadLocal = new ThreadLocal<WebDriver>();
//
//    private static Supplier<WebDriver> chrome = () -> {
//        WebDriverManager.chromedriver().setup();
//        return new ChromeDriver();
//    };
//
//    private static Supplier<WebDriver> firefox = () -> {
//        WebDriverManager.firefoxdriver().setup();
//        return new FirefoxDriver();
//    };
//
//    private static Map<String, Supplier<WebDriver>> driverMapping = new HashMap<>();
//
//    static {
//        driverMapping.put("chrome", chrome);
//        driverMapping.put("firefox", firefox);
//    }
//
//    public WebDriver getDriver(String browserName) {
//        driverThreadLocal.set(driverMapping.get(browserName).get());
//        return driverThreadLocal.get();
//    }
    private WebDriver driver;

    public void setDriver() {
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
    }

    public WebDriver getDriver() {
        return driver;
    }

}

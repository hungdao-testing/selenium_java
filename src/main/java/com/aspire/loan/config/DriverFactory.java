package com.aspire.loan.config;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.PageLoadStrategy;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;

import java.util.HashMap;
import java.util.Map;
import java.util.function.Supplier;

public class DriverFactory {

    private static Supplier<WebDriver> chrome = () -> {
        WebDriverManager.chromedriver().setup();
        return new ChromeDriver();
    };

    private static Supplier<WebDriver> firefox = () -> {
        WebDriverManager.firefoxdriver().setup();
        return new FirefoxDriver();
    };

    private static Map<String, Supplier<WebDriver>> driverMapping = new HashMap<>();

    static {
        driverMapping.put("chrome", chrome);
        driverMapping.put("firefox", firefox);
    }

    public static WebDriver getDriver(String browserName) {
        return driverMapping.get(browserName).get();
    }


}

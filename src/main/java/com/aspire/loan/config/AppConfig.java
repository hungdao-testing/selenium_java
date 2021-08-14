package com.aspire.loan.config;

import org.slf4j.LoggerFactory;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class AppConfig {

    protected static Properties prop = new Properties();

    private static String getProp(String propName){
        try (FileInputStream fis = new FileInputStream(GlobalConstants.propertyFilePath)) {
            prop.load(fis);
            return prop.getProperty(propName);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return "There is no property " + propName;
    }

    public static String getBaseUrl() {
        return getProp("baseUrl");
    }

    public static String getApiUrl() {
        return getProp("apiUrl");
    }

    public static String getBrowserName(){
        return getProp("browser");
    }
}

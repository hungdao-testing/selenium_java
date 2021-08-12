package com.aspire.loan.config;

import com.aspire.loan.core.GlobalConstants;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class AppConfig {

    protected static Properties prop = new Properties();

    public static String getBaseUrl() {
        try (FileInputStream fis = new FileInputStream(GlobalConstants.propertyFilePath)) {
            prop.load(fis);
            return prop.getProperty("baseUrl");
        } catch (IOException e) {
            e.printStackTrace();
        }
        return "There is no property baseUrl";
    }

    public static String getApiUrl() {
        try (FileInputStream fis = new FileInputStream(GlobalConstants.propertyFilePath)) {
            prop.load(fis);
            return prop.getProperty("apiUrl");
        } catch (IOException e) {
            e.printStackTrace();
        }
        return "There is no property apiUrl";
    }
}

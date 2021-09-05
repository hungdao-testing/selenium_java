package com.aspire.loan.specs;

import com.aspire.loan.config.AppConfig;
import org.testng.annotations.Test;

public class ConfigTest extends AbstractBaseTestNG{

    @Test
    public void test_import(){
        System.out.println("Base Url :" + AppConfig.getBaseUrl());
        System.out.println("Locale Url :" + AppConfig.getLocaleFile());

    }
}

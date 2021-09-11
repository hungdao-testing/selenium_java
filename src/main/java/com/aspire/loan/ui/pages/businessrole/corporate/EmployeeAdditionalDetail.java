package com.aspire.loan.ui.pages.businessrole.corporate;

import com.aspire.loan.ui.BasePage;
import com.aspire.loan.ui.pages.businessrole.BusinessRoleProcessor;
import org.openqa.selenium.WebDriver;

import java.util.Map;

public class EmployeeAdditionalDetail extends BasePage implements BusinessRoleProcessor {

    @Override
    public void process(Map<String, String> additionalDetails) {

    }

    public EmployeeAdditionalDetail(WebDriver driver) {
        super(driver);
    }
}

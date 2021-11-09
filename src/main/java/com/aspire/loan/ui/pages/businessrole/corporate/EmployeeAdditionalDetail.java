package com.aspire.loan.ui.pages.businessrole.corporate;

import com.aspire.loan.models.uidata.AdditionalRoleDetail;
import com.aspire.loan.ui.BasePage;
import com.aspire.loan.ui.pages.businessrole.BusinessRoleProcessor;
import org.openqa.selenium.WebDriver;

public class EmployeeAdditionalDetail extends BasePage implements BusinessRoleProcessor {

    @Override
    public void process(AdditionalRoleDetail additionalData) {

    }

    public EmployeeAdditionalDetail(WebDriver driver) {
        super(driver);
    }
}

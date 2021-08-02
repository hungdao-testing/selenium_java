package com.aspire.loan.pages.businessrole;

import com.aspire.loan.core.AbstractBasePage;
import com.aspire.loan.dataschema.AdditionalDetail;
import org.openqa.selenium.WebDriver;

import java.util.List;
import java.util.Map;
import java.util.Objects;


public abstract class IBusinessRoleTemplate extends AbstractBasePage {

    public IBusinessRoleTemplate(WebDriver driver) {
        super(driver);
    }

    public abstract void isAt();
    public abstract void selectRole();
    public abstract void processDetail(AdditionalDetail additionalDetailData);

    public void processBusinessRoleStep(AdditionalDetail additionalDetailData){
        isAt();
        selectRole();
        processDetail(additionalDetailData);
    }
}

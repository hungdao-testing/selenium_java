package com.aspire.loan.ui.pages.businessrole;

import com.aspire.loan.model.uidata.configtype.BusinessRoleType;
import com.aspire.loan.ui.pages.businessrole.incorporate.IncorporateProcessor;
import com.aspire.loan.ui.pages.businessrole.corporate.DirectorAdditionalDetail;
import com.aspire.loan.ui.pages.businessrole.corporate.EmployeeAdditionalDetail;
import org.openqa.selenium.WebDriver;

import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

public class BusinessRoleFactory {

    private static final Function<WebDriver, BusinessRoleProcessor> directorRole = DirectorAdditionalDetail::new;
    private static final Function<WebDriver, BusinessRoleProcessor> employeeRole = EmployeeAdditionalDetail::new;
    private static final Function<WebDriver, BusinessRoleProcessor> incorporate = IncorporateProcessor::new;

    private static final Map<BusinessRoleType, Function<WebDriver, BusinessRoleProcessor>> roleMap = new HashMap<>();

    static {
        roleMap.put(BusinessRoleType.DIRECTOR, directorRole);
        roleMap.put(BusinessRoleType.EMPLOYEE, employeeRole);
        roleMap.put(BusinessRoleType.ENTREPRENEUR, incorporate);

    }


    public static BusinessRoleProcessor loadAdditionalPage(BusinessRoleType type, WebDriver driver){
        return roleMap.get(type).apply(driver);
    }
}

package com.aspire.loan.specs.useraccount;

import com.aspire.loan.data.BusinessRoleType;
import com.aspire.loan.specs.BaseTest;
import com.aspire.loan.ui.pages.authentication.LoginPage;
import com.aspire.loan.ui.pages.businessrole.RoleSelectorPage;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.util.HashMap;
import java.util.Map;

public class BusinessRoleTest extends BaseTest {

    private LoginPage loginPage;
    private RoleSelectorPage roleSelectorPage;

    @BeforeTest
    public void beforeTest(){
        this.loginPage = new LoginPage(driver);
        this.roleSelectorPage = new RoleSelectorPage(driver);


    }

    @Test(dataProvider = "getDirectorData")
    public void select_director_role_test(Map<String,String> data){
        this.loginPage.goTo().isAt();
        this.loginPage.loginByEmail("learning.six@yopmail.com").waitForOtpSectionLoaded().inputOtp("1234");
        this.roleSelectorPage.isAt();
        roleSelectorPage.selectRoleAndProcess(BusinessRoleType.DIRECTOR, data);

    }

    @Test(dataProvider = "getEntreData")
    public void select_entre_role_test(Map<String,String> data){
        this.loginPage.goTo().isAt();
        this.loginPage.loginByEmail("learning.five@yopmail.com").waitForOtpSectionLoaded().inputOtp("1234");
        this.roleSelectorPage.isAt();
        roleSelectorPage.selectRoleAndProcess(BusinessRoleType.ENTREPRENEUR, data);

    }

    @DataProvider
    public Object[] getEntreData(){
        Map<String,String> businessDetails = new HashMap<>();
        businessDetails.put("country", "Singapore");
        businessDetails.put("package", "KICKSTART");
        businessDetails.put("businessName", "Test Business");
        businessDetails.put("website", "http://www.local.com");
        businessDetails.put("numberOfShareholders", "12");
        businessDetails.put("financialYearEndDate", "31 December");

        return new Object[]{
                businessDetails
        };
    }

    @DataProvider
    public Object[] getDirectorData(){
        Map<String,String> additionalDetails = new HashMap<>();
        additionalDetails.put("country", "Singapore");
        additionalDetails.put("solutions", "iste,ut");

        return new Object[]{
                additionalDetails
        };
    }
}

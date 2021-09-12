package com.aspire.loan.specs.useraccount;

import com.aspire.loan.model.uidata.configtype.BusinessRoleType;
import com.aspire.loan.datagenerator.RegistrationDataGenerator;
import com.aspire.loan.model.uidata.RegistrationInfo;
import com.aspire.loan.specs.BaseTestNG;
import com.aspire.loan.ui.common.authentication.ApiRegistration;
import com.aspire.loan.ui.pages.authentication.LoginPage;
import com.aspire.loan.ui.pages.businessrole.RoleSelectorPage;
import org.testng.annotations.*;

import java.util.HashMap;
import java.util.Map;

public class BusinessRoleTest extends BaseTestNG {

    private LoginPage loginPage;
    private RoleSelectorPage roleSelectorPage;
    private RegistrationInfo account;


    @BeforeClass
    public void setUpPage() {
        this.loginPage = new LoginPage(driver);
        this.roleSelectorPage = new RoleSelectorPage(driver);
    }

    @BeforeMethod
    public void login(){
        this.account = RegistrationDataGenerator.generateValidRegistrationData();
        new ApiRegistration().create(account);
        this.loginPage.goTo().isAt();
        this.loginPage.loginByEmail(account.getPersonalInfo().getEmail()).waitForOtpSectionLoaded().inputOtp("1234");
    }

    @Test(dataProvider = "getDirectorData")
    public void select_director_role_test(Map<String,String> data){
        this.roleSelectorPage.isAt();
        roleSelectorPage.selectRoleAndProcess(BusinessRoleType.DIRECTOR, data);
    }

    @Test(dataProvider = "getEntreData")
    public void select_entre_role_test(Map<String,String> data){
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

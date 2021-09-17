package com.aspire.loan.specs.onboarding;


import com.aspire.loan.datagenerator.builder.BuilderSetup;
import com.aspire.loan.datagenerator.builder.BusinessDataBuilder;
import com.aspire.loan.datagenerator.builder.TimeBuilder;
import com.aspire.loan.model.uidata.BusinessInfo;
import com.aspire.loan.model.uidata.RegistrationInfo;
import com.aspire.loan.model.uidata.configtype.BusinessRoleType;
import com.aspire.loan.specs.BaseTestNG;
import com.aspire.loan.ui.pages.BusinessEditPage;
import com.aspire.loan.ui.pages.authentication.LoginPage;
import com.aspire.loan.ui.pages.businessregistration.BusinessRegistrationMethodPage;
import com.aspire.loan.ui.pages.PersonEditPage;
import com.aspire.loan.ui.pages.businessrole.RoleSelectorPage;
import com.google.common.util.concurrent.Uninterruptibles;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.concurrent.TimeUnit;

public class BusinessRegistrationMethodTest extends BaseTestNG {

    BusinessRegistrationMethodPage businessRegistrationPage;
    LoginPage loginPage;
    PersonEditPage personEditPage;
    BusinessEditPage businessEditPage;
    RegistrationInfo account;
    RoleSelectorPage roleSelectorPage;
    BusinessInfo businessInfo;

    @BeforeClass
    public void setUpPage() {

        this.businessRegistrationPage = new BusinessRegistrationMethodPage(driver);
        this.loginPage = new LoginPage(driver);
        this.personEditPage = new PersonEditPage(driver);
        this.businessEditPage = new BusinessEditPage(driver);
        this.roleSelectorPage = new RoleSelectorPage(driver);


        this.businessInfo = new BusinessDataBuilder().generateStandardForBusinessRole(BusinessRoleType.DIRECTOR);

    }

    @BeforeMethod
    public void login(){
        this.loginPage.goTo().isAt();
        this.loginPage.loginByEmail("russell.frami@yahoo.com").waitForOtpSectionLoaded().inputOtp("1234");
    }

    @Test
    public void selectStandardBusiness(){
        this.businessRegistrationPage.isAt();
        this.businessRegistrationPage.selectMethod(this.businessInfo.getRegistrationMethodType());
        this.personEditPage.isAt();
        this.personEditPage
                .setDateOfBirth(
                        TimeBuilder.getDob().get("day"),
                        TimeBuilder.getDob().get("month"),
                        TimeBuilder.getDob().get("year")
                )
                .setNationality(this.businessInfo.getCountry())
                .setGender(BuilderSetup.faker.demographic().sex())
                .clickSubmit();
        Uninterruptibles.sleepUninterruptibly(2, TimeUnit.SECONDS);

        this.businessEditPage.isAt();
        this.businessEditPage
                .setBusinessName(this.businessInfo.getBusinessLegalName())
                .setEntityCategory(this.businessInfo.getEntityCategory())
                .setEntityType(this.businessInfo.getEntityType())
                .setBusinessRegistrationNumber(this.businessInfo.getBusinessRegistrationNumber())
                .setIndustry(this.businessInfo.getIndustry())
                .setSubIndustry(this.businessInfo.getSubIndustry());

    }

}

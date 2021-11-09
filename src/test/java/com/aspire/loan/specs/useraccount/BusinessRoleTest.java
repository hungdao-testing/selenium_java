package com.aspire.loan.specs.useraccount;



import com.aspire.loan.models.uidata.configtype.BusinessRoleType;
import com.aspire.loan.specs.BaseTestNG;
import com.aspire.loan.ui.common.authentication.LoginWF;
import com.aspire.loan.ui.pages.BusinessEditPage;
import com.aspire.loan.ui.pages.PersonEditPage;
import com.aspire.loan.ui.pages.businessregistration.BusinessRegistrationMethodPage;
import com.aspire.loan.ui.pages.businessrole.RoleSelectorPage;
import org.testng.annotations.*;


public class BusinessRoleTest extends BaseTestNG {

    private LoginWF loginWF;
    private RoleSelectorPage roleSelectorPage;
    private BusinessRegistrationMethodPage businessRegistrationPage;
    private PersonEditPage personEditPage;
    private BusinessEditPage businessEditPage;

    @BeforeClass
    public void setUpPage() {
        this.loginWF = new LoginWF(driver);
        this.roleSelectorPage = new RoleSelectorPage(driver);
        this.businessRegistrationPage = new BusinessRegistrationMethodPage(driver);
        this.personEditPage = new PersonEditPage(driver);
        this.businessEditPage = new BusinessEditPage(driver);
    }

    @BeforeMethod
    public void login(){
        this.loginWF.loginWith(this.personalInfo.getEmail());
    }

    @Test
    public void select_director_role_test(){
        this.roleSelectorPage.isAt();
        this.roleSelectorPage.selectRoleAndProcess(
                BusinessRoleType.DIRECTOR,
                this.businessInfo.getAdditionalRoleDetail());

        this.businessRegistrationPage.isAt();
        this.businessRegistrationPage
                .selectMethod(this.businessInfo.getRegistrationMethodType());

        this.personEditPage.isAt();
        this.personEditPage
                .submitPersonalAndVerifyOtp(this.personalInfo);

        this.businessEditPage.isAt();
        this.businessEditPage
                .submitEditBusinessInfo(this.businessInfo);


    }
}

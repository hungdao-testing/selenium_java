package com.aspire.loan.specs.useraccount;



import com.aspire.loan.datagenerator.RegistrationGenerator;
import com.aspire.loan.datagenerator.builder.BusinessInfoBuilder;
import com.aspire.loan.models.uidata.BusinessModel;
import com.aspire.loan.models.uidata.PersonalModel;
import com.aspire.loan.models.uidata.configtype.BusinessRoleType;
import com.aspire.loan.specs.BaseTestNG;
import com.aspire.loan.ui.common.authentication.ApiRegistration;
import com.aspire.loan.ui.common.authentication.LoginWF;
import com.aspire.loan.ui.common.authentication.RegistrationInfo;
import com.aspire.loan.ui.pages.BusinessEditPage;
import com.aspire.loan.ui.pages.PersonEditPage;
import com.aspire.loan.ui.pages.businessregistration.BusinessRegistrationMethodPage;
import com.aspire.loan.ui.pages.businessrole.RoleSelectorPage;
import org.testng.annotations.*;


public class BusinessRoleTest extends BaseTestNG {

    private LoginWF loginWF;
    private PersonalModel personalInfo;
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
        RegistrationInfo account = RegistrationGenerator.generateValidRegistrationData();
        new ApiRegistration().create(account);
        this.personalInfo = account.getPersonalInfo();
        this.loginWF.loginWith(this.personalInfo.getEmail());
    }

    @Test(dataProvider = "standardCorporateBusiness")
    public void select_director_role_test(BusinessModel businessInfo){
        this.roleSelectorPage.isAt();
        this.roleSelectorPage.selectRoleAndProcess(
                businessInfo.getRoleName(),
                businessInfo.getAdditionalRoleDetail());

        this.businessRegistrationPage.isAt();
        this.businessRegistrationPage
                .selectMethod(businessInfo.getRegistrationMethodType());

        this.personEditPage.isAt();
        this.personEditPage
                .submitPersonalAndVerifyOtp(this.personalInfo);

        this.businessEditPage.isAt();
        this.businessEditPage
                .submitEditBusinessInfo(businessInfo);

    }

    @DataProvider
    public Object[] standardCorporateBusiness(){
        return new Object[]{
                new BusinessInfoBuilder().generateStandardForBusinessRole(BusinessRoleType.DIRECTOR),
        };
    }
}

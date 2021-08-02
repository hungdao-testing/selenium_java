package com.aspire.loan.tests;

import com.aspire.loan.dataschema.DataSchema;
import com.aspire.loan.pages.authentication.RegisteredCompletionPage;
import com.aspire.loan.pages.authentication.UserAccountCreationPage;
import com.aspire.loan.pages.businessrole.BusinessRoleSelectionFactory;
import com.aspire.loan.pages.onboarding.*;
import com.aspire.loan.datamanagement.DataManagement;
import com.aspire.loan.utilfunctions.OtpHandlePage;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

public class OnboardingTest extends BaseTest {

    public UserAccountCreationPage userAccountCreationPage;
    public OtpHandlePage mobileOtpHandlePage;
    public DataSchema testData = DataManagement.getData();

    @BeforeTest
    public void setUp() {
        this.userAccountCreationPage = new UserAccountCreationPage(driver);
    }

    @Test
    public void verify_user_could_register_a_new_account_and_onboarding_successfully() {

        this.userAccountCreationPage
                .goTo()
                .isAt()
                .inputFullName(testData.getRegistration().getFullName())
                .inputEmail(testData.getRegistration().getEmail())
                .inputPhoneNumber(testData.getRegistration().getCountryPhoneCode(),
                        testData.getRegistration().getPhoneNumber())
                .inputAboutUs(testData.getRegistration().getHearAboutUs())
                .checkPrivacyBox()
                .clickSubmitBtn();

        this.mobileOtpHandlePage = new OtpHandlePage(driver, Integer.parseInt(testData.getRegistration().getPhoneNumber()));
        this.mobileOtpHandlePage.isAtVerifyPhoneOtp().inputOtp("1234");


        RegisteredCompletionPage registeredCompletionPage = new RegisteredCompletionPage(driver);
        registeredCompletionPage.isAt().clickContinueButton();

        BusinessRoleSelectionFactory
                .getRoleType(testData.getBusinessRole().getRole(), driver)
                .processBusinessRoleStep(testData.getBusinessRole().getAdditionalDetail());

        BusinessRegistrationMethodPage businessRegistrationMethodPage = new BusinessRegistrationMethodPage(driver);
        businessRegistrationMethodPage
                .isAt()
                .selectBusinessRegistrationMethod("standard");

        PersonEditPage personEditPage = new PersonEditPage(driver);
        String[] DOB = testData.getPersonalDetail().getDOB().split("/");
        personEditPage
                .isAt()
                .setEmail(testData.getPersonalDetail().getEmail())
                .setDateOfBirth(Integer.parseInt(DOB[0]), Integer.parseInt(DOB[1]), Integer.parseInt(DOB[2]))
                .setNationality("Singapore")
                .setGender("Male")
                .clickSubmit();

        OtpHandlePage otpEmailHandlePage = new OtpHandlePage(driver, testData.getRegistration().getEmail());
        otpEmailHandlePage.isAtVerifyEmailOtp().inputOtp("1234");

        BusinessEditPage businessEditPage = new BusinessEditPage(driver);
        businessEditPage
                .isAt()
                .setBusinessName(testData.getBusinessDetail().getLegalName())
                .setEntityCategory(testData.getBusinessDetail().getEntity())
                .setBusinessEntityType(testData.getBusinessDetail().getEntityType())
                .setBusinessRegistrationNumber(testData.getBusinessDetail().getBusinessRegistrationNumber())
                .setIndustry(testData.getBusinessDetail().getIndustry())
                .setSubIndustry(testData.getBusinessDetail().getSubIndustry())
                .clickContinueButton();

        BusinessDetailEditPage businessDetailEditPage = new BusinessDetailEditPage(driver);
        businessDetailEditPage
                .isAt()
                .setBusinessActivity(testData.getBusinessDetailEdit().getActivity())
                .setBusinessDetailActivity(testData.getBusinessDetailEdit().getDetailActivity())
                .setLiveWebSite(testData.getBusinessDetailEdit().getLiveWebsite())
                .setNumberOfEmployees(testData.getBusinessDetailEdit().getNumberOfPeople())
                .setAnnualTurnOverField(testData.getBusinessDetailEdit().getAnnualTurnOver())
                .clickSubmit();

        BusinessRelationShipPage businessRelationShipPage = new BusinessRelationShipPage(driver);
        businessRelationShipPage
                .isAt()
                .answerForQuestionOne("No")
                .answerForQuestionTwo("No")
                .clickOnContinueButton();

        IdentityDetailPage identityDetailPage = new IdentityDetailPage(driver);
        identityDetailPage
                .isAt()
                .clickGetStartedButton();

    }


}

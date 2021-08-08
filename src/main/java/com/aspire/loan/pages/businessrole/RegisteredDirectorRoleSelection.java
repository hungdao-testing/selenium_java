package com.aspire.loan.pages.businessrole;

import com.aspire.loan.components.SideBar;
import com.aspire.loan.dataschema.AdditionalDetail;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;


public class RegisteredDirectorRoleSelection extends IBusinessRoleTemplate {

    @FindBy(xpath = "//div[contains(text(), 'I am a registered director of the company')]/parent::div")
    private WebElement registerDirectorSelection;

    @FindBy(xpath = "//div[contains(text(), 'I am a non-registered director or an employee')]/parent::div")
    private WebElement nonRegisteredDirectorSelection;

    private AdditionalDetailPage additionalDetailPage;
    private SideBar sideBar;

    public RegisteredDirectorRoleSelection(WebDriver driver) {
        super(driver);
        PageFactory.initElements(driver, this);
        this.additionalDetailPage = PageFactory.initElements(driver, AdditionalDetailPage.class);
        this.sideBar = PageFactory.initElements(driver, SideBar.class);
    }

    public void isAt(){
        this.wait.until(d -> registerDirectorSelection.isDisplayed() && nonRegisteredDirectorSelection.isDisplayed());
        this.wait.until(d -> this.sideBar.getTitleComp().getText().equalsIgnoreCase("Tell us more about yourself"));
//        this.wait.until(d -> noActiveAjaxRequest());
    }

    @Override
    public void selectRole() {
        this.wait.until(d -> registerDirectorSelection.isDisplayed());
        js.executeScript("arguments[0].click()", registerDirectorSelection);
    }

    @Override
    public void processDetail(AdditionalDetail additionalDetailData) {

    }

//    @Override
//    public void processDetail(AdditionalDetail additionalDetailData) {
//        this.additionalDetailPage
//                .isAt()
//                .setCountry(additionalDetailData.getCountry())
//                .setSolution(additionalDetailData.getSolutions())
//                .clickContinue();
//    }
}

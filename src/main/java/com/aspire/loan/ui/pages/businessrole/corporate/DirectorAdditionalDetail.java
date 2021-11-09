package com.aspire.loan.ui.pages.businessrole.corporate;

import com.aspire.loan.helpers.web_element.IDropdown;
import com.aspire.loan.models.uidata.AdditionalRoleDetail;
import com.aspire.loan.ui.BasePage;
import com.aspire.loan.ui.components.SideBar;
import com.aspire.loan.ui.pages.businessrole.BusinessRoleProcessor;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;

import java.util.List;

public class DirectorAdditionalDetail extends BasePage implements BusinessRoleProcessor, IDropdown {

    @FindBy(css = "div[url='countries/all'] label")
    private WebElement countryDropdown;

    @FindBy(css = "div[url='options']")
    private WebElement solutions;

    @FindBy(css = "[label='What solutions are you looking for?'] div:nth-child(3) .chip-items__item-inner")
    private List<WebElement> selectedSolutions;

    @FindBy(xpath = "//button[.='Continue']")
    private WebElement continueBtn;


    private SideBar sideBar;

    public DirectorAdditionalDetail(WebDriver driver) {
        super(driver);
        PageFactory.initElements(driver, this);
        this.sideBar = PageFactory.initElements(driver, SideBar.class);
    }

    @Override
    public void isAt() {
        super.isAt();
        LOGGER.info("Waiting for Additional Detail page of Director role is loaded");
        this.wait.until(d -> this.sideBar.getTitleComp().isDisplayed() && this.countryDropdown.isDisplayed());
    }

    private void selectCountry(String country){
        LOGGER.info("Select country '{}'", country);
        this.countryDropdown.click();
        scrollDropdownAndSelectValue(driver, wait, country);
    }

    private void selectSolutions(List<String> solutionOptions){
        LOGGER.info("Select Solutions: '{}'", solutionOptions);
        this.solutions.click();

        solutionOptions.stream().forEach(e -> scrollDropdownAndSelectValue(driver, wait, e));
        this.solutions.click();
        this.wait.until(d -> selectedSolutions.size() == solutionOptions.size());

        String text = this.solutions.findElement(By.cssSelector("div:nth-child(1)")).getText();
        Assert.assertEquals(text, String.format("%s selected", solutionOptions.size()));
    }

    private void clickContinueButton(){
        LOGGER.info("Click on Continue Button");
        this.wait.until(d -> continueBtn.isDisplayed());
        continueBtn.click();
    }


    @Override
    public void process(AdditionalRoleDetail additionalData) {
        isAt();
        selectCountry(additionalData.getCountry());
        selectSolutions(additionalData.getSolutions());
        clickContinueButton();
    }
}

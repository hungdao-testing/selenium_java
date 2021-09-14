package com.aspire.loan.ui.pages.businessrole.corporate;

import com.aspire.loan.elementhelper.IDropdown;
import com.aspire.loan.ui.BasePage;
import com.aspire.loan.ui.components.SideBar;
import com.aspire.loan.ui.pages.businessrole.AbstractAdditionalData;
import com.aspire.loan.ui.pages.businessrole.AbstractAdditionalDetail;
import com.aspire.loan.ui.pages.businessrole.BusinessRoleProcessor;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

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

    private void selectSolutions(String solutionOptions){
        LOGGER.info("Select Solutions: '{}'", solutionOptions);
        String[] options = solutionOptions.split(",");

        this.solutions.click();
        Arrays.stream(options).forEach(e -> {
            scrollDropdownAndSelectValue(driver, wait, e);
        });
        this.solutions.click();
        this.wait.until(d -> selectedSolutions.size() == options.length);

        String text = this.solutions.findElement(By.cssSelector("div:nth-child(1)")).getText();
        Assert.assertEquals(text, String.format("%s selected", options.length));
    }

    private void clickContinueButton(){
        this.wait.until(d -> continueBtn.isDisplayed());
        continueBtn.click();
    }

    @Override
    public void process(Map<String, String> additionalDetails) {
        isAt();
        selectCountry(additionalDetails.get("country"));
        selectSolutions(additionalDetails.get("solutions"));
        clickContinueButton();
    }
}

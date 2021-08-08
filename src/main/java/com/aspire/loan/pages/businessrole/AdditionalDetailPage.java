package com.aspire.loan.pages.businessrole;

import com.aspire.loan.components.SideBar;
import com.aspire.loan.controlhelper.IDropdown;
import com.aspire.loan.core.AbstractBasePage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;

import java.util.List;


public class AdditionalDetailPage extends AbstractBasePage implements IDropdown {

    @FindBy(css = "[label='Where is your company registered?']:nth-child(2)")
    private WebElement countryDropDown;

    @FindBy(css = "[label='What solutions are you looking for?'] label")
    private WebElement solutionDropDown;

    @FindBy(css = "button[role='button']")
    private WebElement continueButton;

    private SideBar sideBar;

    @Override
    public void isAt() {
        super.isAt();
    }

    public AdditionalDetailPage(WebDriver driver) {
        super(driver);
        PageFactory.initElements(driver, this);
        this.sideBar = PageFactory.initElements(driver, SideBar.class);
    }

    public AdditionalDetailPage setCountry(String countryValue) {
        this.wait.until(d -> countryDropDown.isDisplayed());
        if (!this.countryDropDown.getText().equalsIgnoreCase(countryValue)) {
            this.wait.until(ExpectedConditions.elementToBeClickable(countryDropDown));
            countryDropDown.click();
            scrollAndSelectOption(driver, wait, countryValue);
            this.wait.until(ExpectedConditions.textToBePresentInElement(countryDropDown, countryValue));
        }
        return this;
    }

    public AdditionalDetailPage setSolution(List<String> options) {
        this.wait.until(ExpectedConditions.elementToBeClickable(solutionDropDown));
        solutionDropDown.click();
        options.forEach(option -> {
            wait.until(ExpectedConditions.refreshed(ExpectedConditions.visibilityOf(solutionDropDown)));
            scrollAndSelectOption(driver, wait, option);
        });
        new Actions(driver).moveToElement(this.solutionDropDown).click().build().perform();
        this.wait.until(ExpectedConditions.textToBePresentInElement(solutionDropDown, options.size() + " selected"));
        return this;
    }

    public void clickContinue() {
        this.wait.until(d -> this.continueButton.isDisplayed());
        this.continueButton.click();
    }


}

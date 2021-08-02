package com.aspire.loan.pages.onboarding;

import com.aspire.loan.components.SideBar;
import com.aspire.loan.core.AbstractBasePage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class IdentityDetailPage extends AbstractBasePage {

    @FindBy(css = ".auth-form__card")
    private WebElement documentCardOptions;

    @FindBy(css = ".auth-form__buttons button")
    private WebElement getStartedButton;

    private SideBar sideBar;

    public IdentityDetailPage(WebDriver driver) {
        super(driver);
        PageFactory.initElements(driver, this);
        this.sideBar = PageFactory.initElements(driver, SideBar.class);
    }

    public IdentityDetailPage goTo(){
        driver.get(getBaseUrl() + "/onboarding/identify-view");
        return this;
    }

    public IdentityDetailPage isAt(){
        this.wait.until(noActiveAjaxRequest());
        this.wait.until(ExpectedConditions.textToBePresentInElement(sideBar.getTitleComp(), "Information needed"));
        this.wait.until(d -> documentCardOptions.isDisplayed());
        return this;
    }

    public void clickGetStartedButton(){
        this.wait.until(d -> getStartedButton.isDisplayed());
        this.getStartedButton.click();
    }
}

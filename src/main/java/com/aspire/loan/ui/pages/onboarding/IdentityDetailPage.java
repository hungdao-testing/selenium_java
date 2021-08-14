package com.aspire.loan.ui.pages.onboarding;

import com.aspire.loan.ui.components.SideBar;
import com.aspire.loan.config.AppConfig;
import com.aspire.loan.ui.AbstractBasePage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

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
        driver.get(AppConfig.getBaseUrl() + "/onboarding/identify-view");
        return this;
    }

    @Override
    public void isAt() {
        super.isAt();
    }

    public void clickGetStartedButton(){
        this.wait.until(d -> getStartedButton.isDisplayed());
        this.getStartedButton.click();
    }
}

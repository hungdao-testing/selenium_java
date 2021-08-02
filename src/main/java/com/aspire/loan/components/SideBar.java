package com.aspire.loan.components;

import com.aspire.loan.core.AbstractBasePage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class SideBar {

    @FindBy(css = "aside div .text-h4")
    protected WebElement titleComp;

    private WebDriver driver;

    public SideBar(WebDriver driver) {
        this.driver = driver;
    }

    public WebElement getTitleComp() {
        return titleComp;
    }
}

package com.aspire.loan.pages.onboarding.kyc;

import com.aspire.loan.core.AbstractBasePage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class PassportFido extends AbstractBasePage implements IdentityFidoStrategy{

    @FindBy(css = "[data-onfido-qa='passport']")
    private WebElement passportOption;

    public PassportFido(WebDriver driver) {
        super(driver);

    }


    @Override
    public void selectMethod(String method) {

    }

    @Override
    public void doUploadImages(String imagePath) {

    }

    @Override
    public void takeSelfie(String photoPath) {

    }
}

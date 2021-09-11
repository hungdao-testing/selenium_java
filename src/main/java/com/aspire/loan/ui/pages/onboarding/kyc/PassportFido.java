package com.aspire.loan.ui.pages.onboarding.kyc;

import com.aspire.loan.ui.BasePage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class PassportFido extends BasePage implements IdentityFidoStrategy{

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

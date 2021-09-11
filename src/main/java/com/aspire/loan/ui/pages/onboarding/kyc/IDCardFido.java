package com.aspire.loan.ui.pages.onboarding.kyc;

import com.aspire.loan.ui.BasePage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class IDCardFido extends BasePage implements IdentityFidoStrategy{

    @FindBy(css = "[data-onfido-qa='national_identity_card']")
    private WebElement nationalIDCardOption;

    public IDCardFido(WebDriver driver) {
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

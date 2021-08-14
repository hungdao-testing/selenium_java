package com.aspire.loan.ui.pages.onboarding.kyc;

import com.aspire.loan.ui.AbstractBasePage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class IDCardFido extends AbstractBasePage implements IdentityFidoStrategy{

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

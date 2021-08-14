package com.aspire.loan.ui.pages.onboarding.kyc;

public interface IdentityFidoStrategy {

    void selectMethod(String method);
    void doUploadImages(String imagePath);
    void takeSelfie(String photoPath);
}

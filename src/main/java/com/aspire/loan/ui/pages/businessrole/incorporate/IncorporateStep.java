package com.aspire.loan.ui.pages.businessrole.incorporate;

public enum IncorporateStep {
    DIRECTOR_DETAIL(1, "Director details"),
    SELECT_PACKAGE(2, "Select a package"),
    ADDITIONAL_DETAIL(3, "Additional details");

    private int stepNum;
    private String stepDesc;

    IncorporateStep(int stepNum, String stepDesc){
        this.stepNum = stepNum;
        this.stepDesc = stepDesc;
    }

    public int getStepNum(){
        return stepNum;
    }

    public String getStepDesc(){
        return stepDesc;
    }
}

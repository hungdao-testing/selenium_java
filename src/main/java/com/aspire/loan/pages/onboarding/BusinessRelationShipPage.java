package com.aspire.loan.pages.onboarding;

import com.aspire.loan.config.AppConfig;
import com.aspire.loan.core.AbstractBasePage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import java.util.List;


public class BusinessRelationShipPage extends AbstractBasePage {

    @FindBy(css = ".auth-form__card > div:nth-of-type(1)")
    private WebElement questionOneDescription;

    @FindBy(css = ".auth-form__card > div:nth-of-type(2) div[role='radio']")
    private List<WebElement> radioButtonsOfQuestionOne;

    @FindBy(css = ".auth-form__card > div:nth-of-type(3)")
    private WebElement questionTwoDescription;

    @FindBy(css = ".auth-form__card > div:nth-of-type(4) div[role='radio']")
    private List<WebElement> radioButtonsOfQuestionTwo;

    @FindBy(css = "button[type='button']")
    private WebElement continueButton;

    public BusinessRelationShipPage(WebDriver driver) {
        super(driver);
        PageFactory.initElements(driver, this);
    }

    public BusinessRelationShipPage goTo() {
        driver.get(AppConfig.getBaseUrl() + "/onboarding/business-relationship");
        return this;
    }

    @Override
    public void isAt() {
        super.isAt();
    }

    public BusinessRelationShipPage answerForQuestionOne(String answerOption) {
        for (WebElement radio : radioButtonsOfQuestionOne) {
            if (radio.getText().equalsIgnoreCase(answerOption)) {
                if (!radio.isSelected()) {
                    radio.click();
                    break;
                }
            }
        }
        return this;
    }

    public BusinessRelationShipPage answerForQuestionTwo(String answerOption) {
        for (WebElement radio : radioButtonsOfQuestionTwo) {
            if (radio.getText().equalsIgnoreCase(answerOption)) {
                if (!radio.isSelected()) {
                    radio.click();
                    break;
                }
            }
        }
        return this;
    }

    public void clickOnContinueButton(){
        this.wait.until(d -> continueButton.isDisplayed());
        continueButton.click();
    }


}

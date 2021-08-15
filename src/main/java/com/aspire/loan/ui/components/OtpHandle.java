package com.aspire.loan.ui.components;

import com.aspire.loan.ui.AbstractBasePage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;

import java.util.List;

public class OtpHandle extends AbstractBasePage {

    @FindBy(xpath = "//input[@data-cy='digit-input']/following-sibling::div/div")
    private List<WebElement> otpFields;

    @FindBy(css = "[data-cy='verify-otp-request-new']")
    private WebElement resendOtpButton;

    @FindBy(xpath = "//form/div[1]/div")
    private WebElement recipientTxt;

    private String identity;
    private SideBar sideBar;

    public OtpHandle(WebDriver driver, String identity) {
        super(driver);
        this.identity = identity;
        PageFactory.initElements(driver, this);
        this.sideBar = PageFactory.initElements(driver, SideBar.class);
    }

    private String getRecipientText(){
        if(recipientTxt.getText().contains("@")){
            return recipientTxt.getText();
        }
        return recipientTxt.getText().replaceAll(" ", "");
    }

    public OtpHandle waitForOtpScreenLoaded(){
        super.isAt();
        this.wait.until(ExpectedConditions.visibilityOfAllElements(otpFields));
        this.wait.until(d -> {
            if(recipientTxt.getText().contains("@")){
                LOGGER.info("The Email OTP component is loaded");
                return this.sideBar.getTitleComp().getText().contains("Enter email OTP");
            }else{
                LOGGER.info("The Mobile OTP component is loaded");
                return this.sideBar.getTitleComp().getText().contains("Enter phone OTP");
            }
        });
        return this;
    }


    public void inputOtp(String Otp){
        LOGGER.info("Inputting otp values: {}", Otp);
        for(int i = 0; i < otpFields.size(); i++){
            this.wait.until(ExpectedConditions.elementToBeClickable(otpFields.get(i)));
            new Actions(driver)
                    .moveToElement(otpFields.get(i))
                    .sendKeys(""+Otp.toCharArray()[i])
                    .build().perform();
            this.wait.until(ExpectedConditions.textToBePresentInElement(otpFields.get(i), ""+Otp.toCharArray()[i]));
        }
    }
}

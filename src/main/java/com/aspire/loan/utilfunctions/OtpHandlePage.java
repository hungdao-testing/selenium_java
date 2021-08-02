package com.aspire.loan.utilfunctions;

import com.aspire.loan.components.SideBar;
import com.aspire.loan.core.AbstractBasePage;
import com.google.common.util.concurrent.Uninterruptibles;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;

import java.util.List;
import java.util.concurrent.TimeUnit;

public class OtpHandlePage extends AbstractBasePage {

    @FindBy(xpath = "//input[@data-cy='digit-input']/following-sibling::div/div")
    private List<WebElement> otpFields;

    @FindBy(css = "[data-cy='verify-otp-request-new']")
    private WebElement resendOtpButton;

    @FindBy(xpath = "//form/div[1]/div")
    private WebElement recipientTxt;

    private int phoneNumber;
    private String email;
    private SideBar sideBar;

    public OtpHandlePage(WebDriver driver, int phoneNumber) {
        super(driver);
        this.phoneNumber = phoneNumber;
        PageFactory.initElements(driver, this);
        this.sideBar = PageFactory.initElements(driver, SideBar.class);
    }

    public OtpHandlePage(WebDriver driver, String email) {
        super(driver);
        this.email = email;
        PageFactory.initElements(driver, this);
        this.sideBar = PageFactory.initElements(driver, SideBar.class);
    }

    private String getRecipientText(){
        if(recipientTxt.getText().contains("@")){
            return recipientTxt.getText();
        }
        String newT = recipientTxt.getText().replaceAll(" ", "");
        return recipientTxt.getText().replaceAll(" ", "");
    }

    public OtpHandlePage isAtVerifyPhoneOtp(){
        this.wait.until(d -> noActiveAjaxRequest());
        this.wait.until(ExpectedConditions.visibilityOfAllElements(otpFields));
        this.wait.until(d ->
                this.getRecipientText().contains(String.valueOf(phoneNumber))
                        && this.resendOtpButton.isDisplayed()
                        && this.sideBar.getTitleComp().getText().contains("Enter phone OTP")
        );
        return this;
    }

    public OtpHandlePage isAtVerifyEmailOtp(){
        this.wait.until(d -> noActiveAjaxRequest());
        this.wait.until(ExpectedConditions.urlContains("email"));
        this.wait.until(ExpectedConditions.visibilityOfAllElements(otpFields));
        this.wait.until(d ->
                this.getRecipientText().contains(email)
                        && this.resendOtpButton.isDisplayed()
                        && this.sideBar.getTitleComp().getText().contains("Enter email OTP")
        );
        return this;
    }

    public void inputOtp(String Otp){
        if(Otp.length() != otpFields.size()) return;
        else{
            for(int i = 0; i < otpFields.size(); i++){
                this.wait.until(ExpectedConditions.visibilityOf(otpFields.get(i)));
                new Actions(driver)
                        .moveToElement(otpFields.get(i))
                        .sendKeys(""+Otp.toCharArray()[i])
                        .build().perform();
                this.wait.until(ExpectedConditions.textToBePresentInElement(otpFields.get(i), ""+Otp.toCharArray()[i]));
                Uninterruptibles.sleepUninterruptibly(250, TimeUnit.MILLISECONDS);
                noActiveAjaxRequest();
            }

        }
    }
}

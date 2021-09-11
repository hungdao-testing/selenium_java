package com.aspire.loan.ui.pages.businessrole.incorporate;

import com.aspire.loan.elementhelper.IDropdown;
import com.aspire.loan.model.uidata.configtype.IncorporatePackageType;
import com.aspire.loan.ui.BasePage;
import com.aspire.loan.ui.components.SideBar;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Supplier;

public class IncorporateYourCompanyPage extends BasePage implements IDropdown {

    @FindBy(css = ".auth-form__card > div[url='countries/all']")
    private WebElement countryField;

    @FindBy(css = ".auth-form__buttons button")
    private WebElement continueBtn;

    @FindBy(xpath = "//div[@class='q-stepper__content']/div")
    private List<WebElement> stepsInSideBar;

    @FindBy(css = "input[data-cy='incorporated-step-business-details-business-name']")
    private WebElement desiredBusinessName;

    @FindBy(css = "input[data-cy='incorporated-step-business-details-website-url']")
    private WebElement liveBusinessWebsite;

    @FindBy(css = "input[data-cy='incorporated-business-name']")
    private WebElement numberOfShareholders;

    @FindBy(css = "input[data-cy='incorporated-financial-year-end-date']")
    private WebElement financialYearEndDate;

    @FindBy(css = "div[data-cy='incorporated-step-business-details-term']")
    private WebElement termCheckbox;

    private SideBar sideBar;
    private IncorporateStep step;

    private static Supplier<IncorporateStep> stepOne = () -> IncorporateStep.DIRECTOR_DETAIL;
    private static Supplier<IncorporateStep> stepTwo = () -> IncorporateStep.SELECT_PACKAGE;
    private static Supplier<IncorporateStep> stepThree = () -> IncorporateStep.ADDITIONAL_DETAIL;
    private static Map<String, Supplier<IncorporateStep>> STEP = new HashMap<>();
    static {
        STEP.put("select country", stepOne);
        STEP.put("select package", stepTwo);
        STEP.put("fill additional details", stepThree);
    }

    @Override
    public void isAt() {
        super.isAt();
        this.wait.until(ExpectedConditions.textToBePresentInElement(sideBar.getTitleComp(), "Incorporate your company"));
        this.wait.until(d -> !this.stepsInSideBar.isEmpty());
    }

    public IncorporateYourCompanyPage(WebDriver driver) {
        super(driver);
        PageFactory.initElements(driver, this);
        this.sideBar = PageFactory.initElements(driver, SideBar.class);
    }

    private void waitForOptionActionOnSideBar(String text){
        LOGGER.info("Wait for the text {} is visible and active in side-bar", text);
        WebElement activeEl =  stepsInSideBar
                .stream()
                .filter(element ->
                        element.findElement(By.tagName("div")).getAttribute("class").contains("q-stepper__tab--active")
                )
                .map(e -> e.findElement(By.tagName("div")))
                .findFirst().get();
        this.wait.until(ExpectedConditions.textToBePresentInElement(activeEl, text));

    }

    private void waitForIconIsMarkedDone( int stepOrder, String text){
        LOGGER.info("Wait for the icon of {} in side bar is mark done", text);
        WebElement markedDoneEl = stepsInSideBar
                .stream()
                .filter(element ->
                        element.findElement(By.tagName("div")).getAttribute("class").contains("q-stepper__tab--done")
                )
                .skip(stepOrder - 1)
                .findFirst()
                .get();

        this.wait.until(ExpectedConditions.textToBePresentInElement(markedDoneEl, text));

    }

    private void selectCountry(String country){
        this.step = STEP.get("select country").get();
        LOGGER.info("Wait for url contains {}", "/incorporated/incorporate-your-company");
        this.wait.until(ExpectedConditions.urlContains("/incorporated/incorporate-your-company"));
        this.wait.until(ExpectedConditions.elementToBeClickable(countryField));

        waitForOptionActionOnSideBar(STEP.get("select country").get().getStepDesc());
        countryField.click();
        searchAndSelectOptionInDropDownList(driver, wait, countryField.findElement(By.tagName("input")), country);
        this.wait.until(d -> continueBtn.isDisplayed());
        continueBtn.click();
        this.waitForPageIsReady();
        waitForIconIsMarkedDone(
                STEP.get("select country").get().getStepNum(),
                STEP.get("select country").get().getStepDesc());
    }

    private void selectPackage(IncorporatePackageType packageName){
        this.step = STEP.get("select package").get();
        LOGGER.info("Wait for url contains {}", "/incorporated/select-package");
        this.wait.until(ExpectedConditions.urlContains("/incorporated/select-package"));

        waitForOptionActionOnSideBar(STEP.get("select package").get().getStepDesc());

        String locator = String.format("//div[normalize-space()='%s']", packageName.getPackageName());
        WebElement packageEl = driver.findElement(By.xpath(locator));
        packageEl.click();
        this.waitForPageIsReady();
        waitForIconIsMarkedDone(
                STEP.get("select package").get().getStepNum(),
                STEP.get("select package").get().getStepDesc());
    }

    private void fillAdditionalDetailForm(String businessName, String website, String numberOfShareholders, String financialYearEndDate){
        this.step = STEP.get("fill additional details").get();
        LOGGER.info("Wait for url contains {}", "/incorporated/business-details");
        this.wait.until(ExpectedConditions.urlContains("/incorporated/business-details"));

        waitForOptionActionOnSideBar(this.step.getStepDesc());
        inputTextToVisibleField(this.desiredBusinessName, businessName);
        inputTextToVisibleField(this.liveBusinessWebsite, website);
        inputTextToVisibleField(this.numberOfShareholders, numberOfShareholders);
        inputTextToVisibleField(this.financialYearEndDate, financialYearEndDate);
        termCheckbox.click();
        clickOnVisibleElement(continueBtn);
    }

    protected void configureIncorporate(Map<String,String> businessDetails){
        LOGGER.info("Configuring Incorporation Information: {}", businessDetails.toString());
        selectCountry(businessDetails.get("country"));
        selectPackage(IncorporatePackageType.valueOf(businessDetails.get("package")));
        fillAdditionalDetailForm(
                businessDetails.get("businessName"),
                businessDetails.get("website"),
                businessDetails.get("numberOfShareholders"),
                businessDetails.get("financialYearEndDate")
        );
    }
}

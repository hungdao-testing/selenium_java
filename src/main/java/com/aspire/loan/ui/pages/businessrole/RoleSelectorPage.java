package com.aspire.loan.ui.pages.businessrole;

import com.aspire.loan.config.AppConfig;
import com.aspire.loan.model.uidata.AdditionalRoleDetailInfo;
import com.aspire.loan.model.uidata.configtype.BusinessRoleType;
import com.aspire.loan.ui.BasePage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Consumer;

public class RoleSelectorPage extends BasePage {

    @FindBy(xpath = "//div[contains(text(), 'I want to open an Aspire account')]/following-sibling::div")
    private List<WebElement> aspireAccountRoles;

    @FindBy(xpath = "//div[contains(text(), 'I want to incorporate my company')]/following-sibling::div")
    private List<WebElement> incorporateCompanyRoles;

    private BusinessRoleProcessor businessSelector;

    public RoleSelectorPage(WebDriver driver) {
        super(driver);
        PageFactory.initElements(driver, this);
    }

    @Override
    public void isAt() {
        super.isAt();
        LOGGER.info("Wait for Business Role Selector page is loaded");
        this.wait.until(d -> aspireAccountRoles.size() > 0 && this.incorporateCompanyRoles.size() > 0);
    }

    public void goTo(){
        LOGGER.info("Open Business Role Selector Page");
        driver.get(AppConfig.getBaseUrl() + "/business-role/role-selector");
    }

    private void openAspireAccountCard(BusinessRoleType role){
        aspireAccountRoles
                .stream()
                .map(e -> e.findElement(By.xpath(String.format("//div[contains(text(), '%s')]", role.getRoleDesc()))))
                .findFirst()
                .get()
                .click();
    }

    private void openIncorporateAccountCard(BusinessRoleType role){
        incorporateCompanyRoles
                .stream()
                .map(e -> e.findElement(By.xpath(String.format("//div[contains(text(), '%s')]", role.getRoleDesc()))))
                .findFirst()
                .get()
                .click();
    }

    private Map<BusinessRoleType, Consumer<BusinessRoleType>> selectRoleCard(){
        Map<BusinessRoleType, Consumer<BusinessRoleType>> MAP = new HashMap<>();
        MAP.put(BusinessRoleType.DIRECTOR, (role) ->  openAspireAccountCard(BusinessRoleType.DIRECTOR));
        MAP.put(BusinessRoleType.EMPLOYEE, (role) ->  openAspireAccountCard(BusinessRoleType.EMPLOYEE));
        MAP.put(BusinessRoleType.FREELANCER, (role) ->  openAspireAccountCard(BusinessRoleType.FREELANCER));
        MAP.put(BusinessRoleType.ENTREPRENEUR, (role) ->  openIncorporateAccountCard(BusinessRoleType.ENTREPRENEUR));
        return MAP;
    }


    public void selectRoleAndProcess(BusinessRoleType role, AdditionalRoleDetailInfo additionalDetails){
        LOGGER.info("Attempt to click on role_card: '{}'", role);
        selectRoleCard().get(role).accept(role);
        BusinessRoleFactory.loadAdditionalPage(role, driver).process(additionalDetails);
    }

}

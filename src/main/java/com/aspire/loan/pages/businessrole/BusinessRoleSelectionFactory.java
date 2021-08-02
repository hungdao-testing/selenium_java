package com.aspire.loan.pages.businessrole;

import org.openqa.selenium.WebDriver;

import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

public class BusinessRoleSelectionFactory {

    private static final Function<WebDriver, IBusinessRoleTemplate> registeredDirector = RegisteredDirectorRoleSelection::new;
    private static final Map<String, Function<WebDriver, IBusinessRoleTemplate>> MAP = new HashMap<>();

    static {
        MAP.put("registeredDirectorRole", registeredDirector);

    }

    public static IBusinessRoleTemplate getRoleType(String role, WebDriver driver){
        return MAP.get(role).apply(driver);
    }
}

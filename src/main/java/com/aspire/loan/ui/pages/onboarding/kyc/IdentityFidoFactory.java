package com.aspire.loan.ui.pages.onboarding.kyc;

import org.openqa.selenium.WebDriver;

import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

public class IdentityFidoFactory {

    private static Function<WebDriver, IdentityFidoStrategy> passport = d -> new PassportFido(d);
    private static Function<WebDriver, IdentityFidoStrategy> idCard = d -> new IDCardFido(d);

    private static Map<String, Function<WebDriver, IdentityFidoStrategy>> MAP = new HashMap<>();
    static {
        MAP.put("passport", passport);
        MAP.put("idCard", idCard);
    }

    public static IdentityFidoStrategy get(String identityMethod, WebDriver driver){
        return MAP.get(identityMethod).apply(driver);
    }

}

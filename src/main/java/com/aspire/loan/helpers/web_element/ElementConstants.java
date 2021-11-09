package com.aspire.loan.helpers.web_element;

import org.openqa.selenium.By;

public class ElementConstants {
    public static final By DROPDOWN_MENU_CSS_SELECTOR = By.cssSelector(".q-menu");
    public static final By DROPDOWN_ITEM_CSS_SELECTOR = By.cssSelector(".q-item__label");

    public enum CalendarElement{
        YEAR("year"), MONTH("month"), DAY("day");

        String name;
        CalendarElement(String name){
            this.name = name;
        }

        public String getName(){
            return name;
        }
    }
}

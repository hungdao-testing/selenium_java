package com.aspire.loan.helpers.web_element;

import com.aspire.loan.config.GlobalConstants;
import com.aspire.loan.ui.utils.DateHelper;
import org.openqa.selenium.*;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;
import java.util.Locale;
import java.util.Optional;



public class Calendar {

    @FindBy(css = "div .q-date__header")
    private WebElement calendarDialog;

    @FindBy(css = ".q-date__calendar-days  > div.q-date__calendar-item.q-date__calendar-item--in")
    private List<WebElement> days;

    @FindBy(css = ".q-date__navigation > div:nth-child(1)")
    private WebElement previousMonthArrow;

    @FindBy(css = ".q-date__navigation > div:nth-child(3)")
    private WebElement nextMonthArrow;

    @FindBy(css = ".q-date__navigation > div:nth-child(4)")
    private WebElement previousYearArrow;

    @FindBy(css = ".q-date__navigation > div:nth-child(6)")
    private WebElement nextYearArrow;

    @FindBy(css = ".q-date__navigation > div:nth-child(2)")
    private WebElement monthWebEl;

    @FindBy(css = ".q-date__navigation > div:nth-child(5)")
    private WebElement yearWebEl;

    @FindBy(css = ".q-date__years-content div")
    private List<WebElement> yearDialogOptions;

    @FindBy(css = "div.col-auto:first-child")
    private WebElement prevArrowYearDialog;

    @FindBy(css = "div.col-auto:last-child")
    private WebElement nextArrowYearDialog;

    @FindBy(css = ".q-date__months-item button")
    private List<WebElement> monthOptions;

    private WebDriver driver;
    private WebDriverWait wait;

    public Calendar(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, GlobalConstants.EXPLICIT_WAIT_TIMEOUT);
    }

    private void isCalendarOpened() {

        this.wait.until(d -> calendarDialog.isDisplayed());
        this.wait.until(d -> days.size() > 1);
    }

    private void navigate(List<WebElement> webElementList, WebElement arrowDirection, String selectedValue){
        Optional<WebElement> optionalWebElement = webElementList.stream().filter(e -> e.getText().equalsIgnoreCase(selectedValue)).findFirst();
        while(!optionalWebElement.isPresent()){
            try{
                arrowDirection.click();
            }catch (StaleElementReferenceException e){
                System.out.println("Ignore Unharmful 'Stale Element' exception");
            }finally {
                this.wait.until(ExpectedConditions.visibilityOfAllElements(webElementList));
                optionalWebElement = webElementList.stream().filter(e -> e.getText().equalsIgnoreCase(selectedValue)).findFirst();
            }
        }
        optionalWebElement.get().click();
    }



    private void setYear(String selectYear) {
        yearWebEl.click();
        this.wait.until(d -> yearDialogOptions.size() > 0);

        int maxYearItem = Integer.parseInt(yearDialogOptions.get(yearDialogOptions.size() - 1).getText());
        int minYearItem = Integer.parseInt(yearDialogOptions.get(0).getText());

        if (Integer.parseInt(selectYear) > maxYearItem) {
            navigate(yearDialogOptions, nextArrowYearDialog, selectYear);
        } else if(Integer.parseInt(selectYear) < minYearItem) {
            navigate(yearDialogOptions, prevArrowYearDialog, selectYear);
        }else{
        }

    }

    private void setMonth(String selectedMonth) {
        if (monthWebEl.getText().equalsIgnoreCase(DateHelper.convertToFullMonthFormat(selectedMonth, Locale.UK)))
            return;

        String shortMonthFormat = DateHelper.convertToShortMonthFormat(selectedMonth, Locale.UK);
        String fullMonthFormat = DateHelper.convertToFullMonthFormat(selectedMonth, Locale.UK);

        monthWebEl.click();
        this.wait.until(d -> monthOptions.size() == 12);
        driver.findElements(By.cssSelector(".q-date__months-item button"))
                .stream()
                .filter(e -> e.getText().contains(shortMonthFormat))
                .findFirst()
                .get()
                .click();
        isCalendarOpened();
        this.wait.until(d -> monthWebEl.getText().equalsIgnoreCase(fullMonthFormat));
    }

    private void setDay(String selectedDay) {
        this.wait.until(d -> days.size() > 0);
        days.stream()
                .filter(e -> e.getText().equalsIgnoreCase(selectedDay))
                .peek(System.out::println)
                .findFirst()
                .get()
                .click();
    }


    public void setDateForField(WebElement dateField, String year, String month, String day){
        dateField.click();
        isCalendarOpened();
        setYear(year);
        setMonth(month);
        setDay(day);
        this.wait.until(d -> !dateField.getAttribute("value").isEmpty());
    }


}

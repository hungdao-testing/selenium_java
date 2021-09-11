package com.aspire.loan.elementhelper;

import com.aspire.loan.config.GlobalConstants;
import com.aspire.loan.ui.utils.DateHelper;
import org.openqa.selenium.By;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;
import java.util.Locale;



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

    @FindBy(css = ".q-date__months-item button")
    private List<WebElement> monthOptions;

    private WebDriver driver;
    private WebDriverWait wait;

    public Calendar(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, GlobalConstants.EXPLICIT_WAIT_TIMEOUT);
    }

    protected void isCalendarOpened() {

        this.wait.until(d -> calendarDialog.isDisplayed());
        this.wait.until(d -> days.size() > 1);
    }


    protected void navigate(WebElement element, WebElement arrowDirection, String selectYear) {
        String currentLoadedValue = element.getText();
        while (!currentLoadedValue.equalsIgnoreCase(selectYear)) {
            try {
                arrowDirection.click();
            } catch (StaleElementReferenceException e) {
                System.out.println("Ignore Unharmful 'Stale Element' exception");
            } finally {
                this.wait.until(ExpectedConditions.visibilityOfAllElements(days));
                currentLoadedValue = element.getText();
            }
        }
    }


    protected Calendar setYear(String selectYear) {
        String currentDisplayedYear = yearWebEl.getText();

        if (Integer.parseInt(currentDisplayedYear) < Integer.parseInt(selectYear)) {
            navigate(yearWebEl, nextYearArrow, selectYear);
        } else if(Integer.parseInt(currentDisplayedYear) > Integer.parseInt(selectYear)) {
            navigate(yearWebEl, previousYearArrow, selectYear);
        }else{
            return this;
        }

        return this;
    }

    protected Calendar setMonth(String selectedMonth) {
        if (monthWebEl.getText().equalsIgnoreCase(DateHelper.convertToFullMonthFormat(selectedMonth, Locale.UK))) return this;

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
        return this;
    }

    protected Calendar setDay(String selectedDay) {
        this.wait.until(ExpectedConditions.visibilityOfAllElements(days));
        days.stream()
                .filter(e -> e.getText().equalsIgnoreCase(selectedDay))
                .peek(System.out::println)
                .findFirst()
                .get()
                .click();
        return this;
    }

    public String getCalendarInputField(WebElement calendarInput){
        return calendarInput.getAttribute("value");
    }

    public void setDateOfBirth(String year, String month, String day){
        isCalendarOpened();
        setYear(year);
        setMonth(month);
        setDay(day);
    }



}

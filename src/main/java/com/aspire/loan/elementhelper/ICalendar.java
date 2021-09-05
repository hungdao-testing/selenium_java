package com.aspire.loan.elementhelper;

import com.aspire.loan.config.GlobalConstants;
import com.google.common.util.concurrent.Uninterruptibles;
import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Month;
import java.time.format.TextStyle;
import java.util.Calendar;
import java.util.List;
import java.util.Locale;
import java.util.concurrent.TimeUnit;


public interface ICalendar {

    default void setYear(WebDriver driver, int inputYear) {
        int currentYear;
        WebDriverWait wait = new WebDriverWait(driver, GlobalConstants.EXPLICIT_WAIT_TIMEOUT);
        wait.until(ExpectedConditions.visibilityOf(driver.findElement(By.cssSelector(".q-date"))));

        if (driver.findElement(By.cssSelector(".q-date__header-title")).getText().contains("—")) {
            currentYear = Calendar.getInstance().get(Calendar.YEAR);
        } else {
            currentYear = Integer.parseInt(
                    driver.findElement(By.cssSelector(".q-date__header-title")).getText().split(" ")[1]);
        }

        if (currentYear == inputYear) {
            return;
        }

        String yearXpathSelector = String.format
                ("//div[contains(@class,'q-date__navigation')]//button[contains(., '%s')]/parent::div/parent::div", currentYear);
        WebElement currentYearWebElement = driver.findElement(By.xpath(yearXpathSelector));
        WebElement leftArrow = currentYearWebElement.findElement(By.xpath(yearXpathSelector + "/preceding-sibling::div[1]"));
        WebElement rightArrow = currentYearWebElement.findElement(By.xpath(yearXpathSelector + "/following-sibling::div"));

        if (Integer.parseInt(currentYearWebElement.getText()) == inputYear) {
            return;
        }

        boolean isStillNavigate = false;
        if (Integer.parseInt(currentYearWebElement.getText()) < inputYear) {
            while (!isStillNavigate) {
                wait.until(ExpectedConditions.elementToBeClickable(rightArrow));
                rightArrow.click();
                Uninterruptibles.sleepUninterruptibly(500, TimeUnit.MILLISECONDS);

                currentYear = currentYear + 1;
                yearXpathSelector = String.format
                        ("//div[contains(@class,'q-date__navigation')]//button[contains(., '%s')]/parent::div/parent::div", currentYear);
                wait.until(ExpectedConditions.elementToBeClickable(currentYearWebElement));
                currentYearWebElement = driver.findElement(By.xpath(yearXpathSelector));
                if (Integer.parseInt(currentYearWebElement.getText()) == inputYear) {
                    isStillNavigate = true;
                }

            }
        } else {
            while (!isStillNavigate) {
                wait.until(ExpectedConditions.elementToBeClickable(leftArrow));
                leftArrow.click();
                Uninterruptibles.sleepUninterruptibly(500, TimeUnit.MILLISECONDS);

                currentYear = currentYear - 1;
                yearXpathSelector = String.format
                        ("//div[contains(@class,'q-date__navigation')]//button[contains(., '%s')]/parent::div/parent::div", currentYear);
                wait.until(ExpectedConditions.elementToBeClickable(currentYearWebElement));
                currentYearWebElement = driver.findElement(By.xpath(yearXpathSelector));
                if (Integer.parseInt(currentYearWebElement.getText()) == inputYear) {
                    isStillNavigate = true;
                }
            }
        }
    }

    default void setMonth(WebDriver driver, int inputMonth) {
        String currentMonth;
        WebDriverWait wait = new WebDriverWait(driver, GlobalConstants.EXPLICIT_WAIT_TIMEOUT);
        wait.until(ExpectedConditions.visibilityOf(driver.findElement(By.cssSelector(".q-date"))));

        if (driver.findElement(By.cssSelector(".q-date__header-title")).getText().trim().contains("—")) {
            currentMonth = Month.of(Calendar.getInstance().get(Calendar.MONTH) + 1).getDisplayName(TextStyle.FULL_STANDALONE,
                    Locale.UK);
        } else {
            currentMonth =
                    driver.findElement(By.cssSelector(".q-date__header-title")).getText().split(" ")[0];
        }


        String inputFullFormat = Month.of(inputMonth).getDisplayName(TextStyle.FULL_STANDALONE,
                Locale.UK);
        String inputShortMonthFormat = Month.of(inputMonth).getDisplayName(TextStyle.SHORT_STANDALONE,
                Locale.UK);
        String currentMonthXpathSelector = String.format
                ("//div[contains(@class,'q-date__navigation')]//button[contains(., '%s')]/parent::div/parent::div", currentMonth);

        if (currentMonth.equalsIgnoreCase(inputFullFormat)) {
            return;
        }

        WebElement currentMonthSelection = driver.findElement(By.xpath(currentMonthXpathSelector));
        wait.until(ExpectedConditions.elementToBeClickable(currentMonthSelection));
        currentMonthSelection.click();

        try {
            WebElement monthMenu = driver.findElement(By.cssSelector(".q-date"));
            List<WebElement> monthItems = monthMenu.findElements(By.cssSelector("div[class*='q-date__months-item']"));
            for (WebElement menu : monthItems) {
                wait.until(ExpectedConditions.elementToBeClickable(menu));
                if (menu.getText().equalsIgnoreCase(inputShortMonthFormat)) {
                    new Actions(driver).moveToElement(menu).click().build().perform();
                }
            }

        } catch (StaleElementReferenceException e) {
            System.out.println("Element is being detached from DOM::");
        }

    }

    default void setDate(WebDriver driver, int inputDay) {
        List<WebElement> dayItems = driver.findElements(By.cssSelector(".q-date__calendar-item button"));
        for (WebElement day : dayItems) {
            if (Integer.parseInt(day.getText()) == inputDay
                    && !day.getAttribute("class").trim().contains("q-btn--active")) {
                day.click();
                break;
            } else if (day.getAttribute("class").trim().contains("q-btn--active")
                    && Integer.parseInt(day.getText()) == inputDay) {
                driver.findElement(By.cssSelector("body")).sendKeys(Keys.ESCAPE);
                break;
            }

        }
    }

}

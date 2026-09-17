package com.store.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public abstract class BasePage {

    private static final int DEFAULT_TIMEOUT_SECONDS = 10;

    /*
     * Variables
     */
    protected WebDriver driver;
    protected WebDriverWait wait;

    /*
     * Constructor
     */

    public BasePage(WebDriver driver) {
        this.driver = driver;
        wait = new WebDriverWait(driver, Duration.ofSeconds(DEFAULT_TIMEOUT_SECONDS));
    }

    /*
     * Methods
     */
    public WebElement find(By element) {
        return driver.findElement(element);
    }

    public void click(By element) {
        wait.until(ExpectedConditions.elementToBeClickable(element)).click();
    }

    public void submit(By element) {
        find(element).submit();
    }

    public void type(By element, String text) {
        WebElement webElement = find(element);
        webElement.clear();
        webElement.sendKeys(text);
    }

    public String text(By element) {
        wait.until(ExpectedConditions.visibilityOfElementLocated(element));
        return find(element).getText();
    }

    public boolean isDisplayedElement(By locator) {
        try {
            wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
            return true;
        } catch (Exception e) {
            return false;
        }
    }
}

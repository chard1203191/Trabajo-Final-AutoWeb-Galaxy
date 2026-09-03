package com.store.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class HomePage extends BasePage {

    private By registerLink = By.className("ico-register");
    private By loginLink = By.className("ico-login");
    private By logoutLink = By.className("ico-logout");
    private By accountLink = By.className("ico-account");
    private By cartLink = By.id("topcartlink");
    private By searchInput = By.id("small-searchterms");
    private By searchButton = By.cssSelector("button.search-box-button");

    public HomePage(WebDriver driver) {
        super(driver);
    }

    public void goToRegister() {
        click(registerLink);
    }

    public void goToLogin() {
        click(loginLink);
    }

    public void goToCart() {
        click(cartLink);
    }

    public void searchProduct(String productName) {
        type(searchInput, productName);
        click(searchButton);
    }

    public void logout() {
        click(logoutLink);
    }

    public boolean isUserLoggedIn() {
        return isDisplayedElement(accountLink);
    }
}

package com.store.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class HomePage extends BasePage {

    private By registerLink = By.linkText("Create an Account");
    private By loginLink = By.linkText("Sign In");
    private By customerMenuButton = By.cssSelector(".customer-welcome button.action.switch");
    private By logoutLink = By.linkText("Sign Out");
    private By accountLink = By.cssSelector(".panel.header .logged-in");
    private By cartLink = By.cssSelector("a.action.showcart");
    private By searchInput = By.id("search");
    private By searchButton = By.cssSelector("button.action.search");

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
        if (isDisplayedElement(customerMenuButton)) {
            click(customerMenuButton);
        }
        click(logoutLink);
    }

    public boolean isUserLoggedIn() {
        return isDisplayedElement(accountLink);
    }
}

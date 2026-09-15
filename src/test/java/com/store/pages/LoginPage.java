package com.store.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class LoginPage extends BasePage {

    private By emailInput = By.id("email");
    private By passwordInput = By.id("password");
    private By loginButton = By.id("send2");
    private By loginError = By.cssSelector(".message-error div");
    private By emailValidationError = By.id("email-error");
    private By passwordRecoveryLink = By.linkText("Forgot Your Password?");

    public LoginPage(WebDriver driver) {
        super(driver);
    }

    public void typeEmail(String email) {
        type(emailInput, email);
    }

    public void typePassword(String password) {
        type(passwordInput, password);
    }

    public void submitLogin() {
        click(loginButton);
    }

    public void login(String email, String password) {
        typeEmail(email);
        typePassword(password);
        submitLogin();
    }

    public String getLoginError() {
        return text(loginError);
    }

    public String getEmailValidationError() {
        return text(emailValidationError);
    }

    public void goToPasswordRecovery() {
        click(passwordRecoveryLink);
    }
}

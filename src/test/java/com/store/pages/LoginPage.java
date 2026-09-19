package com.store.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class LoginPage extends BasePage {

    private static final String LOGIN_FORM = ".block-customer-login form#login-form";

    private By emailInput = By.cssSelector(LOGIN_FORM + " input#email[name='login[username]']");
    private By passwordInput = By.cssSelector(LOGIN_FORM + " input#password[name='login[password]']");
    private By loginButton = By.cssSelector(LOGIN_FORM + " button#send2.action.login.primary");
    private By loginError = By.cssSelector(".page.messages .message-error div");
    private By emailValidationError = By.cssSelector(LOGIN_FORM + " #email-error");
    private By passwordValidationError = By.cssSelector(LOGIN_FORM + " #password-error");
    private By passwordRecoveryLink = By.cssSelector(LOGIN_FORM + " a.action.remind");

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

    public String getPasswordValidationError() {
        return text(passwordValidationError);
    }

    public void goToPasswordRecovery() {
        click(passwordRecoveryLink);
    }
}

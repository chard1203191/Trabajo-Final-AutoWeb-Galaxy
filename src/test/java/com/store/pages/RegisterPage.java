package com.store.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class RegisterPage extends BasePage {

    private By firstNameInput = By.id("firstname");
    private By lastNameInput = By.id("lastname");
    private By emailInput = By.id("email_address");
    private By passwordInput = By.id("password");
    private By confirmPasswordInput = By.id("password-confirmation");
    private By registerButton = By.id("send2");
    private By resultMessage = By.cssSelector(".message-success div");
    private By firstNameError = By.id("firstname-error");
    private By lastNameError = By.id("lastname-error");
    private By emailError = By.id("email_address-error");
    private By passwordError = By.id("password-error");
    private By confirmPasswordError = By.id("password-confirmation-error");
    private By duplicateEmailError = By.cssSelector(".message-error div");

    public RegisterPage(WebDriver driver) {
        super(driver);
    }

    public void typeFirstName(String firstName) {
        type(firstNameInput, firstName);
    }

    public void typeLastName(String lastName) {
        type(lastNameInput, lastName);
    }

    public void typeEmail(String email) {
        type(emailInput, email);
    }

    public void typePassword(String password) {
        type(passwordInput, password);
    }

    public void typeConfirmPassword(String confirmPassword) {
        type(confirmPasswordInput, confirmPassword);
    }

    public void submitRegister() {
        click(registerButton);
    }

    public void registerUser(String firstName, String lastName, String email, String password, String confirmPassword) {
        typeFirstName(firstName);
        typeLastName(lastName);
        typeEmail(email);
        typePassword(password);
        typeConfirmPassword(confirmPassword);
        submitRegister();
    }

    public String getSuccessMessage() {
        return text(resultMessage);
    }

    public String getFirstNameError() {
        return text(firstNameError);
    }

    public String getLastNameError() {
        return text(lastNameError);
    }

    public String getEmailError() {
        return text(emailError);
    }

    public String getPasswordError() {
        return text(passwordError);
    }

    public String getConfirmPasswordError() {
        return text(confirmPasswordError);
    }

    public String getDuplicateEmailError() {
        return text(duplicateEmailError);
    }
}

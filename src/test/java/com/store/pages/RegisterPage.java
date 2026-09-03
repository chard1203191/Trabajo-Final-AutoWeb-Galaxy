package com.store.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class RegisterPage extends BasePage {

    private By firstNameInput = By.id("FirstName");
    private By lastNameInput = By.id("LastName");
    private By emailInput = By.id("Email");
    private By passwordInput = By.id("Password");
    private By confirmPasswordInput = By.id("ConfirmPassword");
    private By registerButton = By.id("register-button");
    private By resultMessage = By.className("result");
    private By firstNameError = By.id("FirstName-error");
    private By lastNameError = By.id("LastName-error");
    private By emailError = By.id("Email-error");
    private By passwordError = By.id("Password-error");
    private By confirmPasswordError = By.id("ConfirmPassword-error");
    private By duplicateEmailError = By.cssSelector(".message-error");

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

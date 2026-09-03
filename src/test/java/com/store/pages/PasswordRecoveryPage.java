package com.store.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class PasswordRecoveryPage extends BasePage {

    private By emailInput = By.id("Email");
    private By recoveryButton = By.cssSelector("button.password-recovery-button");
    private By resultMessage = By.className("result");
    private By emailError = By.id("Email-error");

    public PasswordRecoveryPage(WebDriver driver) {
        super(driver);
    }

    public void typeEmail(String email) {
        type(emailInput, email);
    }

    public void submitRecovery() {
        click(recoveryButton);
    }

    public void recoverPassword(String email) {
        typeEmail(email);
        submitRecovery();
    }

    public String getResultMessage() {
        return text(resultMessage);
    }

    public String getEmailError() {
        return text(emailError);
    }
}

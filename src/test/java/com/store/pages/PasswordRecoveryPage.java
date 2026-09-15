package com.store.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class PasswordRecoveryPage extends BasePage {

    private By emailInput = By.id("email_address");
    private By recoveryButton = By.id("send2");
    private By resultMessage = By.cssSelector(".message-success div");
    private By emailError = By.id("email_address-error");

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

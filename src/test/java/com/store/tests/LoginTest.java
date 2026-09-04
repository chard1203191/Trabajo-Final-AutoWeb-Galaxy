package com.store.tests;

import com.store.utils.BaseTest;
import org.testng.Assert;
import org.testng.annotations.Test;

public class LoginTest extends BaseTest {

    @Test(groups = {"regression", "integration"})
    public void tcLogin01InicioDeSesionExitoso() {
        String email = "login.exitoso." + System.currentTimeMillis() + "@test.com";
        String password = "Password123";

        registrarUsuario(email, password);
        homePage.logout();
        homePage.goToLogin();
        loginPage.login(email, password);

        Assert.assertTrue(homePage.isUserLoggedIn());
    }

    @Test(groups = {"functional"})
    public void tcLogin02InicioDeSesionConCredencialesIncorrectas() {
        String email = "login.incorrecto." + System.currentTimeMillis() + "@test.com";
        String password = "Password123";
        String wrongPassword = "Password456";

        registrarUsuario(email, password);
        homePage.logout();
        homePage.goToLogin();
        loginPage.login(email, wrongPassword);

        Assert.assertTrue(loginPage.getLoginError().contains("Login was unsuccessful"));
    }

    @Test(groups = {"functional"})
    public void tcLogin03CredencialesEnBlanco() {
        homePage.goToLogin();
        loginPage.submitLogin();

        Assert.assertEquals(loginPage.getEmailValidationError(), "Please enter your email");
    }

    @Test(groups = {"regression"})
    public void tcLogin04CorreoElectronicoNoRegistrado() {
        String email = "no.registrado." + System.currentTimeMillis() + "@test.com";
        String password = "Password123";

        homePage.goToLogin();
        loginPage.login(email, password);

        Assert.assertTrue(loginPage.getLoginError().contains("No customer account found"));
    }

    @Test(groups = {"functional", "integration"})
    public void tcLogin05ContrasenaOlvidada() {
        String email = "password.olvidada." + System.currentTimeMillis() + "@test.com";
        String password = "Password123";

        registrarUsuario(email, password);
        homePage.logout();
        homePage.goToLogin();
        loginPage.goToPasswordRecovery();
        passwordRecoveryPage.recoverPassword(email);

        Assert.assertTrue(passwordRecoveryPage.getResultMessage().contains("Email with instructions has been sent"));
    }

    private void registrarUsuario(String email, String password) {
        homePage.goToRegister();
        registerPage.registerUser("Richard", "Saucedo", email, password, password);
        Assert.assertEquals(registerPage.getSuccessMessage(), "Your registration completed");
    }
}

package com.store.tests;

import com.store.utils.BaseTest;
import org.testng.Assert;
import org.testng.annotations.Test;

public class RegisterTest extends BaseTest {

    @Test(groups = {"functional"})
    public void tcRegister01RegistroExitoso() {
        String email = "registro.exitoso." + System.currentTimeMillis() + "@test.com";
        String password = "Password123";

        homePage.goToRegister();
        registerPage.registerUser("Richard", "Saucedo", email, password, password);

        Assert.assertEquals(registerPage.getSuccessMessage(), "Your registration completed");
        Assert.assertTrue(homePage.isUserLoggedIn());
    }

    @Test(groups = {"functional", "integration"})
    public void tcRegister02RegistroConCamposObligatoriosFaltantes() {
        homePage.goToRegister();
        registerPage.submitRegister();

        Assert.assertEquals(registerPage.getFirstNameError(), "First name is required.");
        Assert.assertEquals(registerPage.getLastNameError(), "Last name is required.");
        Assert.assertEquals(registerPage.getEmailError(), "Email is required.");
        Assert.assertEquals(registerPage.getPasswordError(), "Password is required.");
        Assert.assertEquals(registerPage.getConfirmPasswordError(), "Password is required.");
    }

    @Test(groups = {"functional", "regression"})
    public void tcRegister03FormatoDeCorreoElectronicoValido() {
        String invalidEmail = "correo.invalido";
        String validEmail = "correo.formato." + System.currentTimeMillis() + "@test.com";
        String password = "Password123";

        homePage.goToRegister();
        registerPage.registerUser("Richard", "Saucedo", invalidEmail, password, password);
        Assert.assertEquals(registerPage.getEmailError(), "Wrong email");

        registerPage.typeEmail(validEmail);
        registerPage.submitRegister();

        Assert.assertEquals(registerPage.getSuccessMessage(), "Your registration completed");
    }

    @Test(groups = {"regression"})
    public void tcRegister04ContrasenaSegura() {
        String email = "password.segura." + System.currentTimeMillis() + "@test.com";
        String weakPassword = "123";
        String strongPassword = "Password123";

        homePage.goToRegister();
        registerPage.registerUser("Richard", "Saucedo", email, weakPassword, weakPassword);
        Assert.assertTrue(registerPage.getPasswordError().contains("must have at least 6 characters"));

        registerPage.typePassword(strongPassword);
        registerPage.typeConfirmPassword(strongPassword);
        registerPage.submitRegister();

        Assert.assertEquals(registerPage.getSuccessMessage(), "Your registration completed");
    }

    @Test(groups = {"functional", "integration"})
    public void tcRegister05ConfirmacionDeContrasena() {
        String email = "confirmacion.password." + System.currentTimeMillis() + "@test.com";
        String password = "Password123";
        String confirmPassword = "Password456";

        homePage.goToRegister();
        registerPage.registerUser("Richard", "Saucedo", email, password, confirmPassword);

        Assert.assertEquals(registerPage.getConfirmPasswordError(),
                "The password and confirmation password do not match.");
    }

    @Test(groups = {"integration"})
    public void tcRegister06CorreoElectronicoUnico() {
        String email = "correo.unico." + System.currentTimeMillis() + "@test.com";
        String password = "Password123";

        registrarUsuario(email, password);
        homePage.logout();
        homePage.goToRegister();
        registerPage.registerUser("Richard", "Saucedo", email, password, password);

        Assert.assertTrue(registerPage.getDuplicateEmailError().contains("The specified email already exists"));
    }

    private void registrarUsuario(String email, String password) {
        homePage.goToRegister();
        registerPage.registerUser("Richard", "Saucedo", email, password, password);
        Assert.assertEquals(registerPage.getSuccessMessage(), "Your registration completed");
    }
}

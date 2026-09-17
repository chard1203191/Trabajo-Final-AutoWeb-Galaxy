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

        Assert.assertEquals(registerPage.getSuccessMessage(), "Thank you for registering with Main Website Store.");
        Assert.assertTrue(homePage.isUserLoggedIn());
    }

    @Test(groups = {"functional", "integration"})
    public void tcRegister02RegistroConCamposObligatoriosFaltantes() {
        String password = "Password123";

        homePage.goToRegister();
        registerPage.typePassword(password);
        registerPage.typeConfirmPassword(password);
        registerPage.submitRegister();

        Assert.assertEquals(registerPage.getFirstNameError(), "This is a required field.");
        Assert.assertEquals(registerPage.getLastNameError(), "This is a required field.");
        Assert.assertEquals(registerPage.getEmailError(), "This is a required field.");
    }

    @Test(groups = {"functional", "regression"})
    public void tcRegister03FormatoDeCorreoElectronicoValido() {
        String invalidEmail = "correoinvalido";
        String password = "Password123";

        homePage.goToRegister();
        registerPage.registerUser("Richard", "Saucedo", invalidEmail, password, password);

        Assert.assertEquals(registerPage.getEmailError(),
                "Please enter a valid email address (Ex: johndoe@domain.com).");
    }

    @Test(groups = {"regression"})
    public void tcRegister04ContrasenaSegura() {
        String email = "password.segura." + System.currentTimeMillis() + "@test.com";
        String weakPassword = "123";

        homePage.goToRegister();
        registerPage.registerUser("Richard", "Saucedo", email, weakPassword, weakPassword);

        Assert.assertTrue(registerPage.getPasswordError()
                .contains("Minimum length of this field must be equal or greater than 8 symbols."));
    }

    @Test(groups = {"functional", "integration"})
    public void tcRegister05ConfirmacionDeContrasena() {
        String email = "confirmacion.password." + System.currentTimeMillis() + "@test.com";
        String password = "Password123";
        String confirmPassword = "Password456";

        homePage.goToRegister();
        registerPage.registerUser("Richard", "Saucedo", email, password, confirmPassword);

        Assert.assertEquals(registerPage.getConfirmPasswordError(), "Please enter the same value again.");
    }

    @Test(groups = {"integration"})
    public void tcRegister06CorreoElectronicoUnico() {
        String email = "correo.unico." + System.currentTimeMillis() + "@test.com";
        String password = "Password123";

        registrarUsuario(email, password);
        homePage.logout();
        homePage.goToRegister();
        registerPage.registerUser("Richard", "Saucedo", email, password, password);

        Assert.assertTrue(registerPage.getDuplicateEmailError()
                .contains("There is already an account with this email address."));
    }

    private void registrarUsuario(String email, String password) {
        homePage.goToRegister();
        registerPage.registerUser("Richard", "Saucedo", email, password, password);
        Assert.assertEquals(registerPage.getSuccessMessage(), "Thank you for registering with Main Website Store.");
    }
}

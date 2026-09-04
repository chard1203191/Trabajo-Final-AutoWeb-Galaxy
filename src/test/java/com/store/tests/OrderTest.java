package com.store.tests;

import com.store.utils.BaseTest;
import org.testng.Assert;
import org.testng.annotations.Test;

public class OrderTest extends BaseTest {

    @Test(groups = {"regression"})
    public void tcOrder01AnadirProductosAlCarrito() {
        String email = "orden.carrito." + System.currentTimeMillis() + "@test.com";
        String password = "Password123";
        String productName = "Apple MacBook Pro 13-inch";

        registrarUsuario(email, password);
        agregarProducto(productName);
        homePage.goToCart();

        Assert.assertTrue(cartPage.isProductInCart(productName));
    }

    @Test(groups = {"regression"})
    public void tcOrder02EliminarProductosDelCarrito() {
        String email = "orden.eliminar." + System.currentTimeMillis() + "@test.com";
        String password = "Password123";
        String productName = "Apple MacBook Pro 13-inch";

        registrarUsuario(email, password);
        agregarProducto(productName);
        homePage.goToCart();
        cartPage.removeProduct(productName);

        Assert.assertTrue(cartPage.getEmptyCartMessage().contains("Your Shopping Cart is empty"));
    }

    @Test(groups = {"functional", "integration"})
    public void tcOrder03ModificarCantidadDeProductosEnElCarrito() {
        String email = "orden.cantidad." + System.currentTimeMillis() + "@test.com";
        String password = "Password123";
        String productName = "Apple MacBook Pro 13-inch";
        String quantity = "2";

        registrarUsuario(email, password);
        agregarProducto(productName);
        homePage.goToCart();
        cartPage.changeProductQuantity(productName, quantity);

        Assert.assertEquals(cartPage.getProductQuantity(productName), quantity);
    }

    @Test(groups = {"regression"})
    public void tcOrder04CreacionDeOrdenExitosaConTresProductosDiferentes() {
        String email = "orden.exitosa." + System.currentTimeMillis() + "@test.com";
        String password = "Password123";
        String firstProduct = "Apple MacBook Pro 13-inch";
        String secondProduct = "HTC One M8 Android L 5.0 Lollipop";
        String thirdProduct = "Leica T Mirrorless Digital Camera";

        registrarUsuario(email, password);
        agregarProducto(firstProduct);
        agregarProducto(secondProduct);
        agregarProducto(thirdProduct);
        homePage.goToCart();
        cartPage.acceptTermsOfService();
        cartPage.startCheckout();
        checkoutPage.fillBillingAddress("Richard", "Saucedo", email, "Peru", "Lima",
                "Av. Principal 123", "15001", "999999999");
        checkoutPage.continueBillingAddress();
        checkoutPage.selectFirstShippingMethod();
        checkoutPage.continueShippingMethod();
        checkoutPage.selectFirstPaymentMethod();
        checkoutPage.continuePaymentMethod();
        checkoutPage.continuePaymentInfo();
        checkoutPage.confirmOrder();

        Assert.assertTrue(checkoutPage.getOrderCompletedMessage().contains("Your order has been successfully processed"));
        Assert.assertTrue(checkoutPage.getOrderNumber().contains("Order number"));
    }

    private void registrarUsuario(String email, String password) {
        homePage.goToRegister();
        registerPage.registerUser("Richard", "Saucedo", email, password, password);
        Assert.assertEquals(registerPage.getSuccessMessage(), "Your registration completed");
    }

    private void agregarProducto(String productName) {
        homePage.searchProduct(productName);
        productPage.openProduct(productName);
        productPage.addProductFromDetails();
        Assert.assertTrue(productPage.getSuccessNotification().contains("The product has been added"));
        productPage.closeNotification();
    }
}

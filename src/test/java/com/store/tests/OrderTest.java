package com.store.tests;

import com.store.utils.BaseTest;
import org.testng.Assert;
import org.testng.annotations.Test;

public class OrderTest extends BaseTest {

    @Test(groups = {"regression"})
    public void tcOrder01AnadirProductosAlCarrito() {
        String email = "orden.carrito." + System.currentTimeMillis() + "@test.com";
        String password = "Password123";
        String productName = "Apple AirPods Pro 2";

        registrarUsuario(email, password);
        agregarProducto(productName);
        homePage.goToCart();

        Assert.assertTrue(cartPage.isProductInCart(productName));
    }

    @Test(groups = {"regression"})
    public void tcOrder02EliminarProductosDelCarrito() {
        String email = "orden.eliminar." + System.currentTimeMillis() + "@test.com";
        String password = "Password123";
        String productName = "Apple AirPods Pro 2";

        registrarUsuario(email, password);
        agregarProducto(productName);
        homePage.openMiniCart();
        Assert.assertTrue(cartPage.isProductInMiniCart(productName));
        cartPage.removeProductFromMiniCart(productName);
        Assert.assertEquals(cartPage.getRemoveConfirmationMessage(),
                "Are you sure you would like to remove this item from the shopping cart?");
        cartPage.confirmProductRemoval();

        Assert.assertEquals(cartPage.getEmptyMiniCartMessage(), "You have no items in your shopping cart.");
    }

    @Test(groups = {"functional", "integration"})
    public void tcOrder03ModificarCantidadDeProductosEnElCarrito() {
        String email = "orden.cantidad." + System.currentTimeMillis() + "@test.com";
        String password = "Password123";
        String productName = "Apple AirPods Pro 2";
        String quantity = "3";

        registrarUsuario(email, password);
        agregarProducto(productName);
        homePage.goToCart();
        Assert.assertTrue(cartPage.isProductInCart(productName));
        cartPage.changeProductQuantity(productName, quantity);

        Assert.assertEquals(cartPage.getProductQuantity(productName), quantity);
        Assert.assertEquals(cartPage.getProductSubtotal(productName, "$749.97"), "$749.97");
    }

    @Test(groups = {"regression"})
    public void tcOrder04CreacionDeOrdenExitosaConTresProductosDiferentes() {
        String email = "orden.exitosa." + System.currentTimeMillis() + "@test.com";
        String password = "Password123";
        String firstProduct = "Apple AirPods Pro 2";
        String firstProductSlug = "airpods-pro-2";
        String secondProduct = "Adidas Ultraboost Running Shoes";
        String secondProductSlug = "ultraboost-running";
        String thirdProduct = "Ray-Ban Wayfarer Sunglasses";
        String thirdProductSlug = "rayban-wayfarer";

        registrarUsuario(email, password);
        agregarProducto(firstProduct);
        agregarProducto(secondProduct, secondProductSlug);
        agregarProducto(thirdProduct, thirdProductSlug);
        homePage.goToCart();
        Assert.assertTrue(cartPage.isProductInCart(firstProduct, firstProductSlug));
        Assert.assertTrue(cartPage.isProductInCart(secondProduct, secondProductSlug));
        Assert.assertTrue(cartPage.isProductInCart(thirdProduct, thirdProductSlug));
        cartPage.startCheckout();
        checkoutPage.fillShippingAddress("Richard", "Test", "Av. Javier Prado 123", "PE",
                "1007", "Lima", "15036", "999999999");
        checkoutPage.selectShippingMethod();
        checkoutPage.continueToPayment();
        checkoutPage.waitForPaymentStep();

        Assert.assertEquals(checkoutPage.getPaymentMethodValue(), "checkmo");
        Assert.assertTrue(checkoutPage.isCheckMoneyOrderSelected());
        Assert.assertEquals(checkoutPage.getPaymentMethodLabel(), "Check / Money order");
        Assert.assertEquals(checkoutPage.getSubtotal("$619.97"), "$619.97");
        Assert.assertEquals(checkoutPage.getShippingTotal("$15.00"), "$15.00");
        Assert.assertEquals(checkoutPage.getGrandTotal("$634.97"), "$634.97");
        checkoutPage.placeOrder();

        Assert.assertEquals(checkoutPage.getOrderCompletedMessage("Thank you for your purchase!"),
                "Thank you for your purchase!");
        Assert.assertFalse(checkoutPage.getOrderNumber().isEmpty());
    }

    private void registrarUsuario(String email, String password) {
        homePage.goToRegister();
        registerPage.registerUser("Richard", "Saucedo", email, password, password);
        Assert.assertEquals(registerPage.getSuccessMessage(), "Thank you for registering with Main Website Store.");
    }

    private void agregarProducto(String productName) {
        homePage.searchProduct(productName);
        productPage.openProduct(productName);
        productPage.addProductFromDetails();
        Assert.assertEquals(productPage.getSuccessNotification(),
                "You added " + productName + " to your shopping cart.");
    }

    private void agregarProducto(String productName, String productSlug) {
        homePage.searchProduct(productName);
        productPage.openProduct(productName, productSlug);
        productPage.addProductFromDetails();
        Assert.assertEquals(productPage.getSuccessNotification(),
                "You added " + productName + " to your shopping cart.");
    }
}
